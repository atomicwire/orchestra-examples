# JSON schema example

This example demonstrates generating an JSON schema for a custom Orchestra spec.

## Configuration

See [build.gradle](./build.gradle). 

In order to generate an JSON schema for an Orchestra spec, additional type information is required that indicates
which JSON datatype corresponds to each datatype in the Orchestra spec. 

Datatype mapping is supplied via the `encoding` extension. 

The JSON schema generation is activated by the presence of the `jsonSchema` extension. A `namespace` value must be provided.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}

    encoding {
      datatypeMapping([
        double: [
          JSON: 'number',
        ],
        string: [
          JSON: 'string',
        ],
        timestamp: [
          JSON: 'string',
        ],
      ])
    }
  }

  jsonSchema {
    namespace = 'org.example.orchestra'
  }
}
```

## Run

To generate an JSON schema from the Markdown file run

```
./gradlew :basic-examples:06-json-schema:runExample
```
`runExample` is wired to call the `orchestraGenerateJsonSchema` task from the Orchestra plugin.

## Results 

The Avro schema will be generated in the Gradle build folder. e.g.

```
./basic-examples/06-json-schema/build/generated/sources/orch-gen-jsonschema/main/jsonschema/06-json-schema.json
```
