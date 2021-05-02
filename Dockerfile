FROM openjdk:8-jdk
EXPOSE 8080:80
RUN mkdir /app
COPY . /app
WORKDIR /app
CMD ["java","-jar","build/libs/shortly.jar"]
