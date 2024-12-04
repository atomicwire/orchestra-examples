# OpenAPI Spring Boot application

This example shows how to use an OpenAPI spec generated from Orchestra to power a Spring Boot server.

The Spring OpenAPI generator creates server scaffolding, interfaces for the API routes defined in the OpenAPI spec, and model classes for the schemas ultimately specified in the Orchestra spec.

An implementation of the API is then provided in
[InstrumentApiDelegateImpl.java](./src/main/java/org/example/orchestra/springboot/InstrumentApiDelegateImpl.java).
> **Note**: This is a dummy implementation that always provides the same response.

## Configuration

This example uses the spec from [basic-examples/09-openapi](../../basic-examples/09-openapi). Please refer to that example for a detailed
explanation of how the OpenAPI spec is generated from Orchestra.

## Run

Use the Gradle wrapper to start the Spring Boot server.

```shell
$ ./gradlew :app-examples:03-openapi-springboot:bootRun
```

## Results

You can query the server over HTTP.

```shell
$ curl localhost:8080/instrument/IBM

{"UPICode":null,"Symbol":"IBM","SecurityID":"459200-10-1","SecurityIDSource":"CUSIP","Product":"EQUITY","CFICode":"ESNUOB"}
```
