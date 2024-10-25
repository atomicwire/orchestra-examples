# Basic Examples

The basic examples in this folder are targeted to highlight specific functionality offered by the Orchestra plugin.

The basic examples are built upon in [app-examples](../app-examples) to show how to build applications using the Orchestra plugin.

The basic examples contained within this section should be run from the root of this repository using the Gradle wrapper.
Full run commands are given on the README page of each example subproject.

For all examples, the results are output into the subproject Gradle build folder (e.g. `build/orchestra`). For example,
the results for [01-orchestra-hub](./01-orchestra-hub) will be output to `./basic-examples/01-orchestra-hub/build/orchestra/`

The examples included within this section are:

| Example                                | Description                                                                              |
|----------------------------------------|------------------------------------------------------------------------------------------|
| [01-orchestra-hub](./01-orchestra-hub) | Fetches an Orchestra spec from [Orchestra Hub](https://orchestrahub.org)                 |
| [02-markdown](./02-markdown)           | Authoring an Orchestra spec from scratch using Markdown                                  |
| [03-derived](./03-derived)             | Creating a custom spec that re-uses the data dictionary from an existing Orchestra spec  |
| [04-documentation](./04-documentation) | Producing a Message Definition Report for a custom Orchestra spec                        |
| [05-avro-schema](./05-avro-schema)     | Generating an Avro schema for a custom Orchestra spec                                    |
| [06-json-schema](./06-json-schema)     | Generating a JSON schema for a custom Orchestra spec                                     |
| [07-java](./07-java)                   | Generating Java code for use with the Orchestra Java library                             |
| [08-quickfix](./08-quickfix)           | Creates a QuickFIX Data Dictionary from a custom Orchestra spec                          |
| [09-openapi](./09-openapi)             | Produce OpenAPI documentation using an Orchestra spec                                    |
| [10-analyzers](./10-analyzers)         | Generate a report that analyzes an Orchestra spec for style and compatability compliance |

Each of the examples contains a `runExample` Gradle task that is wired to call the Orchestra plugin task that the example
is demonstrating. For example to run [02-markdown](./02-markdown), the command would be 

```
./gradlew :basic-examples:02-markdown:runExample
```
