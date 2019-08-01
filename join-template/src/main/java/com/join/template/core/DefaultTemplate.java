package com.join.template.core;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.context.Context;
import com.join.template.context.HashContext;
import com.join.template.factory.JoinFactory;
import com.join.template.parser.Parser;
import com.join.template.parser.ParserElement;
import com.join.template.process.Process;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;
import com.join.template.reader.ReaderElement;

import java.io.StringWriter;
import java.io.Writer;


public class DefaultTemplate implements Template {
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    protected String templateName;
    protected String templateContent;
    protected Context context;
    protected int lineSize = 0;
    protected Element root = new Node().setNodeType(Constant.EXPRESSION_ROOT);
    private Reader reader;
    private Parser parser;

    public DefaultTemplate(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.context = new HashContext();
        this.reader = new ReaderElement(this);
        this.parser = new ParserElement(this);
    }


    @Override
    public Template init(String name, String text) {
        this.templateName = name;
        this.templateContent = text;
        this.reader.read(root, text, this.parser);
        lineSize = this.reader.getLineSize();
        return this;
    }


    @Override
    public Template putValue(String name, Object value) {
        this.context.put(name, value);
        return this;
    }

    @Override
    public Template putContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Template process(Writer writer) {
        Process process = joinFactory.getProcess(Constant.EXPRESSION_ROOT);
        process.process(root, context, writer);
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
    public Context getContext() {
        return context;
    }

    @Override
    public Element getRoot() {
        return root;
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
    public Parser getParser() {
        return parser;
    }

    @Override
    public Reader getReader() {
        return reader;
    }

    @Override
    public int getLineSize() {
        return lineSize;
    }
}
