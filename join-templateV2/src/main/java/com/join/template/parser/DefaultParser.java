package com.join.template.parser;

import com.join.template.configuration.ExprConfig;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.token.TokenMatcher;
import com.join.template.util.Utils;

import java.util.Map;

public class DefaultParser implements Parser {

    private JoinFactory joinFactory;

    public DefaultParser(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }

    @Override
    public Element parser(String text, TokenMatcher tokenMatcher, ExprConfig exprConfig) {
        Map<String, String> attr = Utils.findAttr(text);
        Node node = new Node();
        node.setNodeType(exprConfig.getNodeType());
        if (tokenMatcher != null) {
            node.setOriginal(tokenMatcher.getMatchBeginTag() + text + tokenMatcher.getMatchEndTag());
        } else {
            node.setOriginal(text);
        }
        node.addAttributes(attr);
        return node;
    }
}
