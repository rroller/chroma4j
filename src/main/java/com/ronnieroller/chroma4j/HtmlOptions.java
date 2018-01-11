package com.ronnieroller.chroma4j;

public class HtmlOptions {
    private final int tabWidth;
    private final int baseLineNumber;
    private final String classPrefix;
    private final boolean lineNumbersInTable;
    private final boolean lineNumbers;
    private final boolean standalone;
    private final boolean classes;
    private final boolean htmlStyles;

    public HtmlOptions(int tabWidth, int baseLineNumber, String classPrefix, boolean lineNumbersInTable, boolean lineNumbers, boolean standalone,
            boolean classes, boolean htmlStyles) {
        this.tabWidth = tabWidth;
        this.baseLineNumber = baseLineNumber;
        this.classPrefix = classPrefix;
        this.lineNumbersInTable = lineNumbersInTable;
        this.lineNumbers = lineNumbers;
        this.standalone = standalone;
        this.classes = classes;
        this.htmlStyles = htmlStyles;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public int getBaseLineNumber() {
        return baseLineNumber;
    }

    public String getClassPrefix() {
        return classPrefix;
    }

    public boolean isLineNumbersInTable() {
        return lineNumbersInTable;
    }

    public boolean isLineNumbers() {
        return lineNumbers;
    }

    public boolean isStandalone() {
        return standalone;
    }

    public boolean isClasses() {
        return classes;
    }

    public boolean isHtmlStyles() {
        return htmlStyles;
    }

    public static class Builder {
        private int tabWidth = 4;
        private int baseLineNumber = 1;
        private String classPrefix = "";
        private boolean lineNumbersInTable;
        private boolean lineNumbers;
        private boolean standalone = true;
        private boolean classes = true;
        private boolean htmlStyles;

        public HtmlOptions build() {
            return new HtmlOptions(tabWidth, baseLineNumber, classPrefix, lineNumbersInTable, lineNumbers, standalone, classes, htmlStyles);
        }

        /**
         * Sets the number of characters for a tab. Defaults to 8.
         */
        public Builder withTabWidth(int tabWidth) {
            this.tabWidth = tabWidth;
            return this;
        }

        /**
         * Sets the initial number to start line numbering at. Defaults to 1.
         */
        public Builder withBaseLineNumber(int baseLineNumber) {
            this.baseLineNumber = baseLineNumber;
            return this;
        }

        /**
         * Prefixes the css classes with the supplied prefix
         */
        public Builder withClassPrefix(String classPrefix) {
            this.classPrefix = classPrefix;
            return this;
        }

        /**
         * LineNumbersInTable will, when combined with WithLineNumbers, separate the line numbers and code in table
         * td's, which make them copy-and-paste friendly.
         */
        public Builder withLineNumbersInTable(boolean lineNumbersInTable) {
            this.lineNumbersInTable = lineNumbersInTable;
            return this;
        }

        /**
         * If true, includes line numbers
         */
        public Builder withLineNumbers(boolean lineNumbers) {
            this.lineNumbers = lineNumbers;
            return this;
        }

        /**
         * If true, returns a full html document... e.g. <html><body>...
         * Otherwise, only returns the highlighted source.
         */
        public Builder withStandalone(boolean standalone) {
            this.standalone = standalone;
            return this;
        }

        /**
         * If true, writes the style inline. e.g. <span style='color:red'>
         */
        public Builder withClasses(boolean classes) {
            this.classes = classes;
            return this;
        }

        /**
         * If true, only returns CSS (doe not include the HTML document)
         */
        public Builder withHtmlStyles(boolean htmlStyles) {
            this.htmlStyles = htmlStyles;
            return this;
        }

    }
}
