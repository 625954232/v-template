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
    protected String templateName;
    protected String templateContent;
    protected Content context;
    protected Tokenizer tokenizer;

    public DefaultTemplate(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.context = new HashContext();
        this.tokenizer = new TreeTokenizer(joinFactory);
    }


    @Override
    public Template init(String name, String text) {
        this.templateName = name;
        this.templateContent = text;
        this.tokenizer.read(text);
        return this;
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
        exprConfig.getProcess().process(this.getRoot(), context, writer);
        return this;
    }

    @Override
    public String process() {
        StringWriter writer = new StringWriter();
        this.process(writer);
        return writer.toString();
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
    public List<Element> getAllElements() {
        return tokenizer.getAllElement();
    }

    @Override
    public Element getRoot() {
        return this.tokenizer.getRootElement();
    }

    @Override
    public String getTemplateContent() {
        return templateContent;
    }

    @Override
    public String getTemplateName() {
        return templateName;
    }

    @Override
    public int getLineSize() {
        return this.tokenizer.getLineSize();
    }
}
