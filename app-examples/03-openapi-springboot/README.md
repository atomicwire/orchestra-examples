# OpenAPI Spring Boot application example

This example demonstrates how an OpenAPI spec, generated from Orchestra, can be used to power a Spring Boot server.

The Spring OpenAPI generator generates the server scaffolding, interfaces for the API routes that are configured
in our OpenAPI spec, and model classes for the Schemas that were ultimately defined in our Orchestra spec.

Our implementation of the API is then provided in
[InstrumentApiDelegateImpl.java](./src/main/java/org/example/orchestra/springboot/InstrumentApiDelegateImpl.java). 
Note that this is a dummy implementation that always provides the same response.

This example consumes the spec from [basic-examples/09-openapi](../../basic-examples/09-openapi). See it for further 
explanation of how the OpenAPI spec is generated from Orchestra.

## Run

To start the Spring Boot server run

```
./gradlew :app-examples:03-openapi-springboot:bootRun
```

## Results 

It can then be queried over HTTP
```
$ curl localhost:8080/instrument/IBM
{"UPICode":null,"Symbol":"IBM","SecurityID":"459200-10-1","SecurityIDSource":"CUSIP","Product":"EQUITY","CFICode":"ESNUOB"}
```
