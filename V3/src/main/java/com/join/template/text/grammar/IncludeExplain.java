package com.join.template.text.grammar;


import com.join.template.core.constant.Constant;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.grammar.Explain;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.Map;

public class IncludeExplain implements Explain {
    @Override
    public void verifyElement(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (!attr.containsKey(configuration.getAttFile())) {
            throw new TemplateException("请设置模板名称（" + configuration.getAttFile() + "）：" + original);
        }
    }

    @Override
    public String getGrammarExplain() {

        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_INCLUDE);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttFile()).append("=\"").append(MarkedWords.Attr_Template_Name).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, Map map, GrammarField field) {
        return null;
    }


}
