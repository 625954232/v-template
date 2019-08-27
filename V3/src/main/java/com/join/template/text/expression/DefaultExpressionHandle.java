package com.join.template.text.expression;


import com.join.template.core.*;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.explain.Explain;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.AbstractExprHandle;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.util.TemplateUtil;
import com.join.template.text.node.Node;

import java.util.Map;


public class DefaultExpressionHandle extends AbstractExprHandle implements ExprHandle {


    public DefaultExpressionHandle(String tag, Integer nodeType, Process process, Explain explain, ExprAttr exprAttr) {
        super(tag, nodeType, process, explain, exprAttr);
    }

    @Override
    public Element parser(Template template, String matchBeginTag, String matchEndTag, String text) {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> attr = this.exprAttr.findAttribute(text);
        Element element = new Node(template);
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
        if (explain != null)
            explain.verifyElement(element);

        for (ParserListener parserListener : parserListeners) {
            parserListener.onParser(element);
        }
        return element;
    }


}
