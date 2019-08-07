package com.join.template.configuration;


import com.join.template.listener.ParserListener;
import com.join.template.listener.ProcessListener;
import com.join.template.parser.Parser;
import com.join.template.process.Process;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ExprConfig {
    /**
     * 标记
     */
    private String tag;
    /**
     * 标记
     */
    private Integer nodeType;
    /**
     * 解析器
     */
    private Parser parser;
    /**
     * 处理器
     */
    private Process process;

    /**
     * 解析监听
     */
    private List<ParserListener> parserListeners = new ArrayList<>();

    /**
     * 处理器监听
     */
    private List<ProcessListener> processListeners = new ArrayList<>();

    public ExprConfig(String tag, Integer nodeType, Parser parser, Process process) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.parser = parser;
        this.process = process;
    }

    public ExprConfig(String tag, Integer nodeType, Parser parser, Process process, List<ParserListener> parserListeners, List<ProcessListener> processListeners) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.parser = parser;
        this.process = process;
        this.parserListeners = parserListeners;
        this.processListeners = processListeners;
    }
}
