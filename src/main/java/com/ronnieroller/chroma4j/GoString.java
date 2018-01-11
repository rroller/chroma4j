package com.ronnieroller.chroma4j;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

// GoString class maps to C type struct { const char *p; GoInt n; }
public class GoString extends Structure implements Structure.ByValue {
    public String p;
    public long n;

    public GoString() {}

    public GoString(String str) {
        this.p = str;
        this.n = this.p.length();
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] { "p", "n" });
    }

    @Override
    public String toString() {
        return this.p;
    }

}