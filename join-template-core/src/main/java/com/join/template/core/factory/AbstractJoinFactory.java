package com.join.template.core.factory;

import com.join.template.core.Reader;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.DefaultExpression;
import com.join.template.core.expression.ExprActuator;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.expression.ExprHandleBuilder;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.factory.template.TemplateMapFactory;
import com.join.template.core.factory.template.TemplateSingleFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.generate.EntityGenerate;
import com.join.template.core.verify.Assert;


import java.util.HashMap;
import java.util.Map;


public abstract class AbstractJoinFactory implements JoinFactory {
    protected Configuration configuration;
    protected Map<String, TemplateFactory> templateFactorys = new HashMap();
    protected Map<Object, ExprHandle> exprHandles = new HashMap();
    protected Reader reader;
    protected ExprActuator exprActuator;
    protected GrammarGenerate grammarGenerate;



    public AbstractJoinFactory(Configuration configuration) {
        this.configuration = configuration;
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory(this));
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));
        this.setExprActuator(new DefaultExpression());
        this.setGrammarGenerate(new EntityGenerate(this));
    }


    /**
     * 设置读取器
     *
     * @param reader
     * @return
     */
    @Override
    public JoinFactory setReader(Reader reader) {
        this.reader = reader;
        return this;
    }

    /**
     * 设置值表达式执行器
     *
     * @param exprActuator
     * @return
     */
    @Override
    public JoinFactory setExprActuator(ExprActuator exprActuator) {
        this.exprActuator = exprActuator;
        return this;
    }

    /**
     * 设置语法生成器
     *
     * @param grammarGenerate
     * @return
     */
    @Override
    public JoinFactory setGrammarGenerate(GrammarGenerate grammarGenerate) {
        this.grammarGenerate = grammarGenerate;
        return this;
    }

    /**
     * 新增模版工厂
     *
     * @param nodeType
     * @param templateFactory
     * @return
     */
    @Override
    public JoinFactory addFactory(String nodeType, TemplateFactory templateFactory) {
        this.templateFactorys.put(nodeType, templateFactory);
        return this;
    }

    @Override
    public JoinFactory addExprHandle(ExprHandle exprHandle) {
        this.exprHandles.put(exprHandle.getNodeType(), exprHandle);
        this.exprHandles.put(exprHandle.getTag(), exprHandle);
        return this;
    }


    /**
     * 获取配置
     *
     * @return
     */
    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * 获取模版工厂
     *
     * @param type
     * @return
     */
    @Override
    public TemplateFactory getTemplateFactorys(String type) {
        TemplateFactory templateFactory = templateFactorys.get(type);
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        return templateFactory;
    }

    /**
     * 根据标记获取表达式配置
     *
     * @param tag
     * @return
     */
    @Override
    public ExprHandle getExprHandle(String tag) {
        return exprHandles.get(tag);
    }

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType
     * @return
     */
    @Override
    public ExprHandle getExprHandle(Integer nodeType) {
        return exprHandles.get(nodeType);
    }

    /**
     * 获取全部表达式配置
     *
     * @return
     */
    @Override
    public Map<Object, ExprHandle> getExprHandles() {
        return exprHandles;
    }

    /**
     * 获取全部模版工厂
     *
     * @return
     */
    @Override
    public Map<String, TemplateFactory> getTemplateFactorys() {
        return templateFactorys;
    }

    /**
     * 获取表达式执行器
     *
     * @return
     */
    @Override
    public ExprActuator getExprActuator() {
        return exprActuator;
    }

    /**
     * 获取解析器
     *
     * @return
     */
    @Override
    public Reader getReader() {
        return reader;
    }


    /**
     * 获取实体类语法生成器
     *
     * @return
     */
    @Override
    public GrammarGenerate getGrammarGenerate() {
        return grammarGenerate;
    }


}
