package com.join.template.text;

import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.Process;

import java.util.List;


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
     * @param nodeType
     * @param tag
     * @param parser
     * @param process
     * @return
     */
    public JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process) {
        return joinFactory.addExprConfig(nodeType, tag, parser, process);
    }

    /**
     * 新增表达式配置
     *
     * @param nodeType
     * @param tag
     * @param parser
     * @param process
     * @param parserListeners
     * @param processListeners
     * @return
     */
    public JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process, List<ParserListener> parserListeners, List<ProcessListener> processListeners) {
        return joinFactory.addExprConfig(nodeType, tag, parser, process, parserListeners, processListeners);
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
