package com.join.template.core.process;

import com.join.template.core.Element;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;

import java.io.Writer;

public abstract class AbstractProcess implements Process {

    protected Configuration configuration;
    protected JoinFactory joinFactory;

    @Override
    public void process(Element element, Content context, Writer writer) {
        this.configuration = TemplateUtil.getConfiguration();
        this.joinFactory = TemplateUtil.getJoinFactory();
    }

    protected void removeSpace(Element child, int index, int size) {
        if (index == (size - 1) && child.getNodeType() == Constant.EXPR_TEXT
                && child.getOriginal().endsWith("\r\n")) {
            String string = child.getOriginal().substring(0, child.getOriginal().length() - 2);
            child.setOriginal(string);
        }
    }


}