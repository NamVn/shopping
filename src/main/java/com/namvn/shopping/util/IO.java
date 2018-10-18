package com.namvn.shopping.util;

import java.util.Arrays;
import java.util.List;

public class IO {
    public List<String> cutWhiteSpaces(String str) {
        String[] splited = str.trim().split("\\s+");
        return Arrays.asList(splited);
    }
}
