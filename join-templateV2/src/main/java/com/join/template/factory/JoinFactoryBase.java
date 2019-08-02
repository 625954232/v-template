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
import java.util.Set;

@Getter
public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<String, ExprConfig> exprConfigs = new HashMap();


    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
        init();
    }

    private void init() {
        this.templateFactorys.put(Constant.TYPE_MAP, new TemplateMapFactory(this));

        this.templateFactorys.put(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));

        this.addExprConfig(Constant.EXPR_ROOT, null, null, null, new Processs(this));

        this.addExprConfig(Constant.EXPR_IF_ROOT, null, null, null, new IfRootProcess(this));

        this.addExprConfig(Constant.EXPR_TEXT, null, null, null, new TextProcess(this));

        this.addExprConfig(Constant.EXPR_VAR, configuration.getVarTagStart(), configuration.getVarTagEnd(), configuration.getVarTagEnd(),
                new VarcharProcess(this));

        this.addExprConfig(Constant.EXPR_LIST, "list", true, new ListProcess(this));
        this.addListener(Constant.EXPR_LIST, new ListParserListener(this));

        this.addExprConfig(Constant.EXPR_IF, "if", true, new IfProcess(this));

        this.addExprConfig(Constant.EXPR_IF_ELSE, "else", false, new IfElseProcess(this));

        this.addExprConfig(Constant.EXPR_IF_ELSE_IF, "elseif", false, new ElseIfProcess(this));

        this.addExprConfig(Constant.EXPR_INCLUDE, "include", false, new IncludeProcess(this));

        this.addExprConfig(Constant.EXPR_SET, "set", false, new SetProcess(this));
        this.addListener(Constant.EXPR_SET, new SetParserListener(this));

        this.addExprConfig(Constant.EXPR_GET, "get", false, new GetProcess(this));
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
     * @param exprConfig
     * @return
     */
    @Override
    public JoinFactory addExprConfig(ExprConfig exprConfig) {
        this.exprConfigs.put(exprConfig.getNodeType(), exprConfig);
        return this;
    }

    /**
     * 新增表达式配置
     * @param nodeType
     * @param tag
     * @param hasEndTag
     * @param process
     * @return
     */
    @Override
    public JoinFactory addExprConfig(String nodeType, String tag, boolean hasEndTag, Process process) {
        ExprConfig exprConfig = new ExprConfig();
        exprConfig.setNodeType(nodeType);
        exprConfig.setCompareTag(configuration.getExprFirstBegin() + tag);
        exprConfig.setCompareEndTag(configuration.getExprEndSupport());
        exprConfig.setProcess(process);
        if (hasEndTag) {
            exprConfig.setEndTag(configuration.getExprLastBegin() + tag + configuration.getExprEndSupport());
        }
        this.exprConfigs.put(nodeType, exprConfig);
        return this;
    }

    /**
     * 新增表达式配置
     * @param nodeType
     * @param compareTag
     * @param compareEndTag
     * @param endTag
     * @param process
     * @return
     */
    @Override
    public JoinFactory addExprConfig(String nodeType, String compareTag, String compareEndTag, String endTag, Process process) {
        ExprConfig exprConfig = new ExprConfig();
        exprConfig.setNodeType(nodeType);
        exprConfig.setCompareTag(compareTag);
        exprConfig.setCompareEndTag(compareEndTag);
        exprConfig.setEndTag(endTag);
        exprConfig.setProcess(process);
        this.exprConfigs.put(nodeType, exprConfig);
        return this;
    }

    /**
     * 新增表达式处理器
     *
     * @param nodeType
     * @param process
     * @return
     */
    @Override
    public JoinFactory addProcess(String nodeType, Process process) {
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        exprConfig.setProcess(process);
        return this;
    }

    /**
     * 新增表达式解析监听
     *
     * @param nodeType
     * @param parserListener
     * @return
     */
    @Override
    public JoinFactory addListener(String nodeType, ParserListener parserListener) {
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        exprConfig.addParserListener(parserListener);
        return null;
    }

    /**
     * 新增表达式处理监听
     *
     * @param nodeType
     * @param processListener
     * @return
     */
    @Override
    public JoinFactory addListener(String nodeType, ProcessListener processListener) {
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        exprConfig.addProcessListener(processListener);
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
     * 根据内容获取表达式配置
     *
     * @param text
     * @return
     */
    @Override
    public ExprConfig hasExpression(String text) {
        for (Map.Entry<String, ExprConfig> entry : exprConfigs.entrySet()) {
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
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        return exprConfig.getProcess();
    }

    /**
     * 根据节点类型获取解析监听
     *
     * @param nodeType
     * @return
     */
    public Set<ParserListener> getParserListeners(String nodeType) {
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        return exprConfig.getParserListeners();
    }


    /**
     * 根据节点类型获取处理监听
     *
     * @param nodeType
     * @return
     */
    public Set<ProcessListener> getProcessListeners(String nodeType) {
        ExprConfig exprConfig = exprConfigs.get(nodeType);
        Assert.isNull(exprConfig, "请新增%s对应的表达式配置", nodeType);
        return exprConfig.getProcessListeners();
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

}
