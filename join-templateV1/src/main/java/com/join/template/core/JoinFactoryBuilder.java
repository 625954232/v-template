package com.join.template.core;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.factory.JoinFactory;
import com.join.template.factory.JoinFactoryBase;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;


public class JoinFactoryBuilder {

    JoinFactory joinFactory;

    public JoinFactoryBuilder() {
        this(new Configuration());
    }

    public JoinFactoryBuilder(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }

    public JoinFactoryBuilder(Configuration configuration) {
        this.joinFactory = new JoinFactoryBase(configuration);
    }

    /**
     * 新增模版工厂
     *
     * @param nodeType        节点类型
     * @param templateFactory 模版工厂
     * @return
     */
    public JoinFactory addFactory(String nodeType, TemplateFactory templateFactory) {
        return joinFactory.addFactory(nodeType, templateFactory);
    }

    /**
     * 新增表达式配置
     *
     * @param exprConfig 表达式配置
     * @return
     */
    public JoinFactory addExprConfig(ExprConfig exprConfig) {
        return joinFactory.addExprConfig(exprConfig);
    }

    /**
     * 新增表达式配置
     *
     * @param nodeType  节点类型
     * @param tag       表达式标记
     * @param hasEndTag 是否有结束节点
     * @param process   处理器
     * @return
     */
    public JoinFactory addExprConfig(String nodeType, String tag, boolean hasEndTag, Process process) {
        return joinFactory.addExprConfig(nodeType, tag, hasEndTag, process);
    }

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
    public JoinFactory addExprConfig(String nodeType, String compareTag, String compareEndTag, String endTag, Process process) {
        return joinFactory.addExprConfig(nodeType, compareTag, compareEndTag, endTag, process);
    }

    /**
     * 新增表达式处理器
     *
     * @param nodeType 节点类型
     * @param process  处理器
     * @return
     */
    public JoinFactory addProcess(String nodeType, Process process) {
        return joinFactory.addProcess(nodeType, process);
    }

    /**
     * 新增表达式解析监听
     *
     * @param nodeType       节点类型
     * @param parserListener 解析监听
     * @return
     */
    public JoinFactory addListener(String nodeType, ParserListener parserListener) {
        return joinFactory.addListener(nodeType, parserListener);
    }

    /**
     * 新增表达式处理监听
     *
     * @param nodeType        节点类型
     * @param processListener 处理监听
     * @return
     */
    public JoinFactory addListener(String nodeType, ProcessListener processListener) {
        return joinFactory.addListener(nodeType, processListener);
    }

    /**
     * 缓存模板
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    public Template putTemplate(String name, String content) {
        return joinFactory.putTemplate(name, content);
    }

    /**
     * 缓存模板
     *
     * @param fileName 文件路径
     * @return
     */
    public Template putTemplate(String fileName) {
        return joinFactory.putTemplate(fileName);
    }

    /**
     * 返回模版总工厂
     *
     * @return
     */
    public JoinFactory builder() {
        return joinFactory;
    }
}
