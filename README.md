# word-wrap
<a href="https://travis-ci.org/davidmoten/word-wrap"><img src="https://travis-ci.org/davidmoten/word-wrap.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/word-wrap/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/word-wrap)<br/>
[![codecov](https://codecov.io/gh/davidmoten/word-wrap/branch/master/graph/badge.svg)](https://codecov.io/gh/davidmoten/word-wrap)

Java library for wrapping text.

## Features
* Can specify custom string width function (for example `FontMetrics.stringWidth`)
* Treats special characters appropriately (don't wrap a comma to the next line for example)
* Conserves leading whitespace on lines

## Getting started
Add this to your pom:

```xml
<dependency>
  <groupId>com.github.davidmoten</groupId>
  <artifactId>word-wrap</artifactId>
  <version>VERSION_HERE</version>
</dependency>
```

## Usage
```java
String text="hi there how are you going?";
System.out.println(Text.wordWrap(text, 10));
```
Output:
```
hi there
how are
you going?
```
Stream support:

```java
Reader in = ...
Writer out = ...
String newLine = "\r\n";
Text.wordWrap(in, out, maxWidth, newLine, s -> s.length());
```

## Build
Use maven:
```bash
maven clean install
```

