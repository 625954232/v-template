package com.join.template.text;

import com.join.template.core.Grammar;
import com.join.template.core.Parser;
import com.join.template.core.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;


public class JoinFactoryBuilder {

    JoinFactory joinFactory;

    public JoinFactoryBuilder() {
        this(new Configuration());
    }

    public JoinFactoryBuilder(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }

    public JoinFactoryBuilder(Configuration configuration) {
        this.joinFactory = new JoinFactoryBase(configuration);
    }


    public JoinFactoryBuilder addFactory(String nodeType, TemplateFactory templateFactory) {
        joinFactory.addFactory(nodeType, templateFactory);
        return this;
    }

    public JoinFactoryBuilder addExprConfig(Integer nodeType, String tag, Parser parser, Process process, Grammar grammar) {
        joinFactory.addExprConfig(nodeType, tag, parser, process, grammar);
        return this;
    }

    public JoinFactoryBuilder addListener(Integer nodeType, ParserListener parserListener) {
        joinFactory.addListener(nodeType, parserListener);
        return this;
    }

    public JoinFactoryBuilder addListener(Integer nodeType, ProcessListener processListener) {
        joinFactory.addListener(nodeType, processListener);
        return this;
    }

    public JoinFactoryBuilder loadGrammar() {
        joinFactory.loadGrammar();
        return this;
    }

    public JoinFactoryBuilder putTemplate(String name, String text) {
        joinFactory.putTemplate(name, text);
        return this;
    }

    public JoinFactoryBuilder putTemplate(String fileName) {
        joinFactory.putTemplate(fileName);
        return this;
    }

    public JoinFactory builder() {
        return joinFactory;
    }
}
