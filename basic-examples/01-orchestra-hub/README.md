# Orchestra Hub example

This example fetches an Orchestra spec from [Orchestra Hub](https://orchestrahub.org) which can then be used as a basis
for a custom derived spec (see example [03-derived](../03-derived)) or in an [application](../../app-examples).

Later examples also demonstrate other artifacts that can be created from an Orchestra spec, such as [documentation](../04-documentation)
and [schemas](../05-avro-schema).

## Configuration

See [build.gradle](./build.gradle). The configuration references a `repository` using the `orchestraHub` name and version

```groovy
orchestra {
  specification {
    repository orchestraHub(name: 'fix-latest', version: 'ep292')
  }
}
```

## Run

To download the Orchestra spec run

```
./gradlew :basic-examples:01-orchestra-hub:runExample
```
`runExample` is wired to call the `orchestraBuildSpec` task from the Orchestra plugin.


## Results

The spec will be downloaded into the Gradle build folder. e.g.

```
./basic-examples/01-orchestra-hub/build/orchestra/specification/01-orchestra-hub.xml
```
