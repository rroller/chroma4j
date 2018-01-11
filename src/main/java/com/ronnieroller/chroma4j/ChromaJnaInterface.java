package com.ronnieroller.chroma4j;

import com.sun.jna.Library;

// How to build the chroma.go file:
// go build -o chroma.so -buildmode=c-shared chroma.go
interface ChromaJnaInterface extends Library {

    public GoString highlight(GoString source, GoString lexer, GoString formatter, GoString style);

    public GoString highlightAsHtml(GoString source, GoString lexer, GoString style, long htmlTabWidthFlag,
            long htmlBaseLineFlag, GoString htmlPrefixFlag, boolean htmlLinesTableFlag, boolean htmlLinesFlag,
            boolean htmlOnlyFlag, boolean htmlInlineStyleFlag, boolean htmlStylesFlag);

}
