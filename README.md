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

## Features

###  User Authentication
A user can register with a valid email and password. There is currently no email verification but this will be added 
later.

A user can query for a JWT token using their email and password. This token can be used to make requests to secured 
endpoints by adding the JWT token as a bearer token.

The system is a stand alone [oauth 2 resource server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
It uses the inbuilt spring security JWT support to generate and validate tokens.

### Task Management

Users can
- Create an account
- Retrieve a list of tasks
- Update task details
- Delete tasks

Users cannot currently mark tasks as completed

### User Specific Data
- An admin can view a list of tasks for all users
- An admin can view a single task created by any user
- A user can only view a list of tasks they own
- A user can only view a single task they own
- A user or admin can only update or delete a task they own. Makes sense right? 😉

### Database Integration
[Spring Data JPA](https://spring.io/projects/spring-data-jpa) is used for database operations and the database of choice is PostgreSQL.

### Security
Role-based access control is implemented to restrict access to certain operations

### Error Handling
There is robust error handling for different scenarios with meaningful error messages in the GraphQL responses. This 
is handled by a Custom Exception handler.

Handled Exceptions include:
- NotFoundException
- AlreadyExistsException
- BadCredentialsException
- AccessDeniedException
- ConstraintViolationException

All other exceptions get a default response 'Something went wrong. Please try again later'

Invalid token issues are currently a challenge as the response makes no sense and I haven't found a way to handle it yet.

### Logging
Spring boot's default logging mechanism is configured to log to files

### Monitoring and Metrics
[Spring Boot's Actuator](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) is configured to 
expose endpoints for health, information and httpexchanges. Visit [localhost:8080/actuator](http://localhost:8080/actuator)
to see all available endpoints. Note that these endpoints are exposed over Rest and not GraphQL.

Challenge

The http traces all have a path '/graphql' and a response status of 200 because of the nature of GraphQL. I thought 
of customizing the http traces however the HttpTrace class is final. I am still looking for workaround for this.


