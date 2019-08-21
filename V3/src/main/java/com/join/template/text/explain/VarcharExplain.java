package com.join.template.text.explain;


import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.grammar.generate.EntityGrammarInfo;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.grammar.Explain;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.util.TemplateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class VarcharExplain implements Explain {
    @Override
    public void verifyElement(String original, Boolean endElement, Map<String, String> attr) {

    }

    @Override
    public String getGrammarExplain() {
        Configuration configuration = TemplateUtil.getConfiguration();
        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getVarTagStart());
        grammar.append(MarkedWords.Attr_Param_Name).append(" ");
        grammar.append(configuration.getVarTagStart());
        return grammar.toString();
    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, Map map, GrammarField field) {
        Object value = map.get(field.getNameField());
        return genGrammar(grammarInfo, value);
    }

    @Override
    public String genGrammar(GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField) {
        return genGrammar(grammarInfo, typeInfo.getName());
    }


    private String genGrammar(GrammarInfo grammarInfo, Object value) {
        Configuration configuration = TemplateUtil.getConfiguration();
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
