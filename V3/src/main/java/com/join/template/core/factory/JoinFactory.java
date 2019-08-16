package com.join.template.core.factory;

import com.join.template.core.*;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;

import java.util.Map;


public interface JoinFactory extends TemplateFactory<JoinFactory> {
    /**
     * 初始化
     *
     * @return
     */
    JoinFactory init();

    /**
     * 新增模版工厂
     *
     * @param nodeType        节点类型
     * @param templateFactory 模版工厂
     * @return
     */
    JoinFactory addFactory(String nodeType, TemplateFactory templateFactory);


    /**
     * 新增表达式配置
     *
     * @param nodeType
     * @param tag
     * @param process
     * @param grammar
     * @return
     */
    JoinFactory addExpressionHandle(Integer nodeType, String tag, Process process, GrammarExpl grammar);

    /**
     * 新增解析监听
     *
     * @param nodeType
     * @param parserListener
     * @return
     */
    JoinFactory addListener(Integer nodeType, ParserListener parserListener);

    /**
     * 新增处理监听
     *
     * @param nodeType
     * @param processListener
     * @return
     */
    JoinFactory addListener(Integer nodeType, ProcessListener processListener);

    /**
     * 加载语法解释
     *
     * @return
     */
    JoinFactory loadGrammar();

    /**
     * 获取配置
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 根据标记获取表达式配置
     *
     * @param tag
     * @return
     */
    ExpressionHandle getExpressionHandle(String tag);

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType
     * @return
     */
    ExpressionHandle getExpressionHandle(Integer nodeType);

    /**
     * 获取表达式执行器
     *
     * @return
     */
    Expression getExpression();

    /**
     * 获取解析器
     *
     * @return
     */
    Reader getReader();

    /**
     * 获取语法解释
     *
     * @return
     */
    Map<Integer, String> getGrammars();

    /**
     * 获取语法解释
     *
     * @param nodeType
     * @param grammarAttr
     * @return
     */
    String getGrammar(Integer nodeType, Map<String, String> grammarAttr);

    /**
     * 获取语法解释
     *
     * @param expressionHandle
     * @param grammarAttr
     * @return
     */
    String getGrammar(ExpressionHandle expressionHandle, Map<String, String> grammarAttr);
}
