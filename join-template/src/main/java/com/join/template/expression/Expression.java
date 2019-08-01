package com.join.template.expression;

import com.join.template.context.Content;

public interface Expression {

    void setExpression(String expression);

    void setContext(Content context);

    void put(String K, Object V);

    Object evaluate();
}
