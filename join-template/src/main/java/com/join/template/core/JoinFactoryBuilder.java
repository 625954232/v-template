package com.join.template.core;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExpressionConfig;
import com.join.template.factory.JoinFactory;
import com.join.template.factory.JoinFactoryBase;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;


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


    public JoinFactory addFactory(String nodeType, TemplateFactory templateFactory) {
        return joinFactory.addFactory(nodeType, templateFactory);
    }

    public JoinFactory addConfig(ExpressionConfig expressionConfig) {
        return joinFactory.addConfig(expressionConfig);
    }

    public JoinFactory addConfig(String nodeType, String compareTag, String compareEndTag, String endTag) {
        return joinFactory.addConfig(nodeType, compareTag, compareEndTag, endTag);
    }

    public JoinFactory addProcess(String nodeType, Process process) {
        return joinFactory.addProcess(nodeType, process);
    }

    public JoinFactory addListener(String nodeType, ParserListener parserListener) {
        return joinFactory.addListener(nodeType, parserListener);
    }

    public JoinFactory addListener(String nodeType, ProcessListener processListener) {
        return joinFactory.addListener(nodeType, processListener);
    }

    public JoinFactory builder() {
        return joinFactory;
    }
}
