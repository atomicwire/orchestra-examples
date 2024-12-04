# JSON Schema Generation

This example demonstrates how to generate [JSON schemas](https://json-schema.org/) directly from an Orchestra specification.

This feature streamlines the creation of REST APIs and integration with modern UI frameworks, data storage solutions, and analytics tools like [Elasticsearch](https://www.elastic.co/) by eliminating the need for separate JSON schema management.

The example uses the Order Book specification from a [previous](../02-markdown) example.

## Configuration

To generate a JSON schema from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra specification to the corresponding JSON datatype.

Add the `encoding` extension to the [build.gradle](./build.gradle) file and define a datatype mapping for each datatype in your Orchestra specification.

JSON schema generation is enabled by the presence of the `jsonSchema` extension. A `namespace` value must be provided.

```groovy
orchestra {
  specification {
    markdown {}

    // Specify the corresponding JSON datatype for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping([
        Double: [
          JSON: 'number',
        ],
        Integer: [
          JSON: 'number',
        ],
        Price: [
          JSON: 'number',
        ],
        Quantity: [
          JSON: 'number',
        ],
        String: [
          JSON: 'string',
        ],
        Timestamp: [
          JSON: 'string',
        ],
      ])
    }
  }

  // Enable JSON schema generation
  jsonSchema {
    namespace = 'org.example.orchestra'
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:06-json-schema:runExample
```

> **Note**: `runExample` is wired to call the `orchestraGenerateJsonSchema` task from the Orchestra plugin.

## Results

The JSON schema will be output to the Gradle build folder.

```shell
$ ./basic-examples/06-json-schema/build/generated/sources/orch-gen-jsonschema/main/jsonschema/06-json-schema.json
```
