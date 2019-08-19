package com.join.template.core.interpreter;

import com.join.template.core.expression.ExpressionHandle;

import java.util.Map;

/**
 * @author CAOYOU
 * @Title: 解释器
 * @date 2019/8/1915:57
 */
public interface Interpreter {

    /**
     * 获取语法解释
     *
     * @param
     * @return java.util.Map<java.lang.Integer                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.String>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:50
     */
    Map<Integer, String> getGrammars();

    /**
     * 获取语法解释
     *
     * @param nodeType    节点类型
     * @param grammarAttr 表达式属性
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:50
     */
    String getGrammar(Integer nodeType, Map<String, String> grammarAttr);

    /**
     * 获取语法解释
     *
     * @param expressionHandle 表达式处理器
     * @param grammarAttr      表达式属性
     * @return
     */
    String getGrammar(ExpressionHandle expressionHandle, Map<String, String> grammarAttr);
}
