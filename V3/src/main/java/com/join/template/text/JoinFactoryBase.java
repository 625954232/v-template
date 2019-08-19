package com.join.template.text;

import com.join.template.core.*;
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
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class JoinFactoryBase implements JoinFactory {
    private Configuration configuration;
    private Map<String, TemplateFactory> templateFactorys = new HashMap();
    private Map<Object, ExpressionHandle> expressionHandles = new HashMap();
    private Map<Integer, String> grammars = new HashMap();

    public JoinFactoryBase(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public JoinFactory init() {
        this.addFactory(Constant.TYPE_MAP, new TemplateMapFactory());
        this.addFactory(Constant.TYPE_SINGLE, new TemplateSingleFactory());

        this.addExpressionHandle(Constant.EXPR_ROOT, null, new Processs(), null);
        this.addExpressionHandle(Constant.EXPR_TEXT, null, new TextProcess(), null);
        this.addExpressionHandle(Constant.EXPR_VAR, null, new VarcharProcess(), null);
        this.addExpressionHandle(Constant.EXPR_LIST, "list", new ListProcess(), new ListGrammarExpl());
        this.addExpressionHandle(Constant.EXPR_IF, "if", new IfProcess(), new IfGrammarExpl());
        this.addExpressionHandle(Constant.EXPR_IF_ELSE, "else", new IfElseProcess(), null);
        this.addExpressionHandle(Constant.EXPR_IF_ELSE_IF, "elseif", new ElseIfProcess(), new ElseIfGrammarExpl());
        this.addExpressionHandle(Constant.EXPR_INCLUDE, "include", new IncludeProcess(), new IncludeGrammarExpl());
        this.addExpressionHandle(Constant.EXPR_SET, "set", new SetProcess(), new SetGrammarExpl());
        this.addExpressionHandle(Constant.EXPR_GET, "get", new GetProcess(), new GetGrammarExpl());
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
    public JoinFactory addExpressionHandle(Integer nodeType, String tag, Process process, GrammarExpl grammar) {
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
     * 加载语法解释
     *
     * @return
     */
    @Override
    public JoinFactory loadGrammar() {
        grammars.clear();
        for (Map.Entry<Object, ExpressionHandle> configEntry : expressionHandles.entrySet()) {
            ExpressionHandle expressionHandle = configEntry.getValue();
            GrammarExpl grammar = expressionHandle.getGrammarExpl();
            if (!(configEntry.getKey() instanceof Integer)) {
                continue;
            }
            if (grammar != null) {
                String str = getGrammar(expressionHandle, grammar.getElementAttrExpl());
                grammars.put((Integer) configEntry.getKey(), str);
            } else {
                String str = getGrammar(expressionHandle, null);
                grammars.put((Integer) configEntry.getKey(), str);
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
        ExpressionHandle expressionHandle = expressionHandles.get(nodeType);
        String grammar = getGrammar(expressionHandle, grammarAttr);
        return grammar;
    }

    /**
     * 获取语法解释
     *
     * @param expressionHandle
     * @param grammarAttr
     * @return
     */
    @Override
    public String getGrammar(ExpressionHandle expressionHandle, Map<String, String> grammarAttr) {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isBlank(expressionHandle.getTag())) {
            return null;
        }
        builder.append(configuration.getExprFirstBegin());
        builder.append(expressionHandle.getTag());
        if (grammarAttr != null) {
            for (Map.Entry<String, String> grammarEntry : grammarAttr.entrySet()) {
                builder.append(" ").append(grammarEntry.getKey()).append("=\"").append(grammarEntry.getValue()).append("\"");
            }
        }
        builder.append(configuration.getExprEndSupport());
        GrammarExpl grammarExpl = expressionHandle.getGrammarExpl();
        if (grammarExpl != null) {
            grammarExpl.verifyElement(builder.toString(), false, grammarAttr);
        }
        if (Constant.EXPR_IF == expressionHandle.getNodeType() || Constant.EXPR_LIST == expressionHandle.getNodeType()) {
            builder.append("请在这输入你需要生成的内容");
            builder.append(configuration.getExprLastBegin());
            builder.append(expressionHandle.getTag());
            builder.append(configuration.getExprEndSupport());
        }
        return builder.toString();
    }
}
