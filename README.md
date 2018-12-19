# Spoofax Gradle Plugin

The Spoofax Gradle plugin makes it possible to build Spoofax languages with Gradle.

## Building

To compile and package the plugin:

```
gradlew assemble
```

To publish the plugin to Maven local:

```
gradlew pTML
```

## Usage

Below is an example build script for building a Spoofax language project.

```groovy
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

plugins {
    id 'nl.martijndwars.spoofax' version '1.0-SNAPSHOT'
}

repositories {
    metaborgReleases()
    metaborgSnapshots()
}

spoofax {
  strategoFormat = 'ctree'
  version = '0.1.0-SNAPSHOT'
  overrides = []
}
```

To build your Spoofax language:

```
gradle archiveLanguage
```

If you get a StackOverflowError or OutOfMemoryError create a `gradle.properties` with the following content:

```
org.gradle.jvmargs=-Xms1g -Xmx2g -Xss32m
```

## Recipes

### Multi-project build

If you have a [source dependency](http://www.metaborg.org/en/latest/source/core/manual/concepts.html?highlight=source%20dependency) on another project in a multi-project build:

```groovy
dependencies {
    sourceLanguage project(':projectB')
}
```

### Override versions

To override the version of a source dependency:

```groovy
spoofax {
  overrides = [
    'com.acme:foo.lang:1.2.3'
  ]
}
```

## Internals

The plugin modifies the build in several ways.

### Plugins

The plugin applies the [Base plugin](https://docs.gradle.org/current/userguide/base_plugin.html) to the project.

### Tasks

The plugin defines the following tasks:

* `cleanLanguage`: Clean the Spoofax language. This task is a dependency of `clean`.
* `compileLanguage`: Compile the Spoofax language. This task is a dependency of `assemble`.
* `archiveLanguage`: Archive the Spoofax language. This task depends on `compileLanguage` and is a dependency of `assemble`.
* `checkLanguage`: Check the Spoofax language. This task depends on `archiveLanguage` and is a dependency of `check`.

If `:projectA` has a [project lib dependency](https://docs.gradle.org/current/userguide/multi_project_builds.html#sec:project_jar_dependencies) on `:projectB` in the `sourceLanguage` configuration, then `:projectA:compileLanguage` depends on `:projectB:archiveLanguage`.
This ensures that all project dependencies are built before the depending project is built.

### Configurations

The plugin defines three dependency configurations:

* `compileLanguage`: a configuration holding all the compile dependencies.
* `sourceLanguage`: a configuration holding all the source dependencies.
* `language`: a convenience configuration that extends `compileLanguage` and `sourceLanguage`.

The plugin defines one artifact configuration:

* `spoofaxLanguage`

The `spoofaxLanguage` configuration contains the built Spoofax language (.spoofax-language) artifact.
The `assemble` configuration is made to extend the `spoofaxLanguage` configuration.
