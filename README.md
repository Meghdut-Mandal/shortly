#  Shortly : The simple link-shortner

Test link :-  https://newi.ga/  

## API 

 `/{shortCode}`   :  **GET**  ->  Redirects to the specified URL, 400 Bad request if invalid code
 <br>
`/generate  `    : **POST**  JSON  Body   { "link" : "http://apple.com" }   ->
 Return a shortned url as    {  "shortUrl": "https://newi.ga/p5l0pttp" }

## Intalling 

Clone the  repository and configure the server.prop
 
    rootUrl= http://newi.ga  ## The public url of the server
    port=80  ## The port number 


Using docker 
1. Build the docker image
```
 docker build -t shortly .
```
2. Run the docker container 

Then execute the command to start the server
```
 docker run -dp 8080:8080 shortly
```
