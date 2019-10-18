package com.join.template.core.factory;

import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.*;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.factory.template.TemplateMapFactory;
import com.join.template.core.factory.template.TemplateSingleFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.generate.EntityGenerate;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;


import java.util.HashMap;
import java.util.Map;


public abstract class AbstractJoinFactory implements JoinFactoryBuilder {

    protected Configuration configuration;
    protected Map<String, TemplateFactory> templateFactorys = new HashMap();
    protected Map<Object, ExprHandle> exprHandles = new HashMap();
    protected ExprHandleBuilder exprHandleBuilder;
    protected Class<? extends Parser> parser;
    protected Class<? extends ExprActuator> exprActuator;
    protected Class<? extends ExprAttr> exprAttr;
    protected Class<? extends GrammarGenerate> grammarGenerate;


    public AbstractJoinFactory(Configuration configuration) {
        this.configuration = configuration;
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory(this.build()));
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory(this.build()));
        this.setExprAttr(TemplateExprAttr.class);
        this.setExprActuator(DefaultExpression.class);
        this.setGrammarGenerate(EntityGenerate.class);
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
    public JoinFactoryBuilder setParser(Class<? extends Parser> parser) {
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
    public JoinFactoryBuilder setExprAttr(Class<? extends ExprAttr> exprAttr) {
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
    public JoinFactoryBuilder setExprActuator(Class<? extends ExprActuator> exprActuator) {
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
    public JoinFactoryBuilder setGrammarGenerate(Class<? extends GrammarGenerate> grammarGenerate) {
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
        return this.exprHandleBuilder = new TemplateExprHandle(this.build());
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

    protected abstract Template putTemplate(String name, String text);

    protected abstract Template getTemplate(String name);

    /**
     * 建造模版总工厂
     *
     * @return
     */
    @Override
    public JoinFactory build() {
        return joinFactory;
    }

    protected JoinFactory joinFactory = new JoinFactory() {

        @Override
        public Template putTemplate(String name, String text) {
            return AbstractJoinFactory.this.putTemplate(name, text);
        }

        @Override
        public Template getTemplate(String name) {
            return AbstractJoinFactory.this.getTemplate(name);
        }

        @Override
        public Configuration getConfiguration() {
            return configuration;
        }

        @Override
        public TemplateFactory getTemplateFactorys(String type) {
            TemplateFactory templateFactory = templateFactorys.get(type);
            Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
            return templateFactory;
        }

        @Override
        public ExprHandle getExprHandle(String tag) {
            return exprHandles.get(tag);
        }

        @Override
        public ExprHandle getExprHandle(Integer nodeType) {
            return exprHandles.get(nodeType);
        }

        @Override
        public Map<Object, ExprHandle> getExprHandles() {
            return exprHandles;
        }

        @Override
        public Map<String, TemplateFactory> getTemplateFactorys() {
            return templateFactorys;
        }

        @Override
        public ExprActuator createExprActuator() {
            try {
                ExprActuator exprActuator = AbstractJoinFactory.this.exprActuator.newInstance();
                exprActuator.setJoinFactory(this);
                return exprActuator;
            } catch (Exception e) {
                throw new TemplateException("创建失败", e);
            }
        }

        @Override
        public ExprAttr createExprAttr() {
            try {
                ExprAttr exprAttr = AbstractJoinFactory.this.exprAttr.newInstance();
                exprAttr.setJoinFactory(this);
                return exprAttr;
            } catch (Exception e) {
                throw new TemplateException("创建失败", e);
            }
        }

        @Override
        public Parser createParser() {
            try {
                Parser parser = AbstractJoinFactory.this.parser.newInstance();
                parser.setJoinFactory(this);
                return parser;
            } catch (Exception e) {
                throw new TemplateException("创建失败", e);
            }
        }

        @Override
        public GrammarGenerate createGrammarGenerate() {
            try {
                GrammarGenerate grammarGenerate = AbstractJoinFactory.this.grammarGenerate.newInstance();
                grammarGenerate.setJoinFactory(this);
                return grammarGenerate;
            } catch (Exception e) {
                throw new TemplateException("创建失败", e);
            }
        }

    };

}
