package com.join.template.core;


import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.process.Process;

import java.util.List;

public interface ExpressionHandle extends Parser, Reader {

    String getTag();

    Integer getNodeType();

    Process getProcess();

    GrammarExpl getGrammarExpl();

    List<ParserListener> getParserListeners();

    List<ProcessListener> getProcessListeners();
}
