# Requirements
[Java Spring Boot Technical Assignment: Vehicle Management System](https://dabivn.notion.site/Java-Spring-Boot-Technical-Assignment-Vehicle-Management-System-8406262e4d1b492da4d345657b193011)

Develop a basic vehicle management system using the Java Spring Boot framework. The system should allow users to register, authenticate, and manage vehicles with role-based access.

# What You Need
* Installed: Java and Mave _(got Maven wrapper in this project)_
* Favourite CLI or IDE with built-in CLI
* Postman for API testing

# Setup Process
1. Clone this project to your local machine
2. Open project's root directory via your favourite CLI
3. Make sure no application using port 8081
4. Run commands:
 * `./mvnw install` to install packages and dependencies using Maven
 * `./mvnw spring-boot:run` to launch back-end application
5. The back-end is now listening on http://localhost:8081
6. Open your Postman and try to invoke some APIs mentioned in the next section

Some other useful commands, just in case:
`./mvnw clean`: clean everything built by Maven
`./mvnw test`: run some test cases written in the project
`./mvnw install -DskipTests`: useful flag to move forward when tests having issues

# APIs
