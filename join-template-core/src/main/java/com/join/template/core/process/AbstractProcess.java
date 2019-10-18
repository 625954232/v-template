package com.join.template.core.process;

import com.join.template.core.element.Element;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.context.Content;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;

public abstract class AbstractProcess implements Process {

    protected Configuration configuration;
    protected JoinFactory joinFactory;
    protected ExprHandle exprHandle;


    @Override
    public void setExprHandle(ExprHandle exprHandle) {
        this.exprHandle = exprHandle;
        this.joinFactory = exprHandle.getJoinFactory();
        this.configuration = exprHandle.getJoinFactory().getConfiguration();
    }

    @Override
    public void process(Element element, Content context, Writer writer) {

    }



}