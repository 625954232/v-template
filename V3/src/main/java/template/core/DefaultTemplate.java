package com.join.template.core;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.context.HashContext;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.token.Tokenizer;
import com.join.template.token.TreeTokenizer;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;


public class DefaultTemplate implements Template {
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    protected Content context;
    protected Tokenizer tokenizer;
    protected String name;
    protected String text;

    public DefaultTemplate(JoinFactory joinFactory, String name, String text) {
        this.joinFactory = joinFactory;
        this.context = new HashContext();
        this.tokenizer = new TreeTokenizer(joinFactory);
        this.name = name;
        this.text = text;
        this.tokenizer.read(this.text);
    }


    @Override
    public Template putValue(String name, Object value) {
        this.context.put(name, value);
        return this;
    }

    @Override
    public Template putContext(Content context) {
        this.context = context;
        return this;
    }

    @Override
    public Template process(Writer writer) {
        ExprConfig exprConfig = joinFactory.getExprConfigByType(Constant.EXPR_ROOT);
        exprConfig.getProcess().process(this.getRootElement(), context, writer);
        return this;
    }

    @Override
    public String process() {
        StringWriter writer = new StringWriter();
        this.process(writer);
        return writer.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public JoinFactory getJoinFactory() {
        return joinFactory;
    }

    @Override
    public Content getContent() {
        return context;
    }

    @Override
    public List<Element> getAllElement() {
        return tokenizer.getAllElement();
    }

    @Override
    public Element getRootElement() {
        return this.tokenizer.getRootElement();
    }


    @Override
    public int getLineSize() {
        return this.tokenizer.getLineSize();
    }
}
