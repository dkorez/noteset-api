# Noteset App
Noteset is a simple application to edit and organize notes. The goal of this project is to research and implement new technologies using Java Spring Boot and Angular frameworks.
Demo is available at [https://notes-app.dkorez.dev](https://notes-app.dkorez.dev)
Please note that this project is still in early phase and some important features like user registration and propper database connection are not implemented yet.

This application is split into two parts, that is [Noteset API](https://github.com/dkorez/noteset-api) and [Noteset web](https://github.com/dkorez/noteset-web).

## Noteset API
Noteset API is the backend REST API for the noteset application. It is used to manipulate data by performing all of the CRUD operations. 
While it is still in early phase of development, it uses in-memory H2 database and Spring Data JPA for data access. Data is exposed via REST API endpoints.


### Requirements
- Spring Boot 3
- Java 17
- Maven

### Getting started
There are several ways to run Spring Boot application with IDE of your choice. One way is by using maven:
`mvn clean package`
And then start the application with:
`mvn spring-boot:run`

To test that everything is running:
`curl http://localhost:8080/api/version`
and you should see the version of the application as a result

### optionally you can run it with docker:
Build docker image with this command:
`docker build -t dkorez/noteset-api -f Dockerfile .`
And to start docker image:
`docker run -p 9099:8080 dkorez/noteset-api`

### To do:
- user registration
- database setup
- build configuration for multiple environments
