package com.join.template.listener.parser;

import com.join.template.configuration.Configuration;
import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.verify.TemplateException;

public class ListParserListener implements ParserListener {
    private final JoinFactory joinFactory;
    private final Configuration configuration;

    public ListParserListener(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void onParser(Element element, Content context) {
        Node node = (Node) element;
        if (!node.getAttributes().containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + node.getHead());
        }
        if (!node.getAttributes().containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + node.getHead());
        }
    }
}
