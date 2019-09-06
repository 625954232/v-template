package com.join.template.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static <T> T returnOrDefault(T o, T defaultValue) {
        if (o == null || "null".equals(o.toString()) || o.toString().length() == 0) {
            return defaultValue;
        }
        return o;
    }
}
