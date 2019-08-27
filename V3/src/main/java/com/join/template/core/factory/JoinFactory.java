package com.join.template.core.factory;

import com.join.template.core.*;
import com.join.template.core.expression.Expr;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.explain.Explain;
import com.join.template.core.grammar.GrammarGenerate;
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
     * 初始化语法解释
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 11:33
     */
    JoinFactory initGrammarExplain();

    /**
     * 设置读取器
     *
     * @param reader 读取器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:13
     */
    JoinFactory setReader(Class<? extends Reader> reader);

    /**
     * 设置值表达式执行器
     *
     * @param expression 值表达式执行器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:12
     */
    JoinFactory setExpr(Class<? extends Expr> expression);

    /**
     * 设置语法生成器
     *
     * @param grammarGenerate 语法生成器
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:12
     */
    JoinFactory setGrammarGenerate(Class<? extends GrammarGenerate> grammarGenerate);

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
     * @param exprAttr
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:46
     */
    JoinFactory addExprHandle(Integer nodeType, String tag, Process process, Explain grammar, ExprAttr exprAttr);

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
     * 获取表达式执行器
     *
     * @param
     * @return com.join.template.core.expression.Expression
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:49
     */
    Expr getExpr();

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
     * 获取实体类语法生成器
     *
     * @param
     * @return com.join.template.core.explain.EntityGrammar
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 17:57
     */
    GrammarGenerate getGrammarGenerate();

    /**
     * 获取全部语法示例
     *
     * @param
     * @return java.util.Map<java.lang.Integer                               ,                               java.lang.String>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 16:09
     */
    Map<Integer, String> getGrammars();
}
