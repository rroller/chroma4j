package com.ronnieroller.chroma4j;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

// GoSlice class maps to C type struct { void *data; GoInt len; GoInt cap; }
public class GoSlice extends Structure {
    public static class ByValue extends GoSlice implements Structure.ByValue {}

    public static class ByReference extends GoSlice implements Structure.ByReference {}

    public Pointer data;
    public long len;
    public long cap;

    protected List<String> getFieldOrder() {
        return Arrays.asList(new String[] { "data", "len", "cap" });
    }
}
