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

------

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

**Response - Success**
```
201 - Created
{
    "id": 2,
    "username": "user",
    "password": null,
    "roles": "USER",
    ...
}
```

**Response - Failure**
```
400 - Bad Request
```

**Note:**
Even when you set role "admin" or any value, it will end up as role "USER".
Admin can only be created via SQL statement, so look up file `data.sql` in the project to add manually.

------

#### POST /users/login
**Description:** authenticate user

**Authorization:** Basic Authentication (ADMIN/USER)

**Response - Success**
```
200 - OK
{
    "id": 2,
    "username": "user",
    "password": null,
    "roles": "USER",
    ...
}
```

**Response - Failure**
```
401 - Unauthorized
```

**Note:**
After authentication, you can call other APIs without setting Basic Auth again.

------

#### GET /users/logout
**Description:** logout current user account

**Authorization:** Public / No Auth

**Response**
```
204 - No Content
```

**Note:**
After logout, calling APIs requiring Basic Auth will return 401 - Unauthorized.

------

#### GET /vehicles
**Description:**

Get vehicles, including associated maintenance records.

Admin: get all vehicles.

User: get vehicles that user has ownership

**Authorization:** Basic Authentication (ADMIN/USER)

**Response - Success**
```
200 - OK
[
    {
        "id": 1,
        "userId": 2,
        "brand": "Honda",
        "model": "XYZ",
        "year": 2020,
        "type": "bike",
        "maintenanceRecords": []
    },
    {
        "id": 2,
        "userId": 2,
        "brand": "Toyota",
        "model": "M1001",
        "year": 2021,
        "type": "car",
        "maintenanceRecords": [
            {
                "id": 1,
                "vehicleId": 2,
                "date": "2024-07-28T17:00:00.000+00:00",
                "description": "Fix wheel",
                "cost": 14.9,
                "vehicle": null
            },
            {
                "id": 2,
                "vehicleId": 2,
                "date": "2024-07-24T17:00:00.000+00:00",
                "description": "Fix chair",
                "cost": 23.9,
                "vehicle": null
            }
        ]
    }
]
```

**Response - Failure**
```
401 - Unauthorized
```

**Note:**
Use `GET /vehicles/{id}` to get only one vehicle.

#### POST /vehicles
**Description:** Add a new vehicle.

**Authorization:** Basic Authentication (ADMIN Only)

**Body:**
```json
{
    "brand": "Suzuki",
    "model": "HZ",
    "year": 2001,
    "type": "bike",
    "userId": 1
}
```

**Response - Success**
```
201 - Created
{
    "id": 3,
    "userId": 1,
    "brand": "Suzuki",
    "model": "HZ",
    "year": 2001,
    "type": "bike"
}
```

**Response - Failure**
```
401 - Unauthorized
```

#### PUT /vehicles/{id}
**Description:** Update a vehicle, restricted to the vehicle owner or Admin.

**Authorization:** Basic Authentication (ADMIN/USER)

**URL:** `id = 1`

**Body:**
```json
{
    "userId": 1,
    "brand": "Suzuki",
    "model": "HZ10",
    "year": 1999,
    "type": "bike"
}
```

**Response - Success**
```
200 - OK
{
    "id": 1,
    "userId": 1,
    "brand": "Suzuki",
    "model": "HZ10",
    "year": 1999,
    "type": "bike"
}
```

**Response - Failure**
```
401 - Unauthorized
404 - Not Found
```

