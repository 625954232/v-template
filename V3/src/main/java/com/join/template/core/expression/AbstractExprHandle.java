package com.join.template.core.expression;

import com.join.template.core.explain.Explain;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExprHandle implements ExprHandle {
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


    public AbstractExprHandle(String tag, Integer nodeType, Process process, Explain explain, ExprAttr exprAttr) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.process = process;
        this.explain = explain;
        this.exprAttr = exprAttr;
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
    public ExprAttr getExprAttr() {
        return exprAttr;
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
