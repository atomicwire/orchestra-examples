# Analyzers

This example shows how to validate a custom FIX specification to ensure it complies with FIX style and tag-value compatibility guidelines.

## Configuration

The analyzers are enabled via the `validation` extension in the [build.gradle](./build.gradle) file. 

To force validation errors to stop the build (rather than just logging warnings) use `ValidationRuleSeverity.ERROR`.

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

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:10-analyzers:runExample
```
> **Note**: `runExample` is wired to call the `orchestraValidateSpec` task from the Orchestra plugin.


## Results

The analyzer results will be displayed in the console.
