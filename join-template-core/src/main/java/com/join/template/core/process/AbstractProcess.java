package com.join.template.core.process;

import com.join.template.core.element.Element;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.context.Content;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;

public abstract class AbstractProcess<T extends Element> implements Process<T> {

    protected Configuration configuration;
    protected JoinFactory joinFactory;
    protected Grammar grammar;


    @Override
    public void setGrammar(Grammar grammar) {
        this.grammar = grammar;
        this.joinFactory = grammar.getJoinFactory();
        this.configuration = grammar.getJoinFactory().getConfiguration();
    }

    @Override
    public void process(T element, Content context, Writer writer) {

    }
}
