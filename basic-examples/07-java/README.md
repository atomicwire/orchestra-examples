# Java example

This example demonstrates generating code for use with the Orchestra Java library.

Java will be generated for the Messages, Components, Fields and CodeSets defined in the Orchestra spec.

## Configuration

See [build.gradle](./build.gradle). 

In order to generate the Orchestra Java code for an Orchestra spec, additional type information is required that 
indicates which Java datatype corresponds to each datatype in the Orchestra spec.

Datatype mapping is supplied via the `encoding` extension. 

The Java code generation is activated by the presence of the Java `codeGen` extension. A `packageName` value must be provided.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}

    encoding {
      datatypeMapping([
        double: [
          AWP: 'double',
        ],
        string: [
          AWP: 'string',
        ],
        timestamp: [
          AWP: 'instant',
        ],
      ])
    }
  }

  java {
    codeGen {
      packageName = 'org.example.orchestra'
    }
  }
}
```

## Run

To generate an Java code from the Markdown file run

```
./gradlew :basic-examples:07-java:runExample
```
`runExample` is wired to call the `orchestraGenerateJava` task from the Orchestra plugin.

## Results 

The Java will be generated in the Gradle build folder. e.g.

```
./basic-examples/07-java/build/generated/sources/orch-gen-java/main/java
```
