package com.join.template.listener.parser;

import com.join.template.configuration.Configuration;
import com.join.template.context.Context;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.parser.Parser;
import com.join.template.process.AbstractProcess;
import com.join.template.process.Process;
import com.join.template.verify.TemplateException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;

public class ListParserListener implements ParserListener {
    private final JoinFactory joinFactory;
    private final Configuration configuration;

    public ListParserListener(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void onParser(Element element, Context context) {
        Node node = (Node) element;
        if (!node.getAttributes().containsKey(configuration.getAttVar())) {
            throw new TemplateException("请求设置循环条件-数据来源别名（" + configuration.getAttVar() + "）：" + node.getHead());
        }
        if (!node.getAttributes().containsKey(configuration.getAttItem())) {
            throw new TemplateException("请求设置循环条件-单项定义别名（" + configuration.getAttItem() + "）：" + node.getHead());
        }
    }
}
