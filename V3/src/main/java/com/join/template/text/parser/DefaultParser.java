package com.join.template.text.parser;

import com.join.template.core.Element;
import com.join.template.core.Parser;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.util.Utils;
import com.join.template.text.node.Node;

import java.util.List;
import java.util.Map;

public class DefaultParser implements Parser {

    private Configuration configuration;
    private JoinFactory joinFactory;

    public DefaultParser() {
        this.joinFactory = TemplateUtil.getJoinFactory();
        this.configuration = TemplateUtil.getConfiguration();
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
