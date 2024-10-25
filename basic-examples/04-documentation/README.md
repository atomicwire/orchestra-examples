# Documentation example

This example demonstrates generating a Message Definition Report for a custom Orchestra spec.


## Configuration

See [build.gradle](./build.gradle). 

The Message Definition Report is generated via Pandoc. 

The plugin runs a Pandoc Docker image, installing relevant LaTeX plugins. Using Docker requires that the 
`com.bmuschko.docker-remote-api` plugin is enabled.

```groovy
plugins {
  id 'com.bmuschko.docker-remote-api'
  id 'io.atomicwire.gradle.orchestra'
}
```

To generate a Message Definition Report detailing the Messages, Components, Fields and CodeSets defined in the Orchestra
spec. The `messageDefinitionReport` extension can be used. 

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

Only `pdf` is enabled by default, if you wish to generate `epub` too, enable the `epub` extension. e.g.

```groovy
orchestra {
  specification {
    // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
    markdown {}
  }

  documentation {
    messageDefinitionReport {
      epub { }
    }
  }
}
```
You can then run the `orchestraGenerateMessageDefinitionReportPdf` or `orchestraGenerateMessageDefinitionReportEpub`
Gradle tasks.

## Run

To generate a PDF Message Definition report from the Markdown file run

```
./gradlew :basic-examples:04-documentation:runExample
```
`runExample` is wired to call the `orchestraGenerateMessageDefinitionReportPdf` task from the Orchestra plugin.


To generate an ePub, the [build.gradle](./build.gradle) can be modified as described above, then run  

```
./gradlew :basic-examples:04-documentation:orchestraGenerateMessageDefinitionReportEpub
```

## Results 

The Message Definition Report will be generated in the Gradle build folder. e.g.

```
./basic-examples/04-documentation/build/orchestra/documentation/mdr/04-documentation.pdf
```
