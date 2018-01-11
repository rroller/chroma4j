package com.ronnieroller.chroma4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class ChromaTest {

    /**
     * When running in an IDE you'll typically need to
     * 1: Build the chroma.so file with go
     * 2: Build this package (mvn clean install)
     * 3: Then run the tests in the IDE.
     * Generally they'll always work on the command line but it's a little funky in the IDE
     */

    @Test
    public void testHighlight() throws IOException {
        String source = fileToString("test_data/ImmutableSortedSet.java");
        String lexer = "java";
        String formatter = "html";
        String style = "monokai";
        String result = Chroma.highlight(source, lexer, formatter, style);
        Assert.assertTrue(result.contains("<span class=\"kd\">public</span> <span class=\"kd\">static</span> <span class=\"o\">&lt;</span>"));
    }

    @Test
    public void testHighlightAsHtml() throws IOException {
        String source = fileToString("test_data/ImmutableSortedSet.java");
        String lexer = "java";
        String style = "monokai";
        HtmlOptions options = HtmlOptions.builder()
                .withLineNumbers(true)
                .build();

        String result = Chroma.highlightAsHtml(source, lexer, style, options);
        Assert.assertTrue(result.startsWith("<pre class=\"chroma\"><span class=\"cm\">/*"));
        Assert.assertTrue(result.contains("<span class=\"kd\">public</span> <span class=\"kd\">static</span> <span class=\"o\">&lt;</span>"));
    }

    private String fileToString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
