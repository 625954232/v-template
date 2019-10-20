package com.join.template.core.grammar.handle;


import com.join.template.core.element.Element;
import com.join.template.core.example.Example;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.process.Process;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 表达式处理器
 * @date 2019/8/19 11:41
 */
public interface GrammarBuilder {

    GrammarBuilder tag(String tag);

    GrammarBuilder nodeType(Integer nodeType);

    GrammarBuilder process(Process process);

    GrammarBuilder explain(Example example);

    GrammarBuilder element(Class<? extends Element> elementClass);

    GrammarBuilder addParserListeners(ParserListener parserListener);

    GrammarBuilder addProcessListeners(ProcessListener parserListener);

    Grammar build();

    JoinFactoryBuilder addIn(JoinFactoryBuilder joinFactoryBuilder);
}
