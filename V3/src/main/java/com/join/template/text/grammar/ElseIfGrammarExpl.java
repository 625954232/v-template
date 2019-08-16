package com.join.template.text.grammar;


import com.join.template.core.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;

import java.util.HashMap;
import java.util.Map;

public class ElseIfGrammarExpl implements GrammarExpl {
    @Override
    public void verifyGrammarAttr(Map<String, String> attr) {

    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttrText(), "判断条件");
        return fields;
    }
}
