package com.join.template.html.explain;


import com.join.template.core.constant.Constant;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.explain.AbstractExplain;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.explain.Explain;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.verify.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class IncludeExplain extends AbstractExplain implements Explain {

    @Override
    public void verifyElement(String original, Map<String, String> attr) {
        if (!attr.containsKey(configuration.getAttFile())) {
            throw new TemplateException("请设置模板名称（" + configuration.getAttFile() + "）：" + original);
        }
    }

    @Override
    public String getGrammarExplain() {
        ExprHandle exprHandle = joinFactory.getExprHandle(Constant.EXPR_INCLUDE);

        Map<String, String> attr = new HashMap<>();
        attr.put(configuration.getAttFile(), MarkedWords.Attr_Template_Name);
        String attribute = joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(exprHandle.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Map map, GrammarField field) {
        return null;
    }

    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField) {
        return null;
    }


}
