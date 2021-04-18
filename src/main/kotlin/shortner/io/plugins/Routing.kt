package shortner.io.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.util.*
import shortner.io.Datastore
import java.util.*

fun Application.configureRouting() {
    install(Locations) {
    }
    install(ContentNegotiation) {
        gson()
    }

    val rootUrl = Datastore["rootUrl"]

    routing {
        post<Generate> {
            val urlLink = call.receive<LinkRequest>().link
            val longCode = Base64.getEncoder().encodeToString(urlLink.toByteArray())
            if (Datastore.allKeys.contains(longCode)) {
                val shortCode =
                    Datastore[longCode] ?: throw IllegalArgumentException("this should happen")
                return@post call.respond(HttpStatusCode.OK, Data("$rootUrl/$shortCode"))
            } else {
                val shortCode = System.currentTimeMillis().toString(35)
                Datastore[longCode] = shortCode
                Datastore[shortCode] = longCode
                return@post call.respond(HttpStatusCode.OK, Data("$rootUrl/$shortCode"))
            }

        }

        get<LoadUrl>{
            if (Datastore.allKeys.contains(it.code)){
                val longcode=Datastore[it.code]
                val urlLink = String(Base64.getDecoder().decode(longcode))

              return@get  call.respondRedirect(urlLink)
            }
          return@get  call.respond(HttpStatusCode.BadRequest,"Bad Url")
        }

    }
}

data class Data(val shortUrl: String)
data class LinkRequest(val link: String)


@Location("/generate")
class Generate

@Location("/{code}")
data class LoadUrl(val code:String)



