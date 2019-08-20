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
import org.apache.commons.lang.StringUtils;

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
        grammar.append(configuration.getAttVar()).append("=\"").append(MarkedWords.Attr_Varchar_Name).append("\" ");
        grammar.append(configuration.getAttItem()).append("=\"").append(MarkedWords.Attr_Item_Name).append("\" ");
        grammar.append(configuration.getAttIndex()).append("=\"").append(MarkedWords.Attr_Index_Name).append("\" ");
        grammar.append(configuration.getAttOpen()).append("=\"").append(MarkedWords.Attr_Statement_Opener).append("\" ");
        grammar.append(configuration.getAttClose()).append("=\"").append(MarkedWords.Attr_Statement_Terminator).append("\" ");
        grammar.append(configuration.getAttSseparator()).append("=\"").append(MarkedWords.Attr_Separator).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        grammar.append(configuration.getExprLastBegin());
        grammar.append(expressionHandle.getTag());
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, Map map, GrammarField field) {
        Configuration configuration = TemplateUtil.getConfiguration();
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(Constant.EXPR_LIST);

        Object value = map.get(field.getNameFieldName());
        StringBuilder var = new StringBuilder();
        if (grammarInfo != null && StringUtils.isNotBlank(grammarInfo.getParentName())) {
            var.append(grammarInfo.getParentName());
            var.append(".");
        }
        var.append(value);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionHandle.getTag()).append(" ");
        grammar.append(configuration.getAttVar()).append("=\"").append(var).append("\" ");
        grammar.append(configuration.getAttItem()).append("=\"").append(var).append("\" ");
        grammar.append(configuration.getAttIndex()).append("=\"").append(configuration.getAttIndex()).append("\" ");
        grammar.append(configuration.getAttOpen()).append("=\"").append(MarkedWords.Attr_Statement_Opener).append("\" ");
        grammar.append(configuration.getAttClose()).append("=\"").append(MarkedWords.Attr_Statement_Terminator).append("\" ");
        grammar.append(configuration.getAttSseparator()).append("=\"").append(MarkedWords.Attr_Separator).append("\" ");
        grammar.append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprLastBegin());
        grammar.append(expressionHandle.getTag());
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }


}
