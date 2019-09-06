package com.join.template.text.expression;


import com.join.template.core.*;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.AbstractExprHandle;
import com.join.template.core.listener.ParserListener;
import com.join.template.text.node.Node;

import java.util.Map;


public class TextExprHandle extends AbstractExprHandle implements ExprHandle {


    public TextExprHandle(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public Element parser(Template template, String matchBeginTag, String matchEndTag, String text, Boolean isEndElement) {
        Map<String, String> attr = this.exprAttr.findAttribute(text);
        Element element = new Node(template);
        element.setEndElement(isEndElement);
        if (matchBeginTag != null && matchEndTag != null) {
            if (matchBeginTag.startsWith(configuration.getVarTagStart()) &&
                    matchEndTag.startsWith(configuration.getVarTagEnd())) {
                element.setNodeType(Constant.EXPR_VAR);
                element.addAttributes(configuration.getAttVar(), text);
            } else {
                element.setNodeType(this.nodeType);
            }
            element.setOriginal(matchBeginTag + text + matchEndTag);
        } else {
            element.setNodeType(this.nodeType);
            element.setOriginal(text);
        }
        element.addAttributes(attr);
        element.setExprHandle(this);
        if (explain != null && !element.isEndElement())
            explain.verifyElement(element);

        for (ParserListener parserListener : parserListeners) {
            parserListener.onParser(element);
        }
        return element;
    }


}
