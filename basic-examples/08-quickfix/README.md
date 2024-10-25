# QuickFIX example

This example demonstrates creating a custom spec that re-uses the data dictionary from an existing spec available on
Orchestra Hub. A [QuickFIX](https://quickfixengine.org/) Data Dictionary is then created from the custom spec.

## Configuration

See [build.gradle](./build.gradle).

The QuickFIX data dictionary generation is activated by the presence of the QuickFIX `dataDictionary` extension.

```groovy
orchestra {
  specification {
    markdown {
      // By default, the plugin looks for your Markdown file at `orchestra/specification/<project-name>.md`
      reference orchestraHub(name: 'fix-latest', version: 'ep292')
    }
  }

  quickfix {
    dataDictionary {}
  }
}
```

## Run

To generate an Java code from the Markdown file run

```
./gradlew :basic-examples:08-quickfix:runExample
```
`runExample` is wired to call the `orchestraGenerateQuickfixDataDictionary` task from the Orchestra plugin.


## Results

The Java will be generated in the Gradle build folder. e.g.

```
./basic-examples/08-quickfix/build/quickfix/08-quickfix.xml
```
