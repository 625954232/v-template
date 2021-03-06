package com.join.template.html.grammar;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.grammar.GrammarAttr;
import com.join.template.core.factory.JoinFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CAOYOU
 * @Title: DefaultExprAttr
 * @date 2019/8/2611:49
 */
public class TemplateExprAttr implements GrammarAttr {

    private Configuration configuration;
    private JoinFactory joinFactory;

    @Override
    public GrammarAttr setJoinFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        return null;
    }

    @Override
    public Map<String, String> findAttribute(CharSequence attr) {
        Map<String, String> result = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder(" (.*?)");
        stringBuilder.append(this.escape(this.configuration.getAttStart()));
        stringBuilder.append("(.*?)");
        stringBuilder.append(this.escape(this.configuration.getAttEnd()));

        Matcher matcher = Pattern.compile(stringBuilder.toString()).matcher(attr);
        while (matcher.find()) {
            result.put(matcher.group(1), matcher.group(2));
        }
        return result;
    }

    @Override
    public String genAttribute(Map<String, String> attr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(this.configuration.getAttStart());
            stringBuilder.append(entry.getValue());
            stringBuilder.append(this.configuration.getAttEnd());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    private String escape(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int length = stringBuilder.length();
        int start = 0;
        while (start < length) {
            if (stringBuilder.charAt(start) == '(' || stringBuilder.charAt(start) == ')') {
                stringBuilder.insert(start, "\\");
                start += 1;
            }
            start++;
        }
        return stringBuilder.toString();
    }

}
