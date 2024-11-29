# Document Generation

This example demonstrates how to generate documentation from an Orchestra specification.

This feature allows users to adopt Orchestra as their primary repository by eliminating the need to maintain separate PDF documents.

By default, the plugin generates Message Definition Reports (MDRs) for a specified message set following the documentation structure used by [ISO 20022](https://www.iso20022.org/iso-20022-message-definitions). 

An MDR contains two main sections:
* **List of Messages**: A detailed description of each message, including its function and contents.
* **Data Dictionary**: A description of the components, fields, codesets, and datatypes used in the messages. 

All generated documentation is designed to be immutable. Changes should be captured directly in the Orchestra specification (or Markdown file) as the master source.

The available output formats are PDF and EPUB. DOCX is not supported due to its editable nature.

This example extends the derived specification from the [previous](../03-derived) example, incorporating additional [Dublin Core](https://www.dublincore.org/specifications/dublin-core/) terms to ensure metadata fields are correctly populated in the output document.

## Configuration

The plugin uses [Pandoc](https://pandoc.org) to generate MDRs from an Orchestra specification.

To enable this, include the `com.bmuschko.docker-remote-api` plugin in the [build.gradle](./build.gradle) file. This allows the use of a Pandoc Docker image to run Pandoc and install the necessary LaTeX plugins.

```groovy
plugins {
  id 'io.atomicwire.gradle.orchestra'
  id 'com.bmuschko.docker-remote-api' // Gradle plugin for managing Docker images and containers
}
```

Include the `messageDefinitionReport` extension in the [build.gradle](./build.gradle) file to enable document generation. 

```groovy
orchestra {
  specification {
    markdown {
      reference orchestraHub(name: 'fix-4.4', version: '4.4')
    }
  }

  documentation {
    // By default, the plugin generates documentation in Portable Document Format (PDF)
    messageDefinitionReport {}
  }
}
```

Include the `epub` extension to also enable document generation in Electronic Publication (EPUB) format.

```groovy
orchestra {
  specification {
    markdown {
      reference orchestraHub(name: 'fix-4.4', version: '4.4')
    }
  }

  documentation {
    messageDefinitionReport {
      // Enable document generation in Electronic Publication (EPUB) format
      epub {}
    }
  }
}
```


## Run

> **Important**: This extension requires Docker to be installed with the Docker daemon running on your system. Please make sure:
> * Docker is installed and correctly configured.
> * The Docker daemon is active before running the example.

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

The MDR file(s) will be output to the Gradle build folder.

```shell
$ ./basic-examples/04-documentation/build/orchestra/documentation/mdr/04-documentation.pdf
```

### Metadata

The plugin utilises the [Dublin Core](https://www.dublincore.org/specifications/dublin-core/) terms defined within the `fixr:metadata` element of the Orchestra specification to configure document metadata.

For instance, the [Markdown file](./orchestra/specification/04-documentation.md) in this project generates the following XML.

```xml
  <fixr:metadata>
    <dcterms:title>Derived Specification Example</dcterms:title>
    <dcterms:creator>Atomic Wire Technology Limited</dcterms:creator>
    <dcterms:created>2024-11-01</dcterms:created>
    <dcterms:conformsTo>[Orchestra v1-0-STANDARD](https://www.fixtrading.org/packages/fix-orchestra-technical-specification-v1-0/)</dcterms:conformsTo>
    <dcterms:abstract>A derived specification based on FIX 4.4 as the reference standard. This specification features a single custom FIX message incorporating user-defined fields not part of the FIX standard, along with deprecated fields from earlier versions of FIX that are needed for backward compatibility.</dcterms:abstract>
    <dcterms:source>[FIX.4.4](https://orchestrahub.org/-/fix-4.4)</dcterms:source>
    <dcterms:rights>Copyright 2024 Atomic Wire Technology Limited</dcterms:rights>
    <dcterms:alternative>Example of Orchestra document generation</dcterms:alternative>
    <dcterms:subject>Orchestra, FIX, Documentation</dcterms:subject>
  </fixr:metadata>
```

The table below demonstrates how this metadata is mapped in a generated PDF document. For instance, the `dcterms:creator` element is used to populate the `Author` field in the PDF metadata.

| Name                         | Value                                                                                                                                                                                                                                                                                             |
|:-----------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| File Size                    | 242 KiB                                                                                                                                                                                                                                                                                           |
| File Modification Date/Time  | 2024:11:19 15:50:01+00:00                                                                                                                                                                                                                                                                         |
| File Access Date/Time        | 2024:11:19 15:50:00+00:00                                                                                                                                                                                                                                                                         |
| File Inode Change Date/Time  | 2024:11:19 15:50:01+00:00                                                                                                                                                                                                                                                                         |
| File Permissions             | -rw-------                                                                                                                                                                                                                                                                                        |
| File Type                    | PDF                                                                                                                                                                                                                                                                                               |
| File Type Extension          | pdf                                                                                                                                                                                                                                                                                               |
| MIME Type                    | application/pdf                                                                                                                                                                                                                                                                                   |
| PDF Version                  | 1.5                                                                                                                                                                                                                                                                                               |
| Linearized                   | No                                                                                                                                                                                                                                                                                                |
| Page Count                   | 33                                                                                                                                                                                                                                                                                                |
| Acceptance Date              | 2024-11-19                                                                                                                                                                                                                                                                                        |
| Subtitle                     | Example of Orchestra document generation                                                                                                                                                                                                                                                          |
| Page Mode                    | UseOutlines                                                                                                                                                                                                                                                                                       |
| Producer                     | pdfTeX-1.40.26                                                                                                                                                                                                                                                                                    |
| Author                       | Atomic Wire Technology Limited                                                                                                                                                                                                                                                                    |
| Title                        | Derived Specification Example                                                                                                                                                                                                                                                                     |
| Subject                      | A derived specification based on FIX 4.4 as the reference standard. This specification features a single custom FIX message incorporating user-defined fields not part of the FIX standard, along with deprecated fields from earlier versions of FIX that are needed for backward compatibility. |
| Creator                      | LaTeX via pandoc with the Eisvogel template                                                                                                                                                                                                                                                       |
| Create Date                  | 2024-11-010000                                                                                                                                                                                                                                                                                    |
| Keywords                     | Orchestra, FIX, Documentation                                                                                                                                                                                                                                                                     |
| Modify Date                  | 2024:11:19 15:49:38Z                                                                                                                                                                                                                                                                              |
| Trapped                      | False                                                                                                                                                                                                                                                                                             |
| PTEX Fullbanner              | This is pdfTeX, Version 3.141592653-2.6-1.40.26 (TeX Live 2024) kpathsea version 6.4.0                                                                                                                                                                                                            |
