# OrderAPI

Order API is used to hold/gather information related to order placed by users. It is used to persist/update orders (hotel, flight)booking related information 

## Installation

Use Maven to build the application

```bash
mvn clean install
```
Upon successfully packaging the app, Use the command to start the application

```bash
mvn spring-boot:run
```
Alternatively We can use Docker to set up and run the application

```bash
docker build -t image:0.0.1 .
```
Create docker container from the image created

```bash
docker run -d -it --name testcontainer image:0.0.1
```
* **Health** :
Endpoint used to determine whether the service is Up or Not

   [http://localhost:8081/orderapi/actuator/health](http://localhost:8081/orderapi/actuator/health)

* **Swagger**:
Endpoint used to determine the API endpoint and the response and error models
You can execute the application from within the swagger endpoint by clicking on "Try It Out".

   [http://localhost:8081/orderapi/swagger-ui.html](http://localhost:8081/orderapi/swagger-ui.html)

* **Metrics**:
Endpoint used to determine the metrics of the application and the environment statistics assosiated with it.

   [http://localhost:8081/orderapi/actuator/metrics](http://localhost:8081/orderapi/actuator/metrics)

* For Specific metrics, like http.server.requests, append the same to the url above.

* API EndPoint:
The API endpoint can be found in Swagger documentation. 
There is one endpoint as it is still in development phase. But feel free to try it out.
1. http://localhost:8081/orderapi/v1/createOrder

## Support & Ownership

Feel free to ask [Sijumon Karyil Raju](sijuthomas1988@gmail.com) if you need some support when there are any questions left or if you need some support.
