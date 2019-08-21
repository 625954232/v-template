package com.join.template.core.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Map<String, String> findAttr(CharSequence source) {
        Map<String, String> result = new HashMap<>();
        String sepReg = " (.*?)=['\"](.*?)['\"]";
        Matcher matcher = Pattern.compile(sepReg).matcher(source);
        while (matcher.find()) {
            result.put(matcher.group(1), matcher.group(2).replaceAll("'", "").replaceAll("\"", ""));
        }
        return result;
    }

    public static <T> T returnOrDefault(T o, T defaultValue) {
        if (o == null || "null".equals(o.toString()) || o.toString().length() == 0) {
            return defaultValue;
        }
        return o;
    }
}
