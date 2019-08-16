package com.join.template.text.grammar;


import com.join.template.core.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class IfGrammarExpl implements GrammarExpl {
    @Override
    public void verifyGrammarAttr(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (endElement && !attr.containsKey(configuration.getAttrText())) {
            throw new TemplateException("请设置判断条件（" + configuration.getAttrText() + "）：" + original);
        }
    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttrText(), "判断条件");
        return fields;
    }
}
