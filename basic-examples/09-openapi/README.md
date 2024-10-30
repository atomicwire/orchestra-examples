# OpenAPI

This example shows how to use Orchestra to support an OpenAPI specification for a REST API. 

The [openapi.yaml](./openapi.yaml) file is used to describe the API itself, while a `schemas.json` file is generated from the Orchestra specification to describe the underlying data model. Schemas from this file are then referenced from `openapi.yaml`.

An off-the-shelf OpenAPI documentation generator is also run to demonstrate one possible usage of the OpenAPI spec.

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
The last step is to configure an off-the-shelf OpenAPI documentation generator.

```groovy
tasks.openApiGenerate {
  inputs.dir(colocateFiles.map { it.destinationDir })
  inputSpec = colocateFiles.map {it.destinationDir.toPath().resolve('openapi.yaml').toString() }
  generatorName = 'html2'
  outputDir = layout.buildDirectory.dir('openapi').get().toString()
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:09-openapi:runExample
```
> **Note**: `runExample` is wired to call the `openApiGenerate` task in [build.gradle](./build.gradle).

## Results

The OpenAPI resources will be output to the Gradle build folder.

```shell
$ ./basic-examples/09-openapi/build/openapi/index.html
```
