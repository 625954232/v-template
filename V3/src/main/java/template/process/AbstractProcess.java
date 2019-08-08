package com.join.template.process;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public abstract class AbstractProcess implements Process {
    protected Configuration configuration;
    protected JoinFactory joinFactory;

    public AbstractProcess(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void process(Element element, Content context, Writer writer) {

    }

    //去换行
    protected void removeSpace(Element child) {
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getOriginal().startsWith("\r\n")) {
            String string = child.getOriginal().substring(2, child.getOriginal().length());
            child.setOriginal(string);
        }
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getOriginal().endsWith("\r\n")) {
            String string = child.getOriginal().substring(0, child.getOriginal().length() - 2);
            child.setOriginal(string);
        }
    }

}