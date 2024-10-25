# Orchestra Gradle Plugin and Java Library Examples

> **Warning**: The examples provided in this repository are experimental.

> **Notice**: While these examples are [open source](LICENSE), the libraries loaded from the Atomic Wire Maven
> repository require a license to use. If you have found this repository and would like an evaluation license, please
> contact us via [our website](https://www.atomicwire.io/).

<!-- TOC -->
* [Orchestra Examples](#orchestra-examples)
  * [Introduction](#introduction)
    * [Basic examples](#basic-examples)
    * [Application examples](#application-examples)
  * [Orchestra Plugin](#orchestra-plugin)
<!-- TOC -->

## Introduction

The Orchestra Examples are split into two distinct sections

### Basic examples

The [basic examples](./basic-examples) are targeted to highlight specific functionality offered by the Orchestra plugin.

The basic examples are built upon in [app-examples](./app-examples) to show how to build applications using the Orchestra plugin.

The basic examples should be run from the root of this repository using the Gradle wrapper. Full run commands are given
on the README page of each example subproject.

For all examples, the results are output into the subproject Gradle build folder (e.g. `build/orchestra`). For example,
the results for [01-orchestra-hub](./01-orchestra-hub) will be output to `./basic-examples/01-orchestra-hub/build/orchestra/`

The examples included within the section are:

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
is demonstrating. For example, to run [02-markdown](./02-markdown), the command would be

```
./gradlew :basic-examples:02-markdown:runExample
```

### Application examples

The application examples build on the [basic-examples](./basic-examples) to create runnable applications showcasing
various use-cases.

The examples included within the section are:

| Example                                          | Description                                                             |
|--------------------------------------------------|-------------------------------------------------------------------------|
| [01-basic](./01-basic)                           |                                                                         |
| [02-quickfix](./02-quickfix)                     | Server and client QuickFIX apps using a                                 |
| [03-openapi-springboot](./03-openapi-springboot) | An OpenAPI Spring Boot application that is powered by an Orchestra spec |

The section also includes an [orchestra-specs/fix-44](./orchestra-specs/fix-44) project which produces an Orchestra
spec that is used by the applications above.


## Orchestra Plugin

All examples utilise the Orchestra Plugin.

[settings.gradle](./settings.gradle) contains the complete setup to run these examples, however the Orchestra Gradle
Plugin settings of interest are:

```groovy
pluginManagement {
  plugins {
    id 'io.atomicwire.gradle.orchestra' version '<version>'
  }

  repositories {
    gradlePluginPortal()

    maven {
      name = 'atomicwire'
      url = uri('https://maven.atomicwire.dev/external')
    }
  }
}

```

Then to use the plugin in a subproject add the following to `build.gradle`:

```
plugins {
  id 'io.atomicwire.gradle.orchestra'
}
```
