package com.join.template.html.node;

import com.join.template.core.constant.Constant;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.example.AbstractExample;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.util.TemplateException;
import com.join.template.html.MarkedWords;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class IncludeNodeExample extends AbstractExample implements NodeVerify, Element {

    private String file;

    @Override
    public String getExample() {

        Map<String, String> attr = new HashMap<>();
        attr.put(this.configuration.getAttFile(), MarkedWords.Attr_Template_Name);
        String attribute = this.joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(this.configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(this.configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public void readAttributes() {
        file = attributes.get(configuration.getAttFile());
    }

    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttFile())) {
            throw new TemplateException("请设置模板名称（" + configuration.getAttFile() + "）：" + original);
        }
    }
}
