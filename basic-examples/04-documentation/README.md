# Documentation

This example shows how to generate a Message Definition Report (MDR) from a custom Orchestra spec.

An MDR details the messages, components, fields and code sets defined in the Orchestra spec. By default, the plugin follows the documentation structure used by [ISO 20022](https://www.iso20022.org/iso-20022-message-definitions).


> **Notice**: This extension requires Docker to be installed with the Docker daemon running on your system. Please make sure that:
> * Docker is installed and correctly configured.
> * The Docker daemon is active before running the example.

## Configuration

MDRs are generated using [Pandoc](https://pandoc.org). The plugin uses a Pandoc Docker image to run Pandoc and install relevant LaTeX plugins. 

The `com.bmuschko.docker-remote-api` Gradle plugin must be enabled to run the MDR generation tasks.

```groovy
plugins {
  id 'com.bmuschko.docker-remote-api'
  id 'io.atomicwire.gradle.orchestra'
}
```

Generation of an MDR from an Orchestra spec is configured in the [build.gradle](./build.gradle) file using the `messageDefinitionReport` extension.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}
  }

  documentation {
    messageDefinitionReport {}
  }
}
```

Support for Portable Document Format (PDF) is enabled by default. To also enable support for the Electronic Publication (EPUB) format, add the `epub` extension.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}
  }

  documentation {
    messageDefinitionReport {
      epub {}
    }
  }
}
```
You can then run the `orchestraGenerateMessageDefinitionReportPdf` or `orchestraGenerateMessageDefinitionReportEpub`
Gradle tasks.

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:04-documentation:runExample
```

> **Note**: `runExample` is wired to call the `orchestraGenerateMessageDefinitionReportPdf` task from the Orchestra plugin.


To generate an ePub file, the [build.gradle](./build.gradle) configuration must be modified as shown above. You can then use the Gradle wrapper as follows:

```shell
$ ./gradlew :basic-examples:04-documentation:orchestraGenerateMessageDefinitionReportEpub
```

## Results

The MDR files will be output to the Gradle build folder.

```shell
$ ./basic-examples/04-documentation/build/orchestra/documentation/mdr/04-documentation.pdf
```
