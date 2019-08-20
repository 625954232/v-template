package com.join.template.text.grammar;


import com.join.template.core.constant.Constant;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.EntityGrammar;
import com.join.template.core.grammar.FieldName;
import com.join.template.core.grammar.Explain;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;

import java.util.Map;

public class ListExplain implements Explain {

    @Override
    public void verifyElement(String original, Boolean endElement, Map<String, String> attr) {
        Configuration configuration = TemplateUtil.getConfiguration();
        if (!original.startsWith(configuration.getExprFirstBegin()) || endElement) {
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
    public String getGrammarExplain() {
        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_LIST);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttVar()).append("=\"").append(MarkedWords.Varchar_Name).append("\" ");
        grammar.append(configuration.getAttItem()).append("=\"").append(MarkedWords.Item_Name).append("\" ");
        grammar.append(configuration.getAttIndex()).append("=\"").append(MarkedWords.Index_Name).append("\" ");
        grammar.append(configuration.getAttOpen()).append("=\"").append(MarkedWords.Statement_Opener).append("\" ");
        grammar.append(configuration.getAttClose()).append("=\"").append(MarkedWords.Statement_Terminator).append("\" ");
        grammar.append(configuration.getAttSseparator()).append("=\"").append(MarkedWords.Separator).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        grammar.append(configuration.getExprLastBegin());
        grammar.append(expressionHandle.getTag());
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(EntityGrammar entityGrammar, Map map, FieldName field) {
        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_LIST);

        Object value = map.get(field.getNameFieldName());
        StringBuilder var = new StringBuilder();
        if (entityGrammar != null) {
            var.append(entityGrammar.getName());
            var.append(".");
        }
        var.append(value);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttVar()).append("=\"").append(var).append("\" ");
        grammar.append(configuration.getAttItem()).append("=\"").append(var).append("\" ");
        grammar.append(configuration.getAttIndex()).append("=\"").append(configuration.getAttIndex()).append("\" ");
        grammar.append(configuration.getAttOpen()).append("=\"").append(MarkedWords.Statement_Opener).append("\" ");
        grammar.append(configuration.getAttClose()).append("=\"").append(MarkedWords.Statement_Terminator).append("\" ");
        grammar.append(configuration.getAttSseparator()).append("=\"").append(MarkedWords.Separator).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        grammar.append(configuration.getExprLastBegin());
        grammar.append(expressionHandle.getTag());
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }


}
