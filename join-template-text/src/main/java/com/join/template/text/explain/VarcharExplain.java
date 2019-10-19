package com.join.template.html.explain;


import com.join.template.core.constant.MarkedWords;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.explain.AbstractExplain;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.explain.Explain;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class VarcharExplain extends AbstractExplain implements Explain {


    @Override
    public void verifyElement(String original, Map<String, String> attr) {

    }

    @Override
    public String getGrammarExplain() {
        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getVarTagStart());
        grammar.append(MarkedWords.Attr_Param_Name).append(" ");
        grammar.append(configuration.getVarTagStart());
        return grammar.toString();
    }

    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Map map, GrammarField field) {
        Object value = map.get(field.getNameField());
        return genGrammar(grammarInfo, value);
    }

    @Override
    public String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField) {
        return genGrammar(grammarInfo, typeInfo.getName());
    }


    private String genGrammar(GrammarInfo grammarInfo, Object value) {
        StringBuilder builder = new StringBuilder();
        builder.append(configuration.getVarTagStart());
        if (grammarInfo != null && StringUtils.isNotBlank(grammarInfo.getParentName())) {
            builder.append(grammarInfo.getParentName());
            builder.append(".");
        }
        builder.append(value);
        builder.append(configuration.getVarTagEnd());
        return builder.toString();
    }


}
