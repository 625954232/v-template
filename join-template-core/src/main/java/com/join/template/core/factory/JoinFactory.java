package com.join.template.core.factory;

import com.join.template.core.*;
import com.join.template.core.expression.*;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.template.TemplateFactory;

import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 模版引擎总工厂
 * @date 2019/8/19 11:45
 */
public interface JoinFactory extends TemplateFactory {


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
     * 获取模版工厂
     *
     * @param type 模版类型
     * @return com.join.template.core.factory.template.TemplateFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/22 11:51
     */
    TemplateFactory getTemplateFactorys(String type);


    /**
     * 根据标记获取表达式配置
     *
     * @param tag 表达式标记
     * @return com.join.template.core.expression.ExpressionHandle
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:48
     */
    ExprHandle getExprHandle(String tag);

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType 节点类型
     * @return com.join.template.core.expression.ExpressionHandle
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    ExprHandle getExprHandle(Integer nodeType);

    /**
     * 获取全部表达式配置
     *
     * @param
     * @return java.util.Map<java.lang.Object.template.core.expression.ExpressionHandle>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:11
     */
    Map<Object, ExprHandle> getExprHandles();

    /**
     * 获取全部模版工厂
     *
     * @param
     * @return java.util.Map<java.lang.String, com.join.template.core.factory.template.TemplateFactory>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/6 15:32
     */
    Map<String, TemplateFactory> getTemplateFactorys();

    /**
     * 获取表达式执行器
     *
     * @param
     * @return com.join.template.core.expression.Expression
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    ExprActuator createExprActuator();

    /**
     * 获取解析器
     *
     * @param
     * @param template
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 10:06
     */
    Parser createParser(Template template);


    /**
     * 获取表达式属性处理器
     *
     * @param
     * @return com.join.template.core.expression.ExprAttr
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/26 12:12
     */
    ExprAttr createExprAttr();

    /**
     * 获取实体类语法生成器
     *
     * @param
     * @return com.join.template.core.explain.EntityGrammar
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 17:57
     */
    GrammarGenerate createGrammarGenerate();

    ;
}
