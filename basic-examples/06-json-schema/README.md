# JSON schemas

This example shows how to generate JSON schemas from a custom Orchestra spec.

## Configuration

To generate an JSON schema from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra spec to the corresponding JSON datatype.

Datatype mapping is supplied via the `encoding` extension in the [build.gradle](./build.gradle).

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

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:06-json-schema:runExample
```

> **Note**: `runExample` is wired to call the `orchestraGenerateJsonSchema` task from the Orchestra plugin.

## Results

The JSON schema(s) will be output to the Gradle build folder.

```shell
$ ./basic-examples/06-json-schema/build/generated/sources/orch-gen-jsonschema/main/jsonschema/06-json-schema.json
```
