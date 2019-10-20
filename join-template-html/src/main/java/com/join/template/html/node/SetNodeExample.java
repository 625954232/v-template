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
public class SetNodeExample extends AbstractExample implements NodeVerify, Element {
    //值属性
    private String var;
    //别名属性
    private String name;


    @Override
    public void readAttributes() {
        this.var = this.getAttribute(configuration.getAttVar());
        this.name = this.getAttribute(configuration.getAttName());
    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置你要存放的值（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attributes.containsKey(configuration.getAttName())) {
            throw new TemplateException("请求设置你要存放值的别名（" + configuration.getAttItem() + "）：" + original);
        }
    }

    @Override
    public String getExample() {

        Map<String, String> attr = new HashMap<>();
        attr.put(configuration.getAttVar(), MarkedWords.Attr_Varchar_Name);
        attr.put(configuration.getAttName(), MarkedWords.Attr_Varchar_Alias);
        String attribute = joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        return grammar.toString();

    }
}
