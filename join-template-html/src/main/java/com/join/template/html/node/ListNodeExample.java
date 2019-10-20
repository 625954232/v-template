package com.join.template.html.node;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.element.Element;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.example.AbstractExample;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GenerateConfig;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.util.TemplateException;
import com.join.template.core.util.type.TypeInfo;
import com.join.template.html.MarkedWords;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: ListNode
 * @date 2019/10/1815:52
 */
@Getter
public class ListNodeExample extends AbstractExample implements NodeVerify, Element {
    //值属性
    private String var;
    //别名属性
    private String separator;
    //单项别名属性
    private String item;
    //语句开始属性
    private String open;
    //索引属性
    private String index;
    //语句结束属性
    private String close;


    @Override
    public void readAttributes() {
        this.var = this.getAttribute(configuration.getAttVar());
        this.separator = this.getAttribute(configuration.getAttSseparator());
        this.item = this.getAttribute(configuration.getAttItem());
        this.open = this.getAttribute(configuration.getAttOpen());
        this.index = this.getAttribute(configuration.getAttIndex());
        this.close = this.getAttribute(configuration.getAttClose());
    }


    @Override
    public void verify() {
        if (!attributes.containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + original);
        }
        if (!attributes.containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + original);
        }
    }

    @Override
    public String getExample() {
        Grammar expressionElse = joinFactory.getGrammar(Constant.EXPR_ELSE);

        Map<String, String> attr = new HashMap<>();
        attr.put(configuration.getAttVar(), MarkedWords.Attr_Varchar_Name);
        attr.put(configuration.getAttItem(), MarkedWords.Attr_Item_Name);
        attr.put(configuration.getAttOpen(), MarkedWords.Attr_Statement_Opener);
        attr.put(configuration.getAttClose(), MarkedWords.Attr_Statement_Terminator);
        attr.put(configuration.getAttSseparator(), MarkedWords.Attr_Separator);
        String attribute = joinFactory.createExprAttr().genAttribute(attr);


        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprFirstBegin()).append(expressionElse.getTag()).append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Default_Content);
        grammar.append(configuration.getExprLastBegin()).append(this.grammar.getTag()).append(configuration.getExprEndSupport());
        return grammar.toString();
    }

    @Override
    public String genExample(TemplateType templateType, GrammarInfo grammarInfo, Map map, GenerateConfig generateConfig) {
        Object value = map.get(generateConfig.getNameField());
        return genExample(templateType, grammarInfo, value);
    }


    @Override
    public String genExample(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GenerateConfig generateConfig) {
        return genExample(templateType, grammarInfo, typeInfo.getName());
    }

    private String genExample(TemplateType templateType, GrammarInfo grammarInfo, Object value) {
        Grammar expressionElse = joinFactory.getGrammar(Constant.EXPR_ELSE);
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
        String attribute = joinFactory.createExprAttr().genAttribute(attr);

        StringBuilder grammar = new StringBuilder();
        grammar.append(configuration.getExprFirstBegin()).append(this.grammar.getTag()).append(" ");
        grammar.append(attribute);
        grammar.append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Content);
        grammar.append(configuration.getExprFirstBegin()).append(expressionElse.getTag()).append(configuration.getExprEndSupport());
        grammar.append(MarkedWords.Hint_Input_Generated_Default_Content);
        grammar.append(configuration.getExprLastBegin()).append(this.grammar.getTag()).append(configuration.getExprEndSupport());
        return grammar.toString();
    }

}
