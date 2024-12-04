# OpenAPI Spring Boot application

This example demonstrates using Orchestra to create an OpenAPI document for a REST API, then generating the scaffolding for a Spring Boot web application from it.

1. First, a JSON Schema is generated from the Orchestra specification
2. Then, a base OpenAPI document can be written that references the schemas produced in the first step
3. Finally, the scaffolding for a Spring Boot application can be generated from the combined OpenAPI document

This example includes an implementation of the generated API scaffolding in [InstrumentApiDelegateImpl.java](
./src/main/java/org/example/orchestra/springboot/InstrumentApiDelegateImpl.java).
> **Note**: This is a dummy implementation that always provides the same response.

## Configuration

This example requires configuration of JSON schema generation in the [build.gradle](./build.gradle) file (as seen in a [previous](../06-json-schema) example), the addition of a custom Gradle task, and the set up of an OpenAPI documentation generator.

```groovy
orchestra {
  specification {
    // spec derived from fix-latest
    markdown {
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
      enableSpotless()
    }

    encoding {
      datatypeMapping([
        double: [
          JSON: 'number',
        ],
        string: [
          JSON: 'string',
        ],
        Pattern: [
          JSON: 'string',
        ],
      ])
    }
  }

  // Generate a JSON Schema for inclusion in the OpenAPI spec
  jsonSchema {
    namespace = 'org.example.orchestra'
  }
}
```

A custom task is added to place the two files together, allowing the provided [openapi.yaml](./openapi.yaml) file to easily reference schemas from the JSON schema file generated and later renamed by the plugin.

```groovy
def colocateFiles = tasks.register('colocateOpenApiFiles', Copy) {
  from(orchestraGenerateJsonSchema) {
    rename { 'schemas.json' }
  }
  from 'openapi.yaml'

  into layout.buildDirectory.dir('tmp/openapi')
}
```

Finally, a task `openApiGenerate` is configured that generates the Spring Boot application scaffolding.

## Run

Use the Gradle wrapper to start the Spring Boot server.

```shell
$ ./gradlew :app-examples:03-openapi-springboot:bootRun
```

## Results

You can then query the server over HTTP:

```shell
$ curl localhost:8080/instrument/IBM
```

```json
{"UPICode":null,"Symbol":"IBM","SecurityID":"459200-10-1","SecurityIDSource":"CUSIP","Product":"EQUITY","CFICode":"ESNUOB"}
```
