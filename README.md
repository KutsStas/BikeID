# Bike ID

> The application was created for registration and accounting of bicycles.

The main task of the application is to create a system of bicycle passports.
Also get information about workshops, maintenance and the fight against bike theft.

## Software requirements

To run the project you need installed:

* [Java 11](https://www.oracle.com/cis/java/technologies/javase/jdk11-archive-downloads.html)  or higher version
* [Maven](https://maven.apache.org/)
* [PostgreSQL](https://www.postgresql.org/docs/)
* [Redis](https://redis.io/docs/about/)
* [POSTMAN](https://www.postman.com/)

## Installing / Getting started

* Clone, fork or download the source code from this Github page
* Create database from file: src/main/resources/db/migration/.sql
* Run mvn clean install
* Copy BikeID-0.0.1-SNAPSHOT.jar from target folder and paste it to the /TOMCAT/webapps folder.

## Launch

* Run Tomcat server using script /TOMCAT/bin/startup.bat
* Connect to Redis server host "127.0.0.1",port 6379
* Connect to the application at http://localhost:8080/

