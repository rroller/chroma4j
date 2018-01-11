package main

// Building:
// go build -o chroma.so -buildmode=c-shared chroma.go

import "C"

import (
	"github.com/alecthomas/chroma/formatters/html"
	"github.com/alecthomas/chroma/lexers"
	"github.com/alecthomas/chroma/quick"
	"github.com/alecthomas/chroma/styles"
	"bytes"
	"github.com/alecthomas/chroma"
)

func main() {

}

// Highlight some text.
//
// Lexer, formatter and style may be empty, in which case a best-effort is made.
//export highlight
func highlight(source, lexer, formatter, style string) *C.char {
	writer := new(bytes.Buffer)
	quick.Highlight(writer, source, lexer, formatter, style)

	return C.CString(writer.String())
}

// Highlight some text.
//
//export highlightAsHtml
func highlightAsHtml(source, lexer, style string, htmlTabWidthFlag int, htmlBaseLineFlag int,
	htmlPrefixFlag string, htmlLinesTableFlag bool, htmlLinesFlag bool, htmlOnlyFlag bool,
	htmlInlineStyleFlag bool, htmlStylesFlag bool) *C.char {

	// Writer
	w := new(bytes.Buffer)

	// Lexer
	l := lexers.Get(lexer)
	if l == nil {
		l = lexers.Analyse(source)
	}
	if l == nil {
		l = lexers.Fallback
	}
	l = chroma.Coalesce(l)


	// Determine style.
	s := styles.Get(style)
	if s == nil {
		s = styles.Fallback
	}

	// Formatter options
	options := []html.Option{
		html.TabWidth(htmlTabWidthFlag),
		html.BaseLineNumber(htmlBaseLineFlag),
	}
	if htmlPrefixFlag != "" {
		options = append(options, html.ClassPrefix(htmlPrefixFlag))
	}

	if htmlStylesFlag {
		// Dump styles.
		formatter := html.New(html.WithClasses())
		formatter.WriteCSS(w, s)
		return C.CString(w.String())
	}
	if !htmlInlineStyleFlag {
		options = append(options, html.WithClasses())
	}
	if !htmlOnlyFlag {
		options = append(options, html.Standalone())
	}
	if htmlLinesFlag {
		options = append(options, html.WithLineNumbers())
	}
	if htmlLinesTableFlag {
		options = append(options, html.LineNumbersInTable())
	}

	f := html.New(options...)

	it, err := l.Tokenise(nil, source)
	if err != nil {
		return nil
	}

	f.Format(w, s, it)
	return C.CString(w.String())
}
