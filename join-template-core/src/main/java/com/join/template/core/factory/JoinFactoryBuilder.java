package com.join.template.core.factory;

import com.join.template.core.Reader;
import com.join.template.core.expression.ExprActuator;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.expression.ExprHandleBuilder;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.grammar.GrammarGenerate;

/**
 * @author CAOYOU
 * @Title: JoinFactoryBuilder
 * @date 2019/9/616:03
 */
public class JoinFactoryBuilder<T extends JoinFactory> {
    private T joinFactory;

    public JoinFactoryBuilder(T joinFactory) {
        this.joinFactory = joinFactory;
    }

    public JoinFactoryBuilder(Class<? extends T> clazz) throws ReflectiveOperationException {
        this.joinFactory = clazz.newInstance();
    }
    
    public JoinFactory setReader(Reader reader) {
        return joinFactory.setReader(reader);
    }

    public JoinFactory setExprActuator(ExprActuator expression) {
        return joinFactory.setExprActuator(expression);
    }

    public JoinFactory setGrammarGenerate(GrammarGenerate grammarGenerate) {
        return joinFactory.setGrammarGenerate(grammarGenerate);
    }

    public JoinFactory addFactory(String nodeType, TemplateFactory templateFactory) {
        return joinFactory.addFactory(nodeType, templateFactory);
    }

    public JoinFactory addExprHandle(ExprHandle exprHandle) {
        return joinFactory.addExprHandle(exprHandle);
    }

    public JoinFactory buildExprHandle() {
        return joinFactory.buildExprHandle();
    }

    public ExprHandleBuilder builderExprHandle() {
        return joinFactory.builderExprHandle();
    }

    public JoinFactoryBuilder putTemplate(String name, String text) {
        joinFactory.putTemplate(name, text);
        return this;
    }

    public JoinFactory build() {
        return this.joinFactory.init();
    }

}
