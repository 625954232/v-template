package com.join.template.core;

import com.join.template.core.context.Content;

public interface Expression {
    
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
