# Avro schemas

This example shows how to generate Avro schemas from a custom Orchestra spec.

## Configuration

To generate an Avro schema from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra spec to the corresponding Avro datatype.

Datatype mapping is supplied via the `encoding` extension in the [build.gradle](./build.gradle) file. [Avro logical types](https://avro.apache.org/docs/1.11.0/spec.html#Logical+Types) can be used to represent more complex or semantically meaningful data types on top of Avro's primitive types.

The Avro schema generation is activated by the presence of the `avro` extension. A `namespace` value must be provided.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}

    encoding {
      datatypeMapping([
        double: [
          Avro: 'double',
        ],
        string: [
          Avro: 'string',
        ],
        timestamp: [
          Avro: [
            standardType: 'long',
            avroLogicalType: 'timestamp-micros',
          ],
        ],
      ])
    }
  }

  avro {
    namespace = 'org.example.orchestra'
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:05-avro-schema:runExample
```

> **Note**: `runExample` is wired to call the `orchestraGenerateAvroSchema` task from the Orchestra plugin.

## Results

The Avro schema(s) will be output to the Gradle build folder.

```shell
$ ./basic-examples/05-avro-schema/build/generated/sources/orch-gen-avro/main/avro/05-avro-schema.avsc
```
