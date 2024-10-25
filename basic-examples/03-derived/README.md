# Derived example

This example demonstrates creating a custom spec that re-uses the data dictionary from an existing spec available on
[Orchestra Hub](https://orchestrahub.org).

In this case, the spec derives from [FIX Latest](https://orchestrahub.org/-/fix-latest), defining a single custom 
message based on elements from the FIX Latest data dictionary.


## Configuration

See [build.gradle](./build.gradle). 

The existing spec to derive from is specified via the `reference` parameter. The [Markdown file](./orchestra/specification/03-derived.md) is in the default location so does not need to be specified.

```groovy
orchestra {
  specification {
    markdown {
      // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }
}
```

## Run

To generate a derived Orchestra spec from the Markdown file run

```
./gradlew :basic-examples:03-derived:runExample
```

`runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.


## Results 

The spec will be generated in the Gradle build folder. e.g.

```
./basic-examples/03-derived/build/orchestra/specification/03-derived.xml
```
