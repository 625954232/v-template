package com.join.template.text.grammar;


import com.join.template.core.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class GetGrammarExpl implements GrammarExpl {

    @Override
    public void verifyGrammarAttr(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (!attr.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请设置需要获取的值别名（" + configuration.getAttVar() + "）：" + original);
        }
    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttVar(), "值别名");
        fields.put(configuration.getAttrFormat(), "时间类型格式化表达式");
        return fields;
    }
}
