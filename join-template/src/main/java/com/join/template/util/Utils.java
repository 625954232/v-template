package com.join.template.util;

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
}
