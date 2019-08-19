package com.join.template.text.grammar;


import com.join.template.core.grammar.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class SetGrammarExpl implements GrammarExpl {
    @Override
    public void verifyElement(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (!attr.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请设置值（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attr.containsKey(configuration.getAttName())) {
            throw new TemplateException("请设置值别名（" + configuration.getAttName() + "）：" + original);
        }
    }

    @Override
    public Map<String, String> getElementAttrExpl() {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttVar(), "值");
        fields.put(configuration.getAttName(), "值别名");
        return fields;
    }


}
