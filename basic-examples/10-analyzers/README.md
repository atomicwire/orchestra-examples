# Analyzers example

This example demonstrates validating a FIX-derived specification for compliance with FIX style and tag-value compatibility guidelines.

## Configuration

See [build.gradle](./build.gradle).

The analyzers are enabled via the `validation` extension. To cause validation errors to stop the build, rather than
just logging warnings, use `ValidationRuleSeverity.ERROR`

```groovy
orchestra {
  specification {
    markdown {
      // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }

  validation {
    fixStyleValidationSeverity = ValidationRuleSeverity.WARN
    tagValueCompatibilityValidationSeverity = ValidationRuleSeverity.WARN
  }
}
```


## Run

To run the analyzers for the derived Orchestra specification run

```
./gradlew :basic-examples:10-analyzers:runExample
```
`runExample` is wired to call the `orchestraValidateSpec` task from the Orchestra plugin.


## Results

The high-level feedback will be output to the console.
