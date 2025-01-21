# Chat-Producer

## Summary:

* This is a java spring boot kafka producer and consumer service
* Consumes rest api messages to be put onto kafka topics

### Reference Links

For further reference, please consider the following sections for working with kafka locally:

* [Bizzaroerik Docker Kafka Github](https://github.com/BizzaroErik/kafka-docker)

### Run Instructions

###### Build the repo

     ./gradlew clean build

###### Run Configs

-Dspring_application_context_path=/chat-producer

-Dspring_application_host=/chat-producer

-Dspring_application_base_path=/

###### Swagger

http://localhost:8080/chat-producer/swagger-ui/index.html 


