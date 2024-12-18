# Verification

This example demonstrates how to verify an Orchestra specification.

The plugin provides different types of validation including syntax and semantic validation. It also enables configurable encoding compatibility checks for multiple data encodings. 

| **Type**                   | **Description**                                                                                                                                                                                                                                                                                                                                                                                          |     **Usage**     |
|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-----------------:|
| **Syntax Validation**      | Ensures the specification is well-formed and adheres to the Orchestra XML Schema Definition (XSD), validating the format and enforcing required element and attribute constraints.                                                                                                                                                                                                                       |     All specs     |
| **Integrity Checking**     | Verifies the consistency of semantic information, such as element references and uniqueness of element names and identifiers. Also ensures scenarios only include elements present in their referenced base scenario ("subsetting").                                                                                                                                                                     |     All specs     |
| **Encoding Compatibility** | Validates that a specification is compatible with a specific data encoding (e.g., FIX tagvalue, JSON, XML, binary formats) by ensuring all required elements are defined and can be serialized and deserialized according to the encoding rules. This includes verifying field sizes, datatype mappings, and encoding-specific constraints (e.g., byte order, delimiters, or scenario element ordering). |   Per encoding    |
| **Metadata Validation**    | Validates the completeness and consistency of [Dublin Core](https://www.dublincore.org/specifications/dublin-core/) terms. Checks are context-dependent; for example, derived specifications must include metadata for a reference specification (`dcterms:source`).                                                                                                                                     | Context-dependent | 

The plugin also supports user-defined extensions for custom validation logic.

The example uses a derived specification with multiple errors to demonstrate the different types of validation, including encoding compatibility checks for FIX tagvalue.

## Configuration

Include the `validation` extension in the [build.gradle](./build.gradle) file to enable validation.

```groovy
orchestra {
  specification {
    markdown {
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }

  // Validation is enabled by default; shown here for completeness
  validation {}
}
```

> **Note**: Syntax validation and integrity checking are enabled by default, even if the `validation` extension is not included. This ensures compatibility with other plugin features that rely on a valid specification. Unless explicitly disabled by setting the `enabled` property to `false`, the plugin will output error messages to the console for syntax and semantic issues.
>
> ```groovy
> validation {
>   enabled = false
> }
> ```

### Encoding compatibility

To enable encoding compatibility checking for FIX tagvalue, use the `fixCompatible` extension.

```groovy
orchestra {
  specification {
    markdown {
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }

  // Enable encoding compatibility checking for FIX tagvalue
  validation {
    fixCompatible {}
  }
}
```

> **Note**: This is an experimental feature and may undergo changes as compatibility checks for additional encodings are introduced.

### Metadata checking

To enable metadata checking for document generation, use the `messageDefinitionReport` extension. Refer to the [documentation](../04-documentation) example to configure the Docker plugin.  

```groovy
orchestra {
  specification {
    markdown {
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }

  // Validation is enabled by default; shown here for completeness
  validation {}

  // Enable metadata checking for document generation
  documentation {
    messageDefinitionReport {}
  }
}
```


## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:09-verification:runExample
```
> **Note**: `runExample` is wired to call the `orchestraValidateSpec` task from the Orchestra plugin.


## Results

The verification results will be displayed in the console.
