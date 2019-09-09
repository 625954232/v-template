package com.join.template.core.expression;

import com.join.template.core.Parser;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.explain.Explain;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;

import java.util.ArrayList;
import java.util.List;

public class TemplateExprHandle implements ExprHandle, ExprHandleBuilder {
    protected final JoinFactory joinFactory;
    protected final Configuration configuration;

    /**
     * 标记
     */
    protected String tag;
    /**
     * 标记
     */
    protected Integer nodeType;
    /**
     * 处理器
     */
    protected Process process;

    /**
     * 语法示例
     */
    protected Explain explain;

    /**
     * 表达式属性处理器
     */
    protected ExprAttr exprAttr;
    /**
     * 解析监听
     */
    protected List<ParserListener> parserListeners = new ArrayList<>();

    /**
     * 处理器监听
     */
    protected List<ProcessListener> processListeners = new ArrayList<>();

    public TemplateExprHandle(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
    }

    @Override
    public ExprHandleBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public ExprHandleBuilder nodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public ExprHandleBuilder process(Process process) {
        this.process = process;
        return this;
    }

    @Override
    public ExprHandleBuilder explain(Explain explain) {
        this.explain = explain;
        return this;
    }


    @Override
    public ExprHandleBuilder addParserListeners(ParserListener parserListener) {
        this.parserListeners.add(parserListener);
        return this;
    }

    @Override
    public ExprHandleBuilder addProcessListeners(ProcessListener parserListener) {
        this.processListeners.add(parserListener);
        return this;
    }

    @Override
    public JoinFactoryBuilder addIn(JoinFactoryBuilder joinFactoryBuilder) {
        return joinFactoryBuilder.addExprHandle(this);
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public Integer getNodeType() {
        return nodeType;
    }

    @Override
    public Process getProcess() {
        return process;
    }

    @Override
    public Explain getExplain() {
        return explain;
    }


    @Override
    public List<ParserListener> getParserListeners() {
        return parserListeners;
    }

    @Override
    public List<ProcessListener> getProcessListeners() {
        return processListeners;
    }


}
