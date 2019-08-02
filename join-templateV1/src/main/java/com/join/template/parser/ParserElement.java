package com.join.template.parser;

import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.core.Template;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.util.Utils;
import com.join.template.verify.Assert;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class ParserElement extends AbstractParser implements Parser {


    public ParserElement(Template template) {
        super(template);
    }

    @Override
    protected void parserHead(ExprConfig nodeConfig, Element element, String text) {
        Node node = (Node) element;
        if (nodeConfig != null) {
            int endIndex = text.indexOf(nodeConfig.getCompareEndTag()) + nodeConfig.getCompareEndTag().length();
            Assert.ifTrue(endIndex <= -1, "错误的语法：" + text);

            String head = text.substring(0, endIndex);
            Assert.isBlank(head, "错误的语法：" + text);

            Map<String, String> attr = Utils.findAttr(head);
            node.setNodeType(nodeConfig.getNodeType());
            node.setHead(head);
            node.addAttributes(attr);
            return;
        } else {
            node.setNodeType(Constant.EXPR_TEXT);
            node.setBody(text);
            return;
        }
    }


    @Override
    protected void parserBody(ExprConfig expressionConfig, Element element, String text) {
        Node node = (Node) element;
        if (expressionConfig == null || StringUtils.isBlank(expressionConfig.getEndTag())) {
            return;
        }
        int ifTagSize = 0;
        int ifTagEndSize = 0;
        int current = 0;
        StringBuffer temp = new StringBuffer();
        while (current < text.length()) {
            if (text.startsWith(expressionConfig.getCompareTag(), current)) {
                ifTagSize++;
            }
            if (text.startsWith(expressionConfig.getEndTag(), current)) {
                ifTagEndSize++;
            }
            temp.append(text.charAt(current));
            if (ifTagSize > 0 && ifTagEndSize > 0 && ifTagSize == ifTagEndSize) {
                break;
            }
            current++;
        }
        if (ifTagSize > ifTagEndSize || ifTagSize < ifTagEndSize) {
            throw new IllegalArgumentException(String.format("未找到对应%s的%s元素", node.getHead(), expressionConfig.getEndTag()));
        }
        if (ifTagSize == 0 && ifTagEndSize == 0) {
            element.setBody(text);
        } else {
            if (current == 0) {
                node.setBody("");
            } else if (current == (element.getHead().length() - 1)) {
                String body = text.substring(expressionConfig.getCompareTag().length(), current);
                node.setHead(expressionConfig.getCompareTag());
                node.setBody(body);
                node.setEnd(expressionConfig.getCompareEndTag());
            } else {
                String body = text.substring(element.getHead().length(), current);
                node.setBody(body);
                node.setEnd(expressionConfig.getEndTag());
            }
        }

    }


}
