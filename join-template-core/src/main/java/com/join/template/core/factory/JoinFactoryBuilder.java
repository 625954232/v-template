package com.join.template.core.factory;

import com.join.template.core.Parser;
import com.join.template.core.expression.Expression;
import com.join.template.core.grammar.GrammarAttr;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.grammar.handle.GrammarBuilder;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.grammar.generate.GrammarGenerate;

/**
 * @author CAOYOU
 * @Title: JoinFactoryBuilder
 * @date 2019/9/99:53
 */
public interface JoinFactoryBuilder {

    /**
     * 设置解析器
     *
     * @param parser
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 9:54
     */
    JoinFactoryBuilder setParser(Class<? extends Parser> parser);

    /**
     * 设置表达式属性处理器
     *
     * @param exprAttr
     * @return com.join.template.core.factory.JoinFactoryBuilder
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 10:36
     */
    JoinFactoryBuilder setExprAttr(Class<? extends GrammarAttr> exprAttr);

    /**
     * 设置值表达式执行器
     *
     * @param expression 值表达式执行器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:12
     */
    JoinFactoryBuilder setExprActuator(Class<? extends Expression> expression);

    /**
     * 设置语法生成器
     *
     * @param grammarGenerate 语法生成器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:12
     */
    JoinFactoryBuilder setGrammarGenerate(Class<? extends GrammarGenerate> grammarGenerate);

    /**
     * 新增模版工厂
     *
     * @param nodeType        节点类型
     * @param templateFactory 模版工厂
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactoryBuilder addFactory(String nodeType, TemplateFactory templateFactory);

    /**
     * 新增表达式配置
     *
     * @param grammar 表达式配置
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactoryBuilder addExprHandle(Grammar grammar);


    /**
     * 获取表达式处理建造器
     *
     * @param
     * @return com.join.template.core.expression.ExprHandleBuilder
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/6 15:29
     */
    GrammarBuilder builderExprHandle();


    /**
     * 建造表达式处理器
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/6 17:41
     */
    JoinFactoryBuilder buildExprHandle();

    /**
     * 添加模版
     *
     * @param name
     * @param text
     * @return com.join.template.core.factory.JoinFactoryBuilder
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 10:32
     */
    JoinFactoryBuilder addTemplate(String name, String text);

    /**
     * 建造模版总工厂
     *
     * @return
     */
    JoinFactory build();
}
