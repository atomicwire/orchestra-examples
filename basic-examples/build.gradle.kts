subprojects {
  tasks.register("runExample") {
    group = "Orchestra Examples"
    description = "Run example ${project.name}"
  }
}
