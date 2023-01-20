# CountryBorders

This application can be used to find out the routes between the two countries. It uses a static json response which can be used to find the borders.

URL : https://raw.githubusercontent.com/mledoze/countries/master/countries.json



### How to build and he run the application

1. to build the application we can use mvn clean package
2. once the application is built, we can use `java -jar target/CountryBorders-0.0.1-SNAPSHOT.jar` to run the application.


### Other options for building the application
1. Currently not included but we can use docker to create a image and run it as a container in any of the cloud.


### Testing the applications.

1. ./mvnw test will execute the integration and the unit tests.
2. http://localhost:8080/swagger-ui/index.html using the swagger url

