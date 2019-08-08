package com.join.template.text.expression;

import com.join.template.core.Expression;
import com.join.template.core.context.Content;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;

public class DefaultExpression implements Expression {

    private Content context;
    private String expression;
    protected final static JexlEngine jexlEngine = new JexlEngine();

    @Override
    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public void setContext(Content context) {
        this.context = context;
    }

    @Override
    public void put(String K, Object V) {
        context.put(K, V);
    }

    @Override
    public Object evaluate() {

        JexlContext jc = new JexlContext() {
            @Override
            public Object get(String name) {
                return context.get(name);
            }

            @Override
            public void set(String name, Object value) {
                context.put(name, value);
            }

            @Override
            public boolean has(String name) {
                return context.hasKey(name);
            }
        };
        org.apache.commons.jexl2.Expression e = jexlEngine.createExpression(expression);
        return e.evaluate(jc);
    }

}
