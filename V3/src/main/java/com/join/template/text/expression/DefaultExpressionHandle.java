package com.join.template.text.expression;


import com.join.template.core.*;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.grammar.Explain;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.AbstractExpressionHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.util.Utils;
import com.join.template.text.node.Node;

import java.util.Map;


public class DefaultExpressionHandle extends AbstractExpressionHandle implements ExpressionHandle {

    public DefaultExpressionHandle(String tag, Integer nodeType, Process process, Explain grammarExpl) {
        super(tag, nodeType, process, grammarExpl);
    }

    @Override
    public Element parser(String matchBeginTag, String matchEndTag, String text) {

        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> attr = Utils.findAttr(text);
        Element element = new Node();
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
        element.setExpressionHandle(this);
        if (explain != null)
            explain.verifyElement(element);

        for (ParserListener parserListener : parserListeners) {
            parserListener.onParser(element);
        }
        return element;
    }


}
