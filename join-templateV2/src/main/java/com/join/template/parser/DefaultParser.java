package com.join.template.parser;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.util.Utils;

import java.util.List;
import java.util.Map;

public class DefaultParser implements Parser {

    private Configuration configuration;
    private JoinFactory joinFactory;

    public DefaultParser(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }


    @Override
    public Element parser(String matchBeginTag, String matchEndTag, String text, ExprConfig exprConfig) {
        List<ParserListener> parserListeners = exprConfig.getParserListeners();
        Map<String, String> attr = Utils.findAttr(text);
        Node node = new Node();
        node.setExprConfig(exprConfig);
        if (matchBeginTag != null && matchEndTag != null) {
            if (matchBeginTag.startsWith(configuration.getVarTagStart()) &&
                    matchEndTag.startsWith(configuration.getVarTagEnd())) {
                node.setNodeType(Constant.EXPR_VAR);
                node.addAttributes(configuration.getAttVar(), text);
            } else {
                node.setNodeType(exprConfig.getNodeType());
            }
            node.setOriginal(matchBeginTag + text + matchEndTag);
        } else {
            node.setNodeType(exprConfig.getNodeType());
            node.setOriginal(text);
        }
        node.addAttributes(attr);
        for (ParserListener parserListener : parserListeners) {
            parserListener.onParser(node);
        }
        return node;
    }
}
