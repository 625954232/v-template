package com.join.template.text.explain;


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

public class SetExplain implements Explain {
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
    public String getGrammarExplain() {
        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_SET);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttVar()).append("=\"").append(MarkedWords.Attr_Varchar_Name).append("\" ");
        grammar.append(configuration.getAttName()).append("=\"").append(MarkedWords.Attr_Varchar_Alias).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();

    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, Map map, GrammarField field) {
        return null;
    }


}
