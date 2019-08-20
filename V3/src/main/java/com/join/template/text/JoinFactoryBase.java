package com.join.template.text;

import com.join.template.core.*;
import com.join.template.core.expression.Expression;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.grammar.generate.EntityGenerate;
import com.join.template.core.grammar.Explain;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.process.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.text.expression.DefaultExpressionHandle;
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
import com.join.template.text.grammar.*;
import com.join.template.text.process.*;
import com.join.template.text.reader.DefaultReader;

import java.util.HashMap;
import java.util.Map;


public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<Object, ExpressionHandle> expressionHandles = new HashMap();
    private Map<Integer, String> grammars = new HashMap();

    private Reader reader;
    private Expression expression;
    private GrammarGenerate grammarGenerate;


    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public JoinFactory init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory());
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory());

        this.addExpressionHandle(Constant.EXPR_ROOT, null, new Processs(), null);
        this.addExpressionHandle(Constant.EXPR_TEXT, null, new TextProcess(), null);
        this.addExpressionHandle(Constant.EXPR_VAR, null, new VarcharProcess(), new VarcharExplain());
        this.addExpressionHandle(Constant.EXPR_LIST, "list", new ListProcess(), new ListExplain());
        this.addExpressionHandle(Constant.EXPR_IF, "if", new IfProcess(), new IfExplain());
        this.addExpressionHandle(Constant.EXPR_IF_ELSE, "else", new IfElseProcess(), null);
        this.addExpressionHandle(Constant.EXPR_IF_ELSE_IF, "elseif", new ElseIfProcess(), new ElseIfExplain());
        this.addExpressionHandle(Constant.EXPR_INCLUDE, "include", new IncludeProcess(), new IncludeExplain());
        this.addExpressionHandle(Constant.EXPR_SET, "set", new SetProcess(), new SetExplain());
        this.addExpressionHandle(Constant.EXPR_GET, "get", new GetProcess(), new GetExplain());
        return this;
    }

    /**
     * 初始化语法解释
     *
     * @return
     */
    @Override
    public JoinFactory initGrammarExplain() {
        for (ExpressionHandle expressionHandle : expressionHandles.values()) {
            Explain grammarExpl = expressionHandle.getGrammarExpl();
            if (grammarExpl != null) {
                grammars.put(expressionHandle.getNodeType(), grammarExpl.getGrammarExplain());
            }
        }
        return this;
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
     * @param expression
     * @return
     */
    @Override
    public JoinFactory setExpression(Expression expression) {
        this.expression = expression;
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


    /***
     * 新增表达式配置
     * @param nodeType
     * @param tag
     * @param process
     * @param grammar
     * @return
     */
    @Override
    public JoinFactory addExpressionHandle(Integer nodeType, String tag, Process process, Explain grammar) {
        ExpressionHandle expressionHandle = new DefaultExpressionHandle(tag, nodeType, process, grammar);
        this.expressionHandles.put(tag, expressionHandle);
        this.expressionHandles.put(nodeType, expressionHandle);
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
        if (!expressionHandles.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExpressionHandle expressionHandle = expressionHandles.get(nodeType);
        expressionHandle.getParserListeners().add(parserListener);
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
        if (!expressionHandles.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExpressionHandle expressionHandle = expressionHandles.get(nodeType);
        expressionHandle.getProcessListeners().add(processListener);
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
    public ExpressionHandle getExpressionHandle(String tag) {
        return expressionHandles.get(tag);
    }

    /**
     * 根据节点类型获取表达式配置
     *
     * @param nodeType
     * @return
     */
    @Override
    public ExpressionHandle getExpressionHandle(Integer nodeType) {
        return expressionHandles.get(nodeType);
    }

    /**
     * 获取全部表达式配置
     *
     * @return
     */
    @Override
    public Map<Object, ExpressionHandle> getExpressionHandles() {
        return expressionHandles;
    }

    /**
     * 获取表达式执行器
     *
     * @return
     */
    @Override
    public Expression getExpression() {
        if (expression == null)
            expression = new DefaultExpression();
        return expression;
    }

    /**
     * 获取解析器
     *
     * @return
     */
    @Override
    public Reader getReader() {
        if (reader == null)
            reader = new DefaultReader();
        return reader;
    }


    /**
     * 获取实体类语法生成器
     *
     * @return
     */
    @Override
    public GrammarGenerate getGrammarGenerate() {
        if (grammarGenerate == null)
            grammarGenerate = new EntityGenerate();
        return grammarGenerate;
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
