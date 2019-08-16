package com.join.template.core;


import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.process.Process;

import java.util.List;

public interface ExpressionHandle extends Parser, Reader {
    /**
     * 获取标记
     *
     * @return
     */
    String getTag();

    /**
     * 获取节点类型
     *
     * @return
     */
    Integer getNodeType();

    /**
     * 获取处理器
     *
     * @return
     */
    Process getProcess();

    /**
     * 获取语法解释器
     *
     * @return
     */
    GrammarExpl getGrammarExpl();

    /**
     * 获取解析监听
     *
     * @return
     */
    List<ParserListener> getParserListeners();

    /**
     * 获取处理监听
     *
     * @return
     */
    List<ProcessListener> getProcessListeners();
}
