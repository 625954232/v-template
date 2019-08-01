package com.join.template.configuration;

import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.process.Process;
import lombok.Data;

@Data
public class ExpressionConfig {
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
    private ParserListener parserListener;
    /**
     * 处理监听
     */
    private ProcessListener processListener;

    public ExpressionConfig(String nodeType, String compareTag, String compareEndTag, String endTag) {
        this.nodeType = nodeType;
        this.compareTag = compareTag;
        this.compareEndTag = compareEndTag;
        this.endTag = endTag;
    }

    public ExpressionConfig(String nodeType, String compareTag, String compareEndTag, String endTag, Process process, ParserListener parserListener, ProcessListener processListener) {
        this.nodeType = nodeType;
        this.compareTag = compareTag;
        this.compareEndTag = compareEndTag;
        this.endTag = endTag;
        this.process = process;
        this.parserListener = parserListener;
        this.processListener = processListener;
    }


}
