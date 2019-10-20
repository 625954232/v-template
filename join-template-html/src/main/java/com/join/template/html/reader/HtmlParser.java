package com.join.template.html.reader;

import com.join.template.core.element.Element;
import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.util.Assert;
import com.join.template.core.element.verify.NodeVerify;
import com.join.template.core.util.TemplateException;

public class HtmlParser implements Parser {

    private JoinFactory joinFactory;
    private Template template;

    private Boolean isGrammar = false;
    private Boolean isVarExpr = false;
    private String matchBeginTag = "";
    private String matchEndTag = "";
    private String content = "";
    private Boolean isEndElement = false;
    private Configuration configuration;


    @Override
    public Parser setJoinFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        return this;
    }

    @Override
    public Parser setTemplate(Template template) {
        this.template = template;
        return this;
    }

    @Override
    public Parser setMatchBeginTag(String matchBeginTag) {
        this.matchBeginTag = matchBeginTag;
        return this;
    }

    @Override
    public Parser setMatchEndTag(String matchEndTag) {
        this.matchEndTag = matchEndTag;
        return this;
    }

    @Override
    public Parser setGrammar(Boolean grammar) {
        this.isGrammar = grammar;
        return this;
    }

    @Override
    public Parser setVarExpr(Boolean varExpr) {
        this.isVarExpr = varExpr;
        return this;
    }

    @Override
    public Parser setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public Parser setEndElement(Boolean endElement) {
        this.isEndElement = endElement;
        return this;
    }

    @Override
    public Element parser() {
        String original = this.matchBeginTag + this.content + this.matchEndTag;
        Grammar grammar = null;
        if (isGrammar) {
            String[] split = this.content.split(" ");
            if (split == null || split.length == 0) {
                throw new TemplateException("错误的节点：" + this.content);
            }
            grammar = joinFactory.getGrammar(split[0]);
            Assert.isNull(grammar, "未找到对应的语法处理方式：" + this.content);
        } else {
            if (isVarExpr) {
                grammar = joinFactory.getGrammar(Constant.EXPR_VAR);
                Assert.isNull(grammar, "未找到参数对应的语法处理方式：" + this.content);
            } else {
                grammar = joinFactory.getGrammar(Constant.EXPR_TEXT);
                Assert.isNull(grammar, "未找到Html对应的语法处理方式：" + this.content);
                if (grammar == null) {
                    throw new TemplateException("未找到对应的语法处理方式：" + this.content);
                }
                if (this.content.contains(this.configuration.getExprFirstBegin())
                        || this.content.contains(this.configuration.getExprLastBegin())
                        || this.content.contains(this.configuration.getExprEndSupport())
                        || this.content.contains(this.configuration.getVarTagStart())
                        || this.content.contains(this.configuration.getVarTagEnd())) {
                    throw new TemplateException("文本标签不能包含语法：" + this.content);
                }
            }
        }
        Element element = grammar.createElement(template);
        element.read(original, isEndElement);
        if (!element.isEndElement() && element instanceof NodeVerify) {
            NodeVerify nodeVerify = (NodeVerify) element;
            nodeVerify.verify();
        }
        for (ParserListener parserListener : grammar.getParserListeners()) {
            parserListener.onParser(element);
        }
        return element;
    }
}
