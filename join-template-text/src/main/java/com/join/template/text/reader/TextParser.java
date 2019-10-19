package com.join.template.html.reader;

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

import java.util.Map;

public class TextParser implements Parser {

    private final JoinFactory joinFactory;
    private final Configuration configuration;
    private final ExprAttr exprAttr;

    public TextParser(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.exprAttr = joinFactory.createExprAttr();
    }

    @Override
    public ElementBuilder parser(Template template, Integer exprText, String matchBeginTag, String matchEndTag, String text, Boolean isEndElement) {
        ElementBuilder elementBuilder = template.elementBuilder();
        Map<String, String> attr = this.exprAttr.findAttribute(text);
        ExprHandle exprHandle = null;
        if (exprText == null) {
            String[] split = text.split(" ");
            if (split == null || split.length == 0) {
                throw new TemplateException("错误的节点：" + text);
            }
            exprHandle = joinFactory.getExprHandle(split[0]);
            if (exprHandle == null) {
                throw new TemplateException("错误的语法表达式：" + text);
            }
            elementBuilder.original(matchBeginTag + text + matchEndTag);
        } else if (Constant.EXPR_VAR == exprText) {
            exprHandle = joinFactory.getExprHandle(Constant.EXPR_VAR);
            elementBuilder.attribute(configuration.getAttVar(), text);
            elementBuilder.original(matchBeginTag + text + matchEndTag);
        } else if (Constant.EXPR_TEXT == exprText) {
            exprHandle = joinFactory.getExprHandle(Constant.EXPR_TEXT);
            elementBuilder.original(text);
        } else {
            throw new TemplateException("错误的语法表达式：" + text);
        }
        elementBuilder.nodeType(exprHandle.getNodeType());
        elementBuilder.exprHandle(exprHandle);
        elementBuilder.attributes(attr);
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
