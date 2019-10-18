package com.join.template.text.reader;

import com.join.template.core.element.Element;
import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.verify.TemplateException;
import com.join.template.core.element.ElementBuilder;

public class TextParser implements Parser {

    private JoinFactory joinFactory;
    private Configuration configuration;
    private ExprAttr exprAttr;
    private Template template;
    private Integer exprText;
    private String matchBeginTag = "";
    private String matchEndTag = "";
    private String text = "";
    private Boolean isEndElement = false;


    @Override
    public Parser setJoinFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.exprAttr = joinFactory.createExprAttr();
        return this;
    }

    @Override
    public Parser setTemplate(Template template) {
        this.template = template;
        return this;
    }


    @Override
    public Parser setExprType(Integer exprText) {
        this.exprText = exprText;
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
    public Parser setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public Parser setEndElement(Boolean endElement) {
        this.isEndElement = endElement;
        return this;
    }

    @Override
    public ElementBuilder parser() {

//        if (exprText == null) {
//            String[] split = text.split(" ");
//            if (split == null || split.length == 0) {
//                throw new TemplateException("错误的节点：" + text);
//            }
//            split[0]
//        }

        ExprHandle exprHandle = joinFactory.getExprHandle(Constant.EXPR_TEXT);
        ElementBuilder elementBuilder = exprHandle.getElementBuilder(template);
        elementBuilder.original(matchBeginTag + text + matchEndTag);
        elementBuilder.nodeType(exprHandle.getNodeType());
        elementBuilder.exprHandle(exprHandle);
        elementBuilder.isEndElement(isEndElement);
        Element element = elementBuilder.build(template);
        if (exprHandle.getExplain() != null && !element.isEndElement())
            exprHandle.getExplain().verifyElement(element);
        for (ParserListener parserListener : exprHandle.getParserListeners()) {
            parserListener.onParser(element);
        }
        return elementBuilder;
    }
}
