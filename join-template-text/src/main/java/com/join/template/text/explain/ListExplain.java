package com.join.template.text.explain;


import com.join.template.core.constant.Constant;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.explain.AbstractExplain;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.explain.Explain;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ListExplain extends AbstractExplain implements Explain {

    public ListExplain(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public void verifyElement(String original, Map<String, String> attr) {
        if (!attr.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attr.containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + original);
        }
    }

    @Override
    public String getGrammarExplain() {
        ExprHandle expressionList = joinFactory.getExprHandle(Constant.EXPR_LIST);
        ExprHandle expressionElse = joinFactory.getExprHandle(Constant.EXPR_ELSE);

        Map<String, String> attr = new HashMap<>();
        attr.put(configuration.getAttVar(), MarkedWords.Attr_Varchar_Name);
        attr.put(configuration.getAttItem(), MarkedWords.Attr_Item_Name);
        attr.put(configuration.getAttOpen(), MarkedWords.Attr_Statement_Opener);
        attr.put(configuration.getAttClose(), MarkedWords.Attr_Statement_Terminator);
        attr.put(configuration.getAttSseparator(), MarkedWords.Attr_Separator);
        String attribute = expressionList.getExprAttr().genAttribute(attr);


        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionList.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprFirstBegin()).append(expressionElse.getTag()).append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Default_Content);
        grammar.append(configuration.getExprLastBegin()).append(expressionList.getTag()).append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Map map, GrammarField field) {
        Object value = map.get(field.getNameField());
        return genGrammar(templateType, grammarInfo, value);
    }


    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField) {
        return genGrammar(templateType, grammarInfo, typeInfo.getName());
    }

    private String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Object value) {
        ExprHandle expressionList = joinFactory.getExprHandle(Constant.EXPR_LIST);
        ExprHandle expressionElse = joinFactory.getExprHandle(Constant.EXPR_ELSE);
        StringBuilder var = new StringBuilder();
        if (grammarInfo != null && StringUtils.isNotBlank(grammarInfo.getParentName())) {
            var.append(grammarInfo.getParentName());
            var.append(".");
        }
        var.append(value);

        Map<String, String> attr = new HashMap<>();
        attr.put(configuration.getAttVar(), var.toString());
        attr.put(configuration.getAttItem(), var.toString());
        attr.put(configuration.getAttOpen(), MarkedWords.Attr_Statement_Opener);
        attr.put(configuration.getAttClose(), MarkedWords.Attr_Statement_Terminator);
        attr.put(configuration.getAttSseparator(), MarkedWords.Attr_Separator);
        String attribute = expressionList.getExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(expressionList.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprFirstBegin()).append(expressionElse.getTag()).append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Default_Content);
        grammar.append(configuration.getExprLastBegin()).append(expressionList.getTag()).append(configuration.getExprEndSupport());
        return grammar.toString();
    }

}
