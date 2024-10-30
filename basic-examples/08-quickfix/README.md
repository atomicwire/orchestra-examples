# QuickFIX

This example shows how to generate a [QuickFIX](https://quickfixengine.org/) Data Dictionary from a custom spec that uses a reference spec obtained from Orchestra Hub. 

## Configuration

The QuickFIX data dictionary generation is activated by the presence of the QuickFIX `dataDictionary` extension in the [build.gradle](./build.gradle) file.

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

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:08-quickfix:runExample
```
> **Note**: `runExample` is wired to call the `orchestraGenerateQuickfixDataDictionary` task from the Orchestra plugin.


## Results

The QuickFIX Data Dictionary will be output to the Gradle build folder.

```shell
$ ./basic-examples/08-quickfix/build/quickfix/08-quickfix.xml
```
