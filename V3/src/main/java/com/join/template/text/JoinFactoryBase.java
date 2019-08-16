package com.join.template.text;

import com.join.template.core.*;
import com.join.template.core.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.entity.ExprConfig;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.factory.template.TemplateMapFactory;
import com.join.template.core.factory.template.TemplateSingleFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;
import com.join.template.text.expression.DefaultExpression;
import com.join.template.text.parser.DefaultParser;
import com.join.template.text.parser.ListParserListener;
import com.join.template.text.process.*;
import com.join.template.text.reader.DefaultReader;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<Integer, ExprConfig> exprConfigTypes = new HashMap();
    private Map<String, ExprConfig> exprConfigTags = new HashMap();
    private Map<Integer, String> grammars = new HashMap();

    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
        init();
    }

    private void init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory(this));
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory(this));

        this.addExprConfig(Constant.EXPR_ROOT, null, new DefaultParser(this), new Processs(this), null);
        this.addExprConfig(Constant.EXPR_TEXT, null, new DefaultParser(this), new TextProcess(this), null);
        this.addExprConfig(Constant.EXPR_VAR, null, new DefaultParser(this), new VarcharProcess(this), null);
        this.addExprConfig(Constant.EXPR_LIST, "list", new DefaultParser(this), new ListProcess(this), new ListProcess(this));
        this.addListener(Constant.EXPR_LIST, new ListParserListener(this));
        this.addExprConfig(Constant.EXPR_IF, "if", new DefaultParser(this), new IfProcess(this), new IfProcess(this));
        this.addExprConfig(Constant.EXPR_IF_ELSE, "else", new DefaultParser(this), new IfElseProcess(this), null);
        this.addExprConfig(Constant.EXPR_IF_ELSE_IF, "elseif", new DefaultParser(this), new ElseIfProcess(this), new ElseIfProcess(this));
        this.addExprConfig(Constant.EXPR_INCLUDE, "include", new DefaultParser(this), new IncludeProcess(this), new IncludeProcess(this));
        this.addExprConfig(Constant.EXPR_SET, "set", new DefaultParser(this), new SetProcess(this), new SetProcess(this));
        this.addExprConfig(Constant.EXPR_GET, "get", new DefaultParser(this), new GetProcess(this), new GetProcess(this));
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


    /***
     * 新增表达式配置
     * @param nodeType
     * @param tag
     * @param parser
     * @param process
     * @param grammar
     * @return
     */
    @Override
    public JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process, Grammar grammar) {
        ExprConfig exprConfig = new ExprConfig(tag, nodeType, parser, process, grammar);
        this.exprConfigTags.put(tag, exprConfig);
        this.exprConfigTypes.put(nodeType, exprConfig);
        return this;
    }

    @Override
    public JoinFactory addListener(Integer nodeType, ParserListener parserListener) {
        if (!exprConfigTypes.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprConfig exprConfig = exprConfigTypes.get(nodeType);
        exprConfig.getParserListeners().add(parserListener);
        return this;
    }

    @Override
    public JoinFactory addListener(Integer nodeType, ProcessListener processListener) {
        if (!exprConfigTypes.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprConfig exprConfig = exprConfigTypes.get(nodeType);
        exprConfig.getProcessListeners().add(processListener);
        return this;
    }


    @Override
    public JoinFactoryBase genGrammar() {
        grammars.clear();
        for (Map.Entry<Integer, ExprConfig> configEntry : exprConfigTypes.entrySet()) {
            ExprConfig config = configEntry.getValue();
            Grammar grammar = configEntry.getValue().getGrammar();
            StringBuilder builder = new StringBuilder();
            if (StringUtils.isBlank(config.getTag())) {
                continue;
            }
            builder.append(configuration.getExprFirstBegin());
            builder.append(config.getTag());
            if (grammar != null) {
                Map<String, String> grammarAttr = grammar.getGrammarAttr();
                for (Map.Entry<String, String> grammarEntry : grammarAttr.entrySet()) {
                    builder.append(" ").append(grammarEntry.getKey()).append("=\"").append(grammarEntry.getValue()).append("\"");
                }
            }
            builder.append(configuration.getExprEndSupport());
            grammars.put(configEntry.getKey(), builder.toString());
        }
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
     * 根据标记获取表达式配置
     *
     * @param tag
     * @return
     */
    @Override
    public ExprConfig getExprConfigByTag(String tag) {
        return exprConfigTags.get(tag);
    }

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType
     * @return
     */
    @Override
    public ExprConfig getExprConfigByType(Integer nodeType) {
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

    /**
     * 获取语法解释
     *
     * @return
     */
    @Override
    public Map<Integer, String> getGrammars() {
        return grammars;
    }
}
