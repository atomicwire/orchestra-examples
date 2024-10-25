# Markdown example

This example demonstrates authoring an Orchestra spec from scratch using Markdown. Like the 
[previous](../01-orchestra-hub) example, this spec can be used in applications and have other artifacts created from it.


## Configuration

See [build.gradle](./build.gradle). 

```groovy
orchestra {
  specification {
    markdown {
      enableSpotless()
    }
  }
}
```

By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`. If you wish to 
have the Markdown file at a different location, you can use the `inputFile` property. e.g.

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

In the examples above, we also use [Spotless](https://github.com/diffplug/spotless) to automatically format Orchestra 
Markdown and enforce style.

## Run

To generate an Orchestra spec from the Markdown file run

```
./gradlew :basic-examples:02-markdown:runExample
```
`runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.

## Results 

The spec will be generated in the Gradle build folder. e.g.

```
./basic-examples/02-markdown/build/orchestra/specification/02-markdown.xml
```
