package com.join.template.core.expression;


import com.join.template.core.explain.Explain;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.process.Process;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 表达式处理器
 * @date 2019/8/19 11:41
 */
public interface ExprHandleBuilder {

    ExprHandleBuilder tag(String tag);

    ExprHandleBuilder nodeType(Integer nodeType);

    ExprHandleBuilder process(Process process);

    ExprHandleBuilder explain(Explain explain);

    ExprHandleBuilder exprAttr(ExprAttr exprAttr);

    ExprHandleBuilder addParserListeners(ParserListener parserListener);

    ExprHandleBuilder addProcessListeners(ProcessListener parserListener);

    JoinFactory addIn();
}
