package com.join.template.factory;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.expression.Expression;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;

import java.util.Set;


public interface JoinFactory extends TemplateFactory {

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
     * @param exprConfig 表达式配置
     * @return
     */
    JoinFactory addExprConfig(ExprConfig exprConfig);

    /**
     * 新增表达式配置
     *
     * @param nodeType  节点类型
     * @param tag       表达式标记
     * @param hasEndTag 是否有结束节点
     * @param process   处理器
     * @return
     */
    JoinFactory addExprConfig(String nodeType, String tag, boolean hasEndTag, Process process);

    /**
     * 新增表达式配置
     *
     * @param nodeType      节点节点类型
     * @param compareTag    节点匹配开始符
     * @param compareEndTag 节点匹配结束符
     * @param endTag        结束节点
     * @param process       处理器
     * @return
     */
    JoinFactory addExprConfig(String nodeType, String compareTag, String compareEndTag, String endTag, Process process);

    /**
     * 新增表达式处理器
     *
     * @param nodeType 节点类型
     * @param process  处理器
     * @return
     */
    JoinFactory addProcess(String nodeType, Process process);

    /**
     * 新增表达式解析监听
     *
     * @param nodeType       节点类型
     * @param parserListener  解析监听
     * @return
     */
    JoinFactory addListener(String nodeType, ParserListener parserListener);

    /**
     * 新增表达式处理监听
     *
     * @param nodeType        节点类型
     * @param processListener  处理监听
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
    ExprConfig hasExpression(String text);

    /**
     * 根据节点类型获取处理器
     *
     * @param nodeType 节点类型
     * @return
     */
    Process getProcess(String nodeType);

    /**
     * 根据节点类型获取解析监听
     *
     * @param nodeType 节点类型
     * @return
     */
    Set<ParserListener> getParserListeners(String nodeType);


    /**
     * 根据节点类型获取处理监听
     *
     * @param nodeType 节点类型
     * @return
     */
    Set<ProcessListener> getProcessListeners(String nodeType);

    /**
     * 获取表达式执行器
     *
     * @return
     */
    Expression getExpression();
}
