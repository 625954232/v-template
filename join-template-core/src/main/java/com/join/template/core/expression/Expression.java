package com.join.template.core.expression;

import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

public interface Expression {

    void setJoinFactory(JoinFactory joinFactory);

    /**
     * 设置表达式
     *
     * @param expression 表达式
     */
    void setExpression(String expression);

    /**
     * 设置参数
     *
     * @param context 参数
     */
    void setContext(Content context);

    /**
     * 设置参数
     *
     * @param K 键
     * @param V 值
     */
    void put(String K, Object V);

    /**
     * 求值
     *
     * @return
     */
    Object evaluate();


}
