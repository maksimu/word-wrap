# word-wrap
<a href="https://github.com/davidmoten/word-wrap/actions/workflows/ci.yml"><img src="https://github.com/davidmoten/word-wrap/actions/workflows/ci.yml/badge.svg"/></a><br/>
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/word-wrap/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.github.davidmoten/word-wrap)<br/>
[![codecov](https://codecov.io/gh/davidmoten/word-wrap/branch/master/graph/badge.svg)](https://codecov.io/gh/davidmoten/word-wrap)

Java library for wrapping text.

**Status**: *published to Maven Central*

Maven site reports are [here](https://davidmoten.github.io/word-wrap) including [javadoc](https://davidmoten.github.io/word-wrap/apidocs/index.html).

## Features
* Designed for use with normally formatted English (spaces after commas, periods for example)
* Can specify custom string width function (for example `FontMetrics.stringWidth`)
* Treats special characters appropriately (don't wrap a comma to the next line for example)
* Conserves leading whitespace on lines
* Optionally insert hyphens
* Easy to use and read builder

## Motivation
@davidmoten needed to render text for display in a PDF using [PDFBox](https://pdfbox.apache.org/) but PDFBox didn't offer word wrapping. He searched for libraries to do it and found Apache *commons-text* and *commons-lang* `WordUtils` but it didn't conserve leading spaces on lines and didn't allow for a customizable string width function. With a bit of luck this library will help those who are on a similar search!

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
String text = "hi there how are you going?";
System.out.println(
  WordWrap.from(text).maxWidth(10).wrap());
```
Output:
```
hi there
how are
you going?
```
More options:

```java
Reader in = ...
Writer out = ...
String newLine = "\r\n";
WordWrap
  .from(in)
  .maxWidth(4.5)
  .newLine("\r\n")
  .includeExtraWordChars("~")
  .excludeExtraWordChars("_")
  .insertHyphens(true)
  .breakWords(true)
  .stringWidth(s -> fontMetrics.stringWidth(s.toString()))
  .wrap(out);
```

Note that the `WordWrap` builder used above is quite flexible and allows you to take input from a `Reader`, `InputStream`, classpath resource, `File`, `String` and has similar options for output.

## Breaking numbers
The default is to be able to break sequences of digits even if `.breakWords(false)` is set. If you don't want sequences of digits broken then set `.extraWordChars("0123456789")`.

## Rules
Rules are [here](https://github.com/davidmoten/word-wrap/blob/master/src/docs/rules.md), generated by [WordWrapTests.java](https://github.com/davidmoten/word-wrap/blob/master/src/test/java/org/davidmoten/text/utils/WordWrapTest.java).

## Rewrapping classic fiction
A good test is to rewrap novels downloaded from Gutenberg (copied from the html form). When you run `mvn test` the output of *The Importance of Being Earnest*, *The Black Gang*, and *Treasure Island* are rewrapped into the directory `target` with line lengths of 20, 20 and 80 respectively. Part of the testing process for this library in addition to lots of unit tests is inspecting the wrapped output from these novels.

From *The Black Gang* wrapped at 20 chars:

```
The wind howled
dismally round a
house standing by
itself almost on the
shores of Barking
Creek. It was the
grey dusk of an
early autumn day,
and the occasional
harsh cry of a sea-
gull rising
discordantly above
the wind alone broke
the silence of the
flat, desolate
waste.

The house seemed
deserted. Every
window was
shuttered; the
garden was uncared
for and a mass of
weeds; the gate
leading on to the
road, apparently
feeling the need of
a deficient top
hinge, propped
itself drunkenly on
what once had been a
flower-bed. A few
gloomy trees swaying
dismally in the wind
surrounded the house
and completed the
picture—one that
would have caused
even the least
imaginative of men
to draw his coat a
little tighter round
him, and feel
thankful that it was
not his fate to live
in such a place.
```

## Performance
For version 0.1.2 JMH benchmarks were added and numerous performance improvements made that gave 10x throughput increase and reduced memory allocation rate to 1/30x.

Benchmark throughput for repeatedly wrapping the Treasure Island fragment at a word length of 80 chars is ~21MB/s on my i5 laptop.

To run benchmarks

```bash
mvn clean install -P benchmark
```

## Build
Use maven:
```bash
maven clean install
```



