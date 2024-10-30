# Markdown

This example shows how to build a new Orchestra spec using Markdown notation. 

As in the 
[previous](../01-orchestra-hub) example, the resulting spec can be used to generate artifacts and build applications.


## Configuration

The Markdown file used to build the spec is configured in the [build.gradle](./build.gradle) file using the `markdown` option.

```groovy
orchestra {
  specification {
    markdown {
      enableSpotless()
    }
  }
}
```

By default, the plugin looks for a Markdown file at `orchestra/specification/<project-name>.md`. 

To specify a Markdown file in a different location, override the default behaviour using the `inputFile` property.

```groovy
orchestra {
  specification {
    markdown {
      inputFile = file("specs/markdown.md")
      enableSpotless()
    }
  }
}
```

In these examples, the plugin uses [Spotless](https://github.com/diffplug/spotless) to automatically format Orchestra Markdown and enforce style.

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:02-markdown:runExample
```

> **Note**: `runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.

## Results

The spec will be output to the Gradle build folder.

```shell
$ ./basic-examples/02-markdown/build/orchestra/specification/02-markdown.xml
```
