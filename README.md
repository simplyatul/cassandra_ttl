# Java Cassandra Demo

## Introduction:
This project demonstrates writing into Cassandra DB using Java. Spring Boot Framework is used. Project exposes ReST API to push data into DB. Upon storing data, it returns a GUID which then used to fetch the pushed data.

This code also demonstrate how to write DB row with TTL.

## Prerequisites:
- Spring Boot Framework (Tested with v2.1.6 on Java v1.8)
- Maven (Tested with Maven 3.6.1)
- Cassandra (Tested with Cassandra v3.11.4)

## Cassandra Setup:
1. Create Keyspace in Cassandra

* `cqlsh -e "CREATE KEYSPACE demoks WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1};"`

## Testing the Project:
- Load the Project using Spring Tool Suit
- In cassandra.properties file, put the Cassandra machine IP in cassandra.contactpoints
- Run the project as Spring Boot App
- Run postData to push the data into DB. It return a GUID.
* `postData.sh <IP>:8800`
- Then get the same data by running 
* `getData.sh <GUID>`

## License
[MIT](https://github.com/simplyatul/cassandrademo/blob/master/LICENSE)
