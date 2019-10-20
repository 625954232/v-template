package com.join.template.core.example;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.factory.JoinFactory;

/**
 * @author CAOYOU
 * @Title: DefaultExplain
 * @date 2019/8/2612:08
 */
public abstract class AbstractExample extends AbstractElement implements Example, Element {

    @Override
    public AbstractExample setGrammar(Grammar grammar) {
        this.grammar = grammar;
        this.joinFactory = grammar.getJoinFactory();
        this.configuration = grammar.getJoinFactory().getConfiguration();
        return this;
    }


}
