package com.join.template.factory;

import com.join.template.core.Template;
import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExpressionConfig;
import com.join.template.constant.Constant;
import com.join.template.expression.DefaultExpression;
import com.join.template.expression.Expression;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.factory.template.TemplateMapFactory;
import com.join.template.factory.template.TemplateSingleFactory;
import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.listener.parser.ListParserListener;
import com.join.template.listener.parser.SetParserListener;
import com.join.template.process.*;
import com.join.template.process.Process;
import com.join.template.verify.Assert;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<String, ExpressionConfig> expressionConfigs = new HashMap();


    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
        init();
    }

    private void init() {
        this.templateFactorys.put(Constant.TYPE_MAP, new TemplateMapFactory(this));
        this.templateFactorys.put(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));

        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_ROOT, null, null,
                null, new Processs(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_TEXT, null, null,
                null, new TextProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_LIST, getExprStart("list"), configuration.getExprEndSupport(),
                getExprEnd("list"), new ListProcess(this), new ListParserListener(this), null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_IF, getExprStart("if"), configuration.getExprEndSupport(),
                getExprEnd("if"), new IfProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_IF_ROOT, null, null,
                null, new IfRootProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_IF_ELSE, getExprStart("else"), configuration.getExprEndSupport(),
                null, new IfElseProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_IF_ELSE_IF, getExprStart("elseif"), configuration.getExprEndSupport(),
                null, new ElseIfProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_INCLUDE, getExprStart("include"), configuration.getExprEndSupport(),
                null, new IncludeProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_VAR, configuration.getVarTagStart(), configuration.getVarTagEnd(),
                configuration.getVarTagEnd(), new VarcharProcess(this), null, null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_SET, getExprStart("set"), configuration.getExprEndSupport(),
                null, new SetProcess(this), new SetParserListener(this), null));
        this.addConfig(new ExpressionConfig(Constant.EXPRESSION_GET, getExprStart("get"), configuration.getExprEndSupport(),
                null, new GetProcess(this), null, null));
    }


    @Override
    public JoinFactory addFactory(String nodeType, TemplateFactory templateFactory) {
        this.templateFactorys.put(nodeType, templateFactory);
        return this;
    }

    @Override
    public JoinFactory addConfig(ExpressionConfig expressionConfig) {
        this.expressionConfigs.put(expressionConfig.getNodeType(), expressionConfig);
        return this;
    }

    @Override
    public JoinFactory addConfig(String nodeType, String compareTag, String compareEndTag, String endTag) {
        ExpressionConfig expressionConfig = new ExpressionConfig(nodeType, compareTag, compareEndTag, endTag);
        this.expressionConfigs.put(nodeType, expressionConfig);
        return this;
    }

    @Override
    public JoinFactory addProcess(String nodeType, Process process) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        expressionConfig.setProcess(process);
        return this;
    }

    @Override
    public JoinFactory addListener(String nodeType, ParserListener parserListener) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        expressionConfig.setParserListener(parserListener);
        return null;
    }

    @Override
    public JoinFactory addListener(String nodeType, ProcessListener processListener) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        expressionConfig.setProcessListener(processListener);
        return this;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * 根据内容获取表达式配置
     *
     * @param text
     * @return
     */
    @Override
    public ExpressionConfig hasExpression(String text) {
        for (Map.Entry<String, ExpressionConfig> entry : expressionConfigs.entrySet()) {
            if (StringUtils.isBlank(entry.getValue().getCompareTag())) {
                continue;
            }
            if (text.startsWith(entry.getValue().getCompareTag())) {
                return entry.getValue();
            }
        }
        return null;
    }


    /**
     * 根据节点类型获取处理器
     *
     * @param nodeType
     * @return
     */
    public Process getProcess(String nodeType) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        return expressionConfig.getProcess();
    }

    /**
     * 根据节点类型获取解析监听
     *
     * @param nodeType
     * @return
     */
    public ParserListener getParserListeners(String nodeType) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        return expressionConfig.getParserListener();
    }


    /**
     * 根据节点类型获取处理监听
     *
     * @param nodeType
     * @return
     */
    public ProcessListener getProcessListeners(String nodeType) {
        ExpressionConfig expressionConfig = expressionConfigs.get(nodeType);
        Assert.isNull(expressionConfig, "请新增%s对应的表达式配置", nodeType);
        return expressionConfig.getProcessListener();
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

    private String getExprEnd(String tag) {
        return configuration.getExprLastBegin() + tag + configuration.getExprEndSupport();
    }

    private String getExprStart(String tag) {
        return configuration.getExprFirstBegin() + tag;
    }
}
