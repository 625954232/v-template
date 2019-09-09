package com.join.template.core.factory;

import com.join.template.core.Parser;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.*;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.factory.template.TemplateMapFactory;
import com.join.template.core.factory.template.TemplateSingleFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.generate.EntityGenerate;
import com.join.template.core.verify.Assert;


import java.util.HashMap;
import java.util.Map;


public abstract class AbstractJoinFactory implements JoinFactory, JoinFactoryBuilder {

    protected Configuration configuration;
    protected Map<String, TemplateFactory> templateFactorys = new HashMap();
    protected Map<Object, ExprHandle> exprHandles = new HashMap();
    protected ExprHandleBuilder exprHandleBuilder;
    protected Parser parser;
    protected ExprActuator exprActuator;
    protected ExprAttr exprAttr;
    protected GrammarGenerate grammarGenerate;


    public AbstractJoinFactory(Configuration configuration) {
        this.configuration = configuration;
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory(this));
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));
        this.setExprAttr(new TemplateExprAttr(this.configuration));
        this.setExprActuator(new DefaultExpression());
        this.setGrammarGenerate(new EntityGenerate(this));
        this.init();
    }

    /**
     * 初始化
     */
    protected abstract void init();

    /**
     * 设置解析器
     *
     * @param parser
     * @return
     */
    @Override
    public JoinFactoryBuilder setParser(Parser parser) {
        this.parser = parser;
        return this;
    }

    /**
     * 设置表达式属性处理器
     *
     * @param exprAttr 表达式属性处理器
     * @return
     */
    @Override
    public JoinFactoryBuilder setExprAttr(ExprAttr exprAttr) {
        this.exprAttr = exprAttr;
        return this;
    }

    /**
     * 设置值表达式执行器
     *
     * @param exprActuator
     * @return
     */
    @Override
    public JoinFactoryBuilder setExprActuator(ExprActuator exprActuator) {
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
    public JoinFactoryBuilder setGrammarGenerate(GrammarGenerate grammarGenerate) {
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
    public JoinFactoryBuilder addFactory(String nodeType, TemplateFactory templateFactory) {
        this.templateFactorys.put(nodeType, templateFactory);
        return this;
    }

    /**
     * 新增表达式处理器
     *
     * @param exprHandle 表达式配置
     * @return
     */
    @Override
    public JoinFactoryBuilder addExprHandle(ExprHandle exprHandle) {
        this.exprHandles.put(exprHandle.getNodeType(), exprHandle);
        this.exprHandles.put(exprHandle.getTag(), exprHandle);
        return this;
    }

    /**
     * 获取表达式处理建造器
     *
     * @return
     */
    @Override
    public ExprHandleBuilder builderExprHandle() {
        return this.exprHandleBuilder = new TemplateExprHandle(this);
    }

    /**
     * 建造表达式处理器
     *
     * @return
     */
    @Override
    public JoinFactoryBuilder buildExprHandle() {
        return this.exprHandleBuilder.addIn(this);
    }

    /**
     * 添加模版
     *
     * @param name
     * @param text
     * @return
     */
    @Override
    public JoinFactoryBuilder addTemplate(String name, String text) {
        this.putTemplate(name, text);
        return this;
    }

    /**
     * 建造模版总工厂
     *
     * @return
     */
    @Override
    public JoinFactory build() {
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
     * 表达式属性处理器
     *
     * @return
     */
    @Override
    public ExprAttr getExprAttr() {
        return exprAttr;
    }

    /**
     * 获取解析器
     *
     * @return
     */
    @Override
    public Parser getParser() {
        return parser;
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
