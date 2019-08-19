package com.join.template.core.expression;

import com.join.template.core.grammar.GrammarExpl;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExpressionHandle implements ExpressionHandle {
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
    protected GrammarExpl grammarExpl;

    /**
     * 解析监听
     */
    protected List<ParserListener> parserListeners = new ArrayList<>();

    /**
     * 处理器监听
     */
    protected List<ProcessListener> processListeners = new ArrayList<>();


    public AbstractExpressionHandle(String tag, Integer nodeType, Process process, GrammarExpl grammarExpl) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.process = process;
        this.grammarExpl = grammarExpl;
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
    public GrammarExpl getGrammarExpl() {
        return grammarExpl;
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
