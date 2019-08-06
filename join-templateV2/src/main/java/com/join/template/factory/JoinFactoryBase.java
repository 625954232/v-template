package com.join.template.factory;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.core.Template;
import com.join.template.expression.DefaultExpression;
import com.join.template.expression.Expression;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.factory.template.TemplateMapFactory;
import com.join.template.factory.template.TemplateSingleFactory;
import com.join.template.parser.DefaultParser;
import com.join.template.parser.Parser;
import com.join.template.process.*;
import com.join.template.process.Process;
import com.join.template.reader.DefaultReader;
import com.join.template.reader.Reader;
import com.join.template.verify.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<String, ExprConfig> exprConfigTypes = new HashMap();
    private Map<String, ExprConfig> exprConfigTags = new HashMap();


    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
        init();
    }

    private void init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory(this));
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));

        this.addExprConfig(Constant.EXPR_ROOT, null, new DefaultParser(this), new Processs(this));
        this.addExprConfig(Constant.EXPR_IF_ROOT, null, new DefaultParser(this), new IfRootProcess(this));
        this.addExprConfig(Constant.EXPR_TEXT, null, new DefaultParser(this), new TextProcess(this));
        this.addExprConfig(Constant.EXPR_VAR, null, new DefaultParser(this), new VarcharProcess(this));
        this.addExprConfig(Constant.EXPR_LIST, "list", new DefaultParser(this), new ListProcess(this));
        this.addExprConfig(Constant.EXPR_IF, "if", new DefaultParser(this), new IfProcess(this));
        this.addExprConfig(Constant.EXPR_IF_ELSE, "else", new DefaultParser(this), new IfElseProcess(this));
        this.addExprConfig(Constant.EXPR_IF_ELSE_IF, "elseif", new DefaultParser(this), new ElseIfProcess(this));
        this.addExprConfig(Constant.EXPR_INCLUDE, "include", new DefaultParser(this), new IncludeProcess(this));
        this.addExprConfig(Constant.EXPR_SET, "set", new DefaultParser(this), new SetProcess(this));
        this.addExprConfig(Constant.EXPR_GET, "get", new DefaultParser(this), new GetProcess(this));
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

    /**
     * 新增表达式配置
     *
     * @param tag
     * @param nodeType
     * @param parser
     * @param process
     * @return
     */
    @Override
    public JoinFactory addExprConfig(String nodeType, String tag, Parser parser, Process process) {
        ExprConfig exprConfig = new ExprConfig(tag, nodeType, parser, process);
        this.exprConfigTags.put(tag, exprConfig);
        this.exprConfigTypes.put(nodeType, exprConfig);
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

    @Override
    public ExprConfig getExprConfigByTag(String tag) {
        return exprConfigTags.get(tag);
    }

    @Override
    public ExprConfig getExprConfigByType(String nodeType) {
        return exprConfigTypes.get(nodeType);
    }

    /**
     * 获取表达式执行器
     *
     * @return
     */
    @Override
    public Expression getExpression() {
        return new DefaultExpression();
    }

    /**
     * 获取解析器
     *
     * @return
     */
    @Override
    public Reader getReader() {
        return new DefaultReader(this);
    }


    /**
     * 缓存模版内容
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    @Override
    public Template putTemplate(String name, String content) {
        TemplateFactory templateFactory = templateFactorys.get(configuration.getType());
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        return templateFactory.putTemplate(name, content);
    }

    /**
     * 根据模版文件地址缓存模版内容
     *
     * @param fileName 文件路径
     * @return
     */
    @Override
    public Template putTemplate(String fileName) {
        TemplateFactory templateFactory = templateFactorys.get(configuration.getType());
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        return templateFactory.putTemplate(fileName);
    }

    /**
     * 根绝模板名称获取模版
     *
     * @param name 文件名称或模板名称
     * @return
     */
    @Override
    public Template getTemplate(String name) {
        TemplateFactory templateFactory = templateFactorys.get(configuration.getType());
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        return templateFactory.getTemplate(name);
    }

}
