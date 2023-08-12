## About The Project

A simple Bank Account System.

## Getting Started

### Dependencies

* Java 17
* Maven 3.9.0

## How to run the application

* Download the project
* Unzip it
* Open terminal in the folder created
* Run:

```
cd .\backend-main\

mvn spring-boot:run
```

* Open browser and navigate to https://localhost:8080/

## How to test the application

```
mvn test
```

A JaCoCo report is available under %PROJECT_DIR%\target\site\jacoco\

## How to run as Docker container

Requires Docker installation
```
docker build -t kyrkosma/backend .
docker run -p 8080:8080 kyrkosma/backend
```

## Help

On startup, three customers are created. You can find the scripts under %PROJECT_DIR%\src\main\resources\data.sql

The front-end shows just a subset of the API's functionalities.

You can find the whole suite of endpoints at https://localhost:8080/swagger-ui/index.html.

You can also import all available requests using Postman. Just import file
%PROJECT_DIR%\postman\BACKEND.postman_collection.json.