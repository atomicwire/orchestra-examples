# Custom specifications

This example shows how to create a custom spec using elements from a reference spec published on [Orchestra Hub](https://orchestrahub.org).

In this example, the spec is derived from [FIX Latest](https://orchestrahub.org/-/fix-latest) and contains a single custom message using elements from the FIX Latest data dictionary.

## Configuration

The reference spec is specified in the [build.gradle](./build.gradle) file using the `reference` parameter. The [Markdown file](./orchestra/specification/03-derived.md) for the custom spec is in the default location so does not need to be configured.

```groovy
orchestra {
  specification {
    markdown {
      // By default, the plugin looks for a Markdown file at `orchestra/specification/<project-name>.md`
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:03-derived:runExample
```

> **Note**: `runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.


## Results

The spec will be output to the Gradle build folder.

```shell
$ ./basic-examples/03-derived/build/orchestra/specification/03-derived.xml
```
