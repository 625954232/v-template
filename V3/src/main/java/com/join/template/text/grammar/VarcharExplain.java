package com.join.template.text.grammar;


import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.grammar.EntityGrammar;
import com.join.template.core.grammar.FieldName;
import com.join.template.core.grammar.Explain;
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
        grammar.append(MarkedWords.Param_Name).append(" ");
        grammar.append(configuration.getVarTagStart());
        return grammar.toString();

    }

    @Override
    public String genGrammar(EntityGrammar entityGrammar, Map map, FieldName field) {
        Configuration configuration = TemplateUtil.getConfiguration();

        Object value = map.get(field.getNameFieldName());
        StringBuilder builder = new StringBuilder();
        builder.append(configuration.getVarTagStart());
        if (entityGrammar != null && StringUtils.isNotBlank(entityGrammar.getParentName())) {
            builder.append(entityGrammar.getParentName());
            builder.append(".");
        }
        builder.append(value);
        builder.append(configuration.getVarTagEnd());
        return builder.toString();
    }


}
