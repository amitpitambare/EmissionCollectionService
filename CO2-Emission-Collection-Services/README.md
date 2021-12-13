# CO2 Sensor Emission Collection Services
Carbon Dioxide (CO2) is all around us and we are constantly expelling it, but in high concentration CO2 can be harmful or even lethal.CO2 Levels between 2000 and 5000 ppm are associated with headaches, sleepi-
ness poor concentration, loss of attention, increased heart rate and slight nausea may also be present.

## Objective
To build a service capable of collecting data from hundreds of thousands of sensors and alert if the CO2 concentrations reach critical levels.
### Feature Requirements
* If the CO2 level exceeds 2000 ppm the sensor status should be set to WARN
* If the service receives 3 or more consecutive measurements higher than 2000 the sensor status should be set to ALERT
* When the sensor reaches to status ALERT an alert should be stored
* When the sensor reaches to status ALERT it stays in this state until it receives 3 consecutive measurements lower than 2000; then it moves to OK
* The service should provide the following metrics about each sensor:
 - Average CO2 level for the last 30 days
 - Maximum CO2 Level in the last 30 days
* It is possible to list all the alerts for a given sensor
## Architecture Style
* CQRS 
* Event Driven 

## Persistence Layer
* Mongo DB is used as persistence storage as it can be good choice for capturing  time series events and provides powerful aggregations mechanisms. 
* Spring Data Mongo library is used for interacting with Mongo Database
 
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Application framework

## UML Diagrams
#### Sequence Diagrams:
* Collect Sensor Measurement: ![Alt text](diagrams/CollectSensorMeasurement.png?raw=true "Collect Sensor Measurement")
* Sensor Status: ![Alt text](diagrams/FetchSensorStatus.png?raw=true "Sensor Status")
* Sensor Metrics: ![Alt text](diagrams/FetchLast30DaysAverageMetrics.png?raw=true "Sensor Metrics")

## Running Locally
* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 
* Build the project and install all the dependencies by running ```mvn clean install```


```
cd emission-command-service     //Navigate to emission-command-service  project directory
java -jar -Dserver.port=7070  target/emission-command-service-1.0-SNAPSHOT.jar

```

```
cd emission-query-service     //Navigate to emission-query-service  project directory
java -jar -Dserver.port=7080  target/emission-command-service-1.0-SNAPSHOT.jar

```

##Services or API
The Services exposed all are REST based services 
Complete List of APIS can be accessed using swagger using these below endpoints
* emission-command-service:- http://localhost:7070/swagger-ui.html#/
* emission-query-service: http://localhost:7080/swagger-ui.html#/

##### Collect sensor mesurements

API is used  to receive measurements from each sensor.
REQUEST:
```
POST <base-url>/api/v1/sensors/{uuid}/measurements
Accept: */*
Content-Type: application/json

{
  "co2": 1350,
  "time": "2021-12-10T19:15:56.544Z"
}
```
RESPONSE: HTTP 202 (ACCEPTED)

#####Sensor status
This service will give the current status of sensor whether the CO2 concentration levels are OK STATE,WARN STATE,ALERT STATE
 
REQUEST:
```
GET <base-url>/api/v1/sensors/{uuid}
URL PARAMETER: uuid :-Unique identifier for the Sensor
```
RESPONSE: HTTP 200 (OK)
```
{
"status" : "OK" // Possible status OK,WARN,ALERT
}
```

##### Sensor Metrics
REQUEST:
```
GET <base-url>/api/v1/sensors/{uuid}/metrics
URL PARAMETER: uuid :-Unique identifier for the Sensor
```
RESPONSE: HTTP 200 (OK)
```
{
"maxLast30Days" : 1200,
"avgLast30Days" : 900
}
```


##### Listing Alerts (TO BE IMPLEMENTED)
REQUEST:
```
GET <base-url>/api/v1/sensors/{uuid}/alerts
URL PARAMETER: uuid :-Unique identifier for the Sensor
```
RESPONSE: HTTP 200 (OK)
```
[
{
"startTime" : "2019-02-02T18:55:47+00:00",
"endTime" : "2019-02-02T20:00:47+00:00",
"mesurement1" : 2100,
"mesurement2" : 2200,
"mesurement3" : 2100
}
]
```

## FUTURE SCOPE
* Implementing Microservices Pattern on top of this.(Adding ZUUL,RIBBON and EUREKA Server)
* Implementing Kafka for Event Sourcing.