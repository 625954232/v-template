package com.join.template.expression;

import com.join.template.context.Context;

public interface Expression {
    void setExpression(String expression);

    void setContext(Context context);

    void put(String K, Object V);

    Object evaluate();
}
