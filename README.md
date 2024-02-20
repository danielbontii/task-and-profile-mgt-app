# Task and Profile Management App

A comprehensive Task and Profile Management System API using Spring Boot and GraphQL. This system allows users to
securely manage their tasks and upload/delete their profile pictures. The project covers a range of production-ready
functionalities, including user authentication, task management, and profile picture handling.

Technologies Used:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [GraphQL](https://graphql.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [Flyway](https://flywaydb.org/)

## Running up the project

The project can be run in two different ways:

1. With docker
2. Without docker

### Running the project with docker

* Install [docker](https://docs.docker.com/engine/install/) if you haven't already.
* Rename .env.example to .env or create a .env file and copy the contents of .env.example into it
* Fill all the environment variables
* From the root of the project run the command `docker compose up -d`

### Running the project without docker

* Create a database or use an online database
* Fill all the environment variables
  with [intellij](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) or
  directly replace the variables on the application.yml file
* Click the run button on intellij

Execute mutations and queries on [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?
path=/graphql).
If you have customized the url, use your custom url.
