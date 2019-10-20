package com.join.template.html.node;

import com.join.template.core.constant.Constant;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.example.AbstractExample;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.util.TemplateException;
import com.join.template.html.MarkedWords;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: ListNode
 * @date 2019/10/1815:52
 */
@Getter
public class IFNodeExample extends AbstractExample implements NodeVerify, Element {
    //值属性
    private String text;


    @Override
    public void readAttributes() {
        this.text = this.getAttribute(configuration.getAttrText());
    }

    @Override
    public String getExample() {

        Map<String, String> attr = new HashMap<>();
        attr.put(this.configuration.getAttrText(), MarkedWords.Attr_Judgement_Conditions);
        String attribute = this.joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(this.configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(this.configuration.getExprEndSupport());
        grammar.append(this.configuration.getExprLastBegin());
        grammar.append(this.grammar.getTag());
        grammar.append(this.configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public void verify() {
        if (!this.attributes.containsKey(this.configuration.getAttrText())) {
            throw new TemplateException("请设置判断条件（" + this.configuration.getAttrText() + "）：" + this.original);
        }
    }

}
