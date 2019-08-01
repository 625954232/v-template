package com.join.template.factory;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExpressionConfig;
import com.join.template.expression.Expression;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;


public interface JoinFactory extends TemplateFactory {

    /**
     * 新增模版工厂
     *
     * @param nodeType
     * @param templateFactory
     * @return
     */
    JoinFactory addFactory(String nodeType, TemplateFactory templateFactory);

    /**
     * 新增表达式配置
     *
     * @param expressionConfig
     * @return
     */
    JoinFactory addConfig(ExpressionConfig expressionConfig);

    /**
     * 新增表达式配置
     *
     * @param nodeType
     * @param compareTag
     * @param compareEndTag
     * @param endTag
     * @return
     */
    JoinFactory addConfig(String nodeType, String compareTag, String compareEndTag, String endTag);

    /**
     * 新增表达式处理器
     *
     * @param nodeType
     * @param process
     * @return
     */
    JoinFactory addProcess(String nodeType, Process process);

    /**
     * 新增表达式解析监听
     *
     * @param nodeType
     * @param parserListener
     * @return
     */
    JoinFactory addListener(String nodeType, ParserListener parserListener);

    /**
     * 新增表达式处理监听
     *
     * @param nodeType
     * @param processListener
     * @return
     */
    JoinFactory addListener(String nodeType, ProcessListener processListener);

    /**
     * 获取配置
     *
     * @return
     */
    Configuration getConfiguration();

    /**
     * 根据内容获取表达式配置
     *
     * @param text
     * @return
     */
    ExpressionConfig hasExpression(String text);

    /**
     * 根据节点类型获取处理器
     *
     * @param nodeType
     * @return
     */
    Process getProcess(String nodeType);

    /**
     * 根据节点类型获取解析监听
     *
     * @param nodeType
     * @return
     */
    ParserListener getParserListeners(String nodeType);


    /**
     * 根据节点类型获取处理监听
     *
     * @param nodeType
     * @return
     */
    ProcessListener getProcessListeners(String nodeType);

    /**
     * 获取表达式执行器
     *
     * @return
     */
    Expression getExpression();
}
