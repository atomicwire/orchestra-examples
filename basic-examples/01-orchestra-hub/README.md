# Orchestra Hub

[Orchestra Hub](https://orchestrahub.org) is a repository that allows firms to publish and distribute their specifications to other market participants.

This example shows how to configure the plugin to pull an existing Orchestra spec directly from the  repository. 

Plugin users can then use these specs to create their own [custom specifications](../03-derived) and generate artifacts (such as [code libraries](../07-java) and [schemas](../05-avro-schema)) for use in their [applications](../../app-examples).

## Configuration

The `name` and `version` of the specification is configured in the [build.gradle](./build.gradle) file using the `repository` option.

```groovy
orchestra {
  specification {
    repository orchestraHub(name: 'fix-latest', version: 'ep292')
  }
}
```

## Run

Use the Gradle wrapper to run the example.

```shell
$ ./gradlew :basic-examples:01-orchestra-hub:runExample
```

> **Note**: `runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.


## Results

The spec will be output to the Gradle build folder.

```shell
$ ./basic-examples/01-orchestra-hub/build/orchestra/specification/01-orchestra-hub.xml
```
