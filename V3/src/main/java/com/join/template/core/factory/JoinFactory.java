package com.join.template.core.factory;

import com.join.template.core.*;
import com.join.template.core.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.text.JoinFactoryBase;

import java.util.Map;


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
     * @param nodeType
     * @param tag
     * @param parser
     * @param process
     * @param grammar
     * @return
     */
    JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process, Grammar grammar);

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

    JoinFactoryBase genGrammar();

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
    ExprConfig getExprConfigByTag(String tag);

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType
     * @return
     */
    ExprConfig getExprConfigByType(Integer nodeType);

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

    Map<Integer, String> getGrammars();
}
