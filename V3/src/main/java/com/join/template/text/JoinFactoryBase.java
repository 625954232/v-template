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
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;
import com.join.template.text.expression.DefaultExpression;
import com.join.template.text.grammar.*;
import com.join.template.text.parser.DefaultParser;
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

    public JoinFactoryBase() {
        this.configuration = TemplateUtil.getConfiguration();
    }

    @Override
    public void init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory());
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory());

        this.addExprConfig(Constant.EXPR_ROOT, null, new DefaultParser(), new Processs(), null);
        this.addExprConfig(Constant.EXPR_TEXT, null, new DefaultParser(), new TextProcess(), null);
        this.addExprConfig(Constant.EXPR_VAR, null, new DefaultParser(), new VarcharProcess(), null);
        this.addExprConfig(Constant.EXPR_LIST, "list", new DefaultParser(), new ListProcess(), new ListGrammarExpl());
        this.addExprConfig(Constant.EXPR_IF, "if", new DefaultParser(), new IfProcess(), new IfGrammarExpl());
        this.addExprConfig(Constant.EXPR_IF_ELSE, "else", new DefaultParser(), new IfElseProcess(), null);
        this.addExprConfig(Constant.EXPR_IF_ELSE_IF, "elseif", new DefaultParser(), new ElseIfProcess(), new ElseIfGrammarExpl());
        this.addExprConfig(Constant.EXPR_INCLUDE, "include", new DefaultParser(), new IncludeProcess(), new IncludeGrammarExpl());
        this.addExprConfig(Constant.EXPR_SET, "set", new DefaultParser(), new SetProcess(), new SetGrammarExpl());
        this.addExprConfig(Constant.EXPR_GET, "get", new DefaultParser(), new GetProcess(), new GetGrammarExpl());
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
    public JoinFactory addExprConfig(Integer nodeType, String tag, Parser parser, Process process, GrammarExpl grammar) {
        ExprConfig exprConfig = new ExprConfig(tag, nodeType, parser, process, grammar);
        this.exprConfigTags.put(tag, exprConfig);
        this.exprConfigTypes.put(nodeType, exprConfig);
        return this;
    }

    /**
     * 新增解析监听
     *
     * @param nodeType
     * @param parserListener
     * @return
     */
    @Override
    public JoinFactory addListener(Integer nodeType, ParserListener parserListener) {
        if (!exprConfigTypes.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprConfig exprConfig = exprConfigTypes.get(nodeType);
        exprConfig.getParserListeners().add(parserListener);
        return this;
    }

    /**
     * 新增处理监听
     *
     * @param nodeType
     * @param processListener
     * @return
     */
    @Override
    public JoinFactory addListener(Integer nodeType, ProcessListener processListener) {
        if (!exprConfigTypes.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprConfig exprConfig = exprConfigTypes.get(nodeType);
        exprConfig.getProcessListeners().add(processListener);
        return this;
    }

    /**
     * 加载语法解释
     *
     * @return
     */
    @Override
    public JoinFactory loadGrammar() {
        grammars.clear();
        for (Map.Entry<Integer, ExprConfig> configEntry : exprConfigTypes.entrySet()) {
            ExprConfig exprConfig = configEntry.getValue();
            GrammarExpl grammar = exprConfig.getGrammar();
            if (grammar != null) {
                String str = getGrammar(exprConfig, grammar.getGrammarAttr());
                grammars.put(configEntry.getKey(), str);
            } else {
                String str = getGrammar(exprConfig, null);
                grammars.put(configEntry.getKey(), str);
            }
        }
        return this;
    }

    /**
     * 缓存模版内容
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    @Override
    public JoinFactory putTemplate(String name, String content) {
        TemplateFactory templateFactory = templateFactorys.get(configuration.getType());
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        templateFactory.putTemplate(name, content);
        return this;
    }

    /**
     * 根据模版文件地址缓存模版内容
     *
     * @param fileName 文件路径
     * @return
     */
    @Override
    public JoinFactory putTemplate(String fileName) {
        TemplateFactory templateFactory = templateFactorys.get(configuration.getType());
        Assert.isNull(templateFactory, "没该" + configuration.getType() + "类型模版工厂");
        templateFactory.putTemplate(fileName);
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
        return new DefaultReader();
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

    /**
     * 获取语法解释
     *
     * @param nodeType
     * @param grammarAttr
     * @return
     */
    @Override
    public String getGrammar(Integer nodeType, Map<String, String> grammarAttr) {
        ExprConfig exprConfig = exprConfigTypes.get(nodeType);
        String grammar = getGrammar(exprConfig, grammarAttr);
        return grammar;
    }

    /**
     * 获取语法解释
     *
     * @param exprConfig
     * @param grammarAttr
     * @return
     */
    @Override
    public String getGrammar(ExprConfig exprConfig, Map<String, String> grammarAttr) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(exprConfig.getTag())) {
            return null;
        }
        builder.append(configuration.getExprFirstBegin());
        builder.append(exprConfig.getTag());
        if (grammarAttr != null) {
            for (Map.Entry<String, String> grammarEntry : grammarAttr.entrySet()) {
                builder.append(" ").append(grammarEntry.getKey()).append("=\"").append(grammarEntry.getValue()).append("\"");
            }
        }
        builder.append(configuration.getExprEndSupport());
        if (Constant.EXPR_IF == exprConfig.getNodeType() || Constant.EXPR_LIST == exprConfig.getNodeType()) {
            builder.append("请在这输入你需要生成的内容");
            builder.append(configuration.getExprLastBegin());
            builder.append(exprConfig.getTag());
            builder.append(configuration.getExprEndSupport());
        }
        return builder.toString();
    }
}
