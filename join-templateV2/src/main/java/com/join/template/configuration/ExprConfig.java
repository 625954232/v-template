package com.join.template.configuration;

import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ExprConfig {
    /**
     * 节点类型
     */
    private String nodeType;
    /**
     * 用于查询标签开始元素起始位置
     */
    private String compareTag;
    /**
     * 用于查询标签开始元素结束位置
     */
    private String compareEndTag;
    /**
     * 标签结束元素
     */
    private String endTag;
    /**
     * 处理器
     */
    private Process process;
    /**
     * 解析监听
     */
    private Set<ParserListener> parserListeners = new HashSet<>();
    /**
     * 处理监听
     */
    private Set<ProcessListener> processListeners = new HashSet<>();

    /**
     * 新增解析监听
     *
     * @param parserListener
     */
    public void addParserListener(ParserListener parserListener) {
        parserListeners.add(parserListener);
    }

    /**
     * 新增处理监听
     *
     * @param processListener
     */
    public void addProcessListener(ProcessListener processListener) {
        processListeners.add(processListener);
    }
}
