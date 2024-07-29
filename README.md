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
   - `./mvnw install` to install packages and dependencies using Maven
   - `./mvnw spring-boot:run` to launch back-end application
5. The back-end is now listening on http://localhost:8081
6. Open your Postman and try to invoke some APIs mentioned in the next section

Some other useful commands, just in case:

  `./mvnw clean`: clean everything built by Maven
  
  `./mvnw test`: run some test cases written in the project
  
  `./mvnw install -DskipTests`: useful flag to move forward when tests having issues

# APIs
Basic authentication is used for securing endpoints and simplicity purpose.

Therefore, let's take notes about user credentials being initialized when app get started.

| username  | password |
| --------- | --------:|
| admin     | password |
| user      | password |
| john      | password |

###### Using Postman?
* Open Postman and create a new request.
* Select the HTTP method (GET, POST, PUT, DELETE, etc.).
* Enter the request URL.
* Go to the "Authorization" tab.
* Select "Basic Auth" from the dropdown menu.
* Enter the username and password.
* Click "Send" to make the request.

#### POST /users/register
**Description:** create a new user account

**Authorization:** Public / No Auth

**Body:**
```json
{
    "username": "userB",
    "password": "password",
    "roles": "ADMIN"
}
```

**Note:**

Even when you set role "admin" or any value, it will end up as role "USER".
Admin can only be created via SQL statement, so look up file `data.sql` in the project to add manually.
