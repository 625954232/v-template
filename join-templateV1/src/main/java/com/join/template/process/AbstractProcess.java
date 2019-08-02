package com.join.template.process;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ProcessListener;
import com.join.template.node.Element;

import java.io.Writer;
import java.util.Set;

public abstract class AbstractProcess implements Process {
    protected Configuration configuration;
    protected JoinFactory joinFactory;

    public AbstractProcess(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public void process(Element element, Content context, Writer writer) {
        Set<ProcessListener> processListeners = this.joinFactory.getProcessListeners(element.getNodeType());
        if (processListeners != null) {
            for (ProcessListener processListener : processListeners) {
                processListener.onProcess(element, context, writer);
            }
        }
    }

    //去换行
    protected void removeSpace(Element child) {
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().startsWith("\r\n")) {
            String string = child.getBody().substring(2, child.getBody().length());
            child.setBody(string);
        }
        if (child.getNodeType() == Constant.EXPR_TEXT
                && child.getBody().endsWith("\r\n")) {
            String string = child.getBody().substring(0, child.getBody().length() - 2);
            child.setBody(string);
        }
    }

}