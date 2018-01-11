# About
This is a JNA wrapper around the Chroma syntax highlighter. It exposes two highlight methods.

Read the Chroma docs on how to use Chroma https://github.com/alecthomas/chroma.

This exists because there's not a good server side syntax highlighter in Java (that I could find). Chroma is fast and follows the popular pygments python library.

# Building
```bash
go build -o chroma.so -buildmode=c-shared chroma.go
mvn clean install
```
# Example use
```java
String source = "public class MyClass { public void hello() {} }";
String lexer = "java";
String style = "monokai";
HtmlOptions options = HtmlOptions.builder()
	.withLineNumbers(true)
	.build();

String result = Chroma.highlightAsHtml(source, lexer, style, options);
```

# TODO/Not done
* This is not exported to Maven Central -- You'll need to build it yourself
* I haven't tested it beyond a Mac
* Expose more methods from the Go Chroma library
