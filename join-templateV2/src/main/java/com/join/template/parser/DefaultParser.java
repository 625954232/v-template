package com.join.template.parser;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.util.Utils;

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
        Map<String, String> attr = Utils.findAttr(text);
        Node node = new Node();
        if (matchBeginTag != null && matchEndTag != null) {
            if (matchBeginTag.startsWith(configuration.getVarTagStart()) &&
                    matchEndTag.startsWith(configuration.getVarTagEnd())) {
                node.setNodeType(Constant.EXPR_VAR);
            } else {
                node.setNodeType(exprConfig.getNodeType());
            }
            node.setOriginal(matchBeginTag + text + matchEndTag);
        } else {
            node.setNodeType(exprConfig.getNodeType());
            node.setOriginal(text);
        }
        node.addAttributes(attr);
        return node;
    }
}
