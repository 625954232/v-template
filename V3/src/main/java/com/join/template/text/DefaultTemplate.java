package com.join.template.text;

import com.join.template.core.Element;
import com.join.template.core.Template;
import com.join.template.core.Tokenizer;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.constant.Constant;
import com.join.template.core.context.Content;
import com.join.template.core.context.HashContext;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;
import com.join.template.text.token.TreeTokenizer;

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

    public DefaultTemplate(String name, String text) {
        this.joinFactory = TemplateUtil.getJoinFactory();
        this.context = new HashContext();
        this.tokenizer = new TreeTokenizer();
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
