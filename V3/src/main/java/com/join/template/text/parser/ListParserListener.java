package com.join.template.text.parser;

import com.join.template.core.Element;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.verify.TemplateException;
import com.join.template.text.node.Node;

public class ListParserListener implements ParserListener {
    private final JoinFactory joinFactory;
    private final Configuration configuration;

    public ListParserListener(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void onParser(Element element) {
        Node node = (Node) element;
        if (!element.getOriginal().startsWith(configuration.getExprFirstBegin())) {
            return;
        }
        if (!node.getAttributes().containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + node.getOriginal());
        }
        if (!node.getAttributes().containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + node.getOriginal());
        }
    }
}
