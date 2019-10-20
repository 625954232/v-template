package com.join.template.html.node;

import com.join.template.core.constant.TemplateType;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.example.AbstractExample;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GenerateConfig;
import com.join.template.core.util.type.TypeInfo;
import com.join.template.html.MarkedWords;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author CAOYOU
 * @Title: ListNode
 * @date 2019/10/1815:52
 */
@Getter
public class VarNodeExample extends AbstractExample implements NodeVerify, Element {


    private String var;

    @Override
    public void readAttributes() {
        String replace = this.original.replace(this.configuration.getVarTagStart(), "");
        this.var = replace.replaceFirst(this.configuration.getVarTagEnd(), "");
        this.attributes.put(this.configuration.getAttVar(), this.var);
    }


    @Override
    public void verify() {
    }

    @Override
    public String getExample() {
        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getVarTagStart());
        grammar.append(MarkedWords.Attr_Param_Name).append(" ");
        grammar.append(configuration.getVarTagStart());
        return grammar.toString();
    }

    @Override
    public String genExample(TemplateType templateType, GrammarInfo grammarInfo, Map map, GenerateConfig generateConfig) {
        Object value = map.get(generateConfig.getNameField());
        return genExample(grammarInfo, value);
    }

    @Override
    public String genExample(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GenerateConfig generateConfig) {
        return genExample(grammarInfo, typeInfo.getName());
    }


    private String genExample(GrammarInfo grammarInfo, Object value) {
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
