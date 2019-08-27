package com.join.template.text;

import com.join.template.core.Element;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.Template;
import com.join.template.core.Word;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.context.Content;
import com.join.template.core.context.HashContext;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.TemplateUtil;
import com.join.template.text.word.TreeWord;

import java.io.StringWriter;
import java.io.Writer;
import java.util.List;


public class DefaultTemplate implements Template {
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    protected Content context;
    protected Word tokenizer;
    protected String name;
    protected String text;
    protected TemplateType templateType;

    public DefaultTemplate(String name, String text) {
        this.name = name;
        this.text = text;
        this.templateType = TemplateType.of(name);
        this.joinFactory = TemplateUtil.getJoinFactory();
        this.context = new HashContext();
        this.tokenizer = new TreeWord(this);
        this.tokenizer.word(this.text);
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
        ExprHandle expressionHandle = joinFactory.getExprHandle(Constant.EXPR_ROOT);
        expressionHandle.getProcess().process(this.getRootElement(), context, writer);
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
    public TemplateType getTemplateType() {
        return templateType;
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
