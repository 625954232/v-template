package com.join.template.text.grammar;


import com.join.template.core.GrammarExpl;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class ListGrammarExpl implements GrammarExpl {

    @Override
    public void verifyGrammarAttr(String original, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (!original.startsWith(configuration.getExprFirstBegin())) {
            return;
        }
        if (!attr.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attr.containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + original);
        }
    }

    @Override
    public Map<String, String> getGrammarAttr() {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> fields = new HashMap<>();
        fields.put(configuration.getAttVar(), "参数名称");
        fields.put(configuration.getAttItem(), "单项别名");
        fields.put(configuration.getAttOpen(), "语句开始符");
        fields.put(configuration.getAttClose(), "语句结束符");
        fields.put(configuration.getAttSseparator(), "分隔符");
        fields.put(configuration.getAttIndex(), "索引");
        return fields;
    }
}
