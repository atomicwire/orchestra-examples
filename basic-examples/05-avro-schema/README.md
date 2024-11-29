# Avro Schema Generation

This example demonstrates how to generate [Apache Avro](https://avro.apache.org) schemas directly from an Orchestra specification.

This feature streamlines integration with modern data platforms like [Apache Kafka](https://kafka.apache.org) by eliminating the need to maintain separate Avro schemas.

The example uses the Order Book specification from a [previous](../02-markdown) example.

## Configuration

To generate an Avro schema from an Orchestra specification, additional type information is needed to map each datatype in the Orchestra specification to the corresponding Avro datatype.

Add the `encoding` extension to the [build.gradle](./build.gradle) file and define a datatype mapping for each datatype in your Orchestra specification. 

The plugin supports [Avro logical types](https://avro.apache.org/docs/1.11.0/spec.html#Logical+Types), enabling representation of more complex or semantically meaningful data types beyond Avro's primitive types.

Avro schema generation is enabled by the presence of the `avro` extension. A `namespace` value must be provided.

```groovy
orchestra {
  specification {
    markdown {}

    // Specify the corresponding Avro datatype for each datatype in the Orchestra specification.
    encoding {
      datatypeMapping([
        Double: [
          Avro: 'double',
        ],
        Integer: [
          Avro: 'int',
        ],
        Price: [
          Avro: 'double',
        ],
        Quantity: [
          Avro: 'int',
        ],
        String: [
          Avro: 'string',
        ],
        Timestamp: [
          Avro: [
            standardType: 'long',
            avroLogicalType: 'timestamp-micros',
          ],
        ],
      ])
    }
  }
    
  // Enable Avro schema generation
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

The Avro schema will be output to the Gradle build folder.

```shell
$ ./basic-examples/05-avro-schema/build/generated/sources/orch-gen-avro/main/avro/05-avro-schema.avsc
```
