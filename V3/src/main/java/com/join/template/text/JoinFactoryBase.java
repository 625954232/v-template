package com.join.template.text;

import com.join.template.core.*;
import com.join.template.core.expression.DefaultExprAttr;
import com.join.template.core.expression.Expr;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.grammar.generate.EntityGenerate;
import com.join.template.core.explain.Explain;
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
import com.join.template.text.explain.*;
import com.join.template.text.process.*;
import com.join.template.text.reader.DefaultReader;

import java.util.HashMap;
import java.util.Map;


public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<Object, ExprHandle> exprHandles = new HashMap();
    private Map<Integer, String> grammars = new HashMap();

    private Class<? extends Reader> reader;
    private Class<? extends Expr> expr;
    private Class<? extends GrammarGenerate> grammarGenerate;


    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public JoinFactory init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory());
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory());
        this.addExprHandle(Constant.EXPR_ROOT, null, new Processs(), null, new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_TEXT, null, new TextProcess(), null, new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_VAR, null, new VarcharProcess(), new VarcharExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_LIST, "list", new ListProcess(), new ListExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_IF, "if", new IfProcess(), new IfExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_ELSE, "else", new IfElseProcess(), null, new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_IF_ELSE_IF, "elseif", new ElseIfProcess(), new ElseIfExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_INCLUDE, "include", new IncludeProcess(), new IncludeExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_SET, "set", new SetProcess(), new SetExplain(), new DefaultExprAttr());
        this.addExprHandle(Constant.EXPR_GET, "get", new GetProcess(), new GetExplain(), new DefaultExprAttr());
        return this;
    }

    /**
     * 初始化语法解释
     *
     * @return
     */
    @Override
    public JoinFactory initGrammarExplain() {
        for (ExprHandle expressionHandle : exprHandles.values()) {
            Explain grammarExpl = expressionHandle.getExplain();
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
    public JoinFactory setReader(Class<? extends Reader> reader) {
        this.reader = reader;
        return this;
    }

    /**
     * 设置值表达式执行器
     *
     * @param expr
     * @return
     */
    @Override
    public JoinFactory setExpr(Class<? extends Expr> expr) {
        this.expr = expr;
        return this;
    }

    /**
     * 设置语法生成器
     *
     * @param grammarGenerate
     * @return
     */
    @Override
    public JoinFactory setGrammarGenerate(Class<? extends GrammarGenerate> grammarGenerate) {
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
     * @param exprAttr
     * @return
     */
    @Override
    public JoinFactory addExprHandle(Integer nodeType, String tag, Process process, Explain grammar, ExprAttr exprAttr) {
        ExprHandle expressionHandle = new DefaultExpressionHandle(tag, nodeType, process, grammar, exprAttr);
        this.exprHandles.put(tag, expressionHandle);
        this.exprHandles.put(nodeType, expressionHandle);
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
        if (!exprHandles.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprHandle expressionHandle = exprHandles.get(nodeType);
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
        if (!exprHandles.containsKey(nodeType)) {
            throw new TemplateException("请配置表达式");
        }
        ExprHandle expressionHandle = exprHandles.get(nodeType);
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
        TemplateFactory templateFactory = getTemplateFactorys(configuration.getType());
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
     * 获取表达式执行器
     *
     * @return
     */
    @Override
    public Expr getExpr() {
        try {
            if (expr == null)
                return new DefaultExpression();
            return expr.newInstance();
        } catch (InstantiationException e) {
            throw new TemplateException("加载表达式执行器失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("加载表达式执行器失败", e);
        }
    }

    /**
     * 获取解析器
     *
     * @return
     */
    @Override
    public Reader getReader() {
        try {
            if (reader == null)
                return new DefaultReader();
            return reader.newInstance();
        } catch (InstantiationException e) {
            throw new TemplateException("加载解析器失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("加载解析器失败", e);
        }
    }


    /**
     * 获取实体类语法生成器
     *
     * @return
     */
    @Override
    public GrammarGenerate getGrammarGenerate() {
        try {
            if (grammarGenerate == null)
                return new EntityGenerate();
            return grammarGenerate.newInstance();
        } catch (InstantiationException e) {
            throw new TemplateException("加载实体类语法生成器失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("加载实体类语法生成器失败", e);
        }
    }

    /**
     * 获取全部语法示例
     *
     * @return
     */
    @Override
    public Map<Integer, String> getGrammars() {
        return grammars;
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
