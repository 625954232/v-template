package com.join.template.html.node;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.example.AbstractExample;
import com.join.template.core.example.Example;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GenerateConfig;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.util.TemplateException;
import com.join.template.core.util.type.TypeInfo;
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
public class GetNodeExample extends AbstractExample implements NodeVerify, Element, Example {
    //值属性
    private String var;
    //别名属性
    private String name;
    //单项别名属性
    private String format;


    @Override
    public void readAttributes() {
        this.var = this.getAttribute(configuration.getAttVar());
        this.name = this.getAttribute(configuration.getAttName());
        this.format = this.getAttribute(configuration.getAttrFormat());
    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请设置需要获取的值别名（" + configuration.getAttVar() + "）：" + original);
        }
    }


    @Override
    public String getExample() {


        Map<String, String> attr = new HashMap<>();
        attr.put(this.configuration.getAttVar(), MarkedWords.Attr_Varchar_Name);
        attr.put(this.configuration.getAttrFormat(), MarkedWords.Attr_Date_Format);
        String attribute = this.joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(this.configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(this.configuration.getExprEndSupport());
        return grammar.toString();

    }


}
