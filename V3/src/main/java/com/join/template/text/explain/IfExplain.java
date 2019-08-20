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

public class IfExplain implements Explain {
    @Override
    public void verifyElement(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (endElement && !attr.containsKey(configuration.getAttrText())) {
            throw new TemplateException("请设置判断条件（" + configuration.getAttrText() + "）：" + original);
        }
    }

    @Override
    public String getGrammarExplain() {
        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_IF);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttrText()).append("=\"").append(MarkedWords.Attr_Judgement_Conditions).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        grammar.append(configuration.getExprLastBegin());
        grammar.append(expressionHandle.getTag());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, Map map, GrammarField field) {
        return null;
    }


}
