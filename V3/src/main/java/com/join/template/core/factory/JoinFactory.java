package com.join.template.core.factory;

import com.join.template.core.*;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;

import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 模版引擎总工厂
 * @date 2019/8/19 11:45
 */
public interface JoinFactory extends TemplateFactory<JoinFactory> {
    /**
     * 初始化
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactory init();


    /**
     * 新增模版工厂
     *
     * @param nodeType        节点类型
     * @param templateFactory 模版工厂
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactory addFactory(String nodeType, TemplateFactory templateFactory);


    /**
     * 新增表达式配置
     *
     * @param nodeType 节点类型
     * @param tag      表达式标记
     * @param process  语法处理器
     * @param grammar  语法示例器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactory addExpressionHandle(Integer nodeType, String tag, Process process, GrammarExpl grammar);

    /**
     * 新增解析监听
     *
     * @param nodeType       节点类型
     * @param parserListener 解析监听
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:47
     */
    JoinFactory addListener(Integer nodeType, ParserListener parserListener);


    /**
     * 新增处理监听
     *
     * @param nodeType        节点类型
     * @param processListener 处理监听
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:48
     */
    JoinFactory addListener(Integer nodeType, ProcessListener processListener);

    /**
     * 加载语法解释
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:48
     */
    JoinFactory loadGrammar();

    /**
     * 获取配置
     *
     * @param
     * @return com.join.template.core.configuration.Configuration
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:48
     */
    Configuration getConfiguration();

    /**
     * 根据标记获取表达式配置
     *
     * @param tag 表达式标记
     * @return com.join.template.core.ExpressionHandle
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:48
     */
    ExpressionHandle getExpressionHandle(String tag);

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType 节点类型
     * @return com.join.template.core.ExpressionHandle
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    ExpressionHandle getExpressionHandle(Integer nodeType);

    /**
     * 获取表达式执行器
     *
     * @param
     * @return com.join.template.core.Expression
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    Expression getExpression();

    /**
     * 获取解析器
     *
     * @param
     * @return com.join.template.core.Reader
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    Reader getReader();

    /**
     * 获取语法解释
     *
     * @param
     * @return java.util.Map<java.lang.Integer                                                               ,                                                               java.lang.String>
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
