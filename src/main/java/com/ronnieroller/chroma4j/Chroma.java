package com.ronnieroller.chroma4j;

import com.sun.jna.Native;

public class Chroma {
    private static final ChromaJnaInterface lib = getSo();

    public static String highlight(String source, String lexer, String formatter, String style) {
        final GoString sourceValue = new GoString(source);
        final GoString lexerValue = new GoString(lexer);
        final GoString formatterValue = new GoString(formatter);
        final GoString styleValue = new GoString(style);

        final GoString result = lib.highlight(sourceValue, lexerValue, formatterValue, styleValue);
        return result.toString();
    }

    /**
     * Highlights the supplied source code
     *
     * @param source
     *            The source to highlight
     * @param lexer
     *            The type of lexer. Examples: java, python, go, css, html, ...
     * @param style
     *            The style. Examples: emacs, fruity, github, igor, native, etc...
     * @param options
     *            The HTML options such as number of spaces, line numbers, etc...
     * @return the highlighted source as markedup HTML
     */
    public static String highlightAsHtml(String source, String lexer, String style, HtmlOptions options) {
        final GoString sourceValue = new GoString(source);
        final GoString lexerValue = new GoString(lexer);
        final GoString styleValue = new GoString(style);

        int htmlTabWidthFlag = options.getTabWidth();
        int htmlBaseLineFlag = options.getBaseLineNumber();
        GoString htmlPrefixFlag = new GoString(options.getClassPrefix());
        boolean htmlLinesTableFlag = options.isLineNumbersInTable();
        boolean htmlLinesFlag = options.isLineNumbers();
        boolean htmlOnlyFlag = options.isStandalone();
        boolean htmlInlineStyleFlag = !options.isClasses();
        boolean htmlStylesFlag = options.isHtmlStyles();

        final GoString result = lib.highlightAsHtml(sourceValue, lexerValue, styleValue, htmlTabWidthFlag, htmlBaseLineFlag, htmlPrefixFlag, htmlLinesTableFlag,
                htmlLinesFlag, htmlOnlyFlag, htmlInlineStyleFlag, htmlStylesFlag);

        return result.toString();
    }

    private static ChromaJnaInterface getSo() {
        try {
            return (ChromaJnaInterface) Native.loadLibrary("/chroma.so", ChromaJnaInterface.class);
        } catch (Exception e) {
            // This one is needed for unit tests
            return (ChromaJnaInterface) Native.loadLibrary("chroma.so", ChromaJnaInterface.class);
        }
    }

}