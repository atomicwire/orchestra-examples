# Avro schema example

This example demonstrates generating an Avro schema for a custom Orchestra spec.

## Configuration

See [build.gradle](./build.gradle). 

In order to generate an Avro schema for an Orchestra spec, additional type information is required that indicates
which Avro datatype corresponds to each datatype in the Orchestra spec. 

Datatype mapping is supplied via the `encoding` extension. [Avro logical types](https://avro.apache.org/docs/1.11.0/spec.html#Logical+Types) 
can be used to represent more complex or semantically meaningful data types on top of Avro's primitive types.

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

To generate an Avro schema from the Markdown file run

```
./gradlew :basic-examples:05-avro-schema:runExample
```
`runExample` is wired to call the `orchestraGenerateAvroSchema` task from the Orchestra plugin.

## Results 

The Avro schema will be generated in the Gradle build folder. e.g.

```
./basic-examples/05-avro-schema/build/generated/sources/orch-gen-avro/main/avro/05-avro-schema.avsc
```
