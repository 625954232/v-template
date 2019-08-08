package com.join.template.factory;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.expression.Expression;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.parser.Parser;
import com.join.template.process.Process;
import com.join.template.reader.Reader;

import java.util.List;


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
     * @return
     */
    JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process);

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
    JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process, List<ParserListener> parserListeners, List<ProcessListener> processListeners);

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


}
