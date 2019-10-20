package com.join.template.core.process;

import com.join.template.core.element.Element;
import com.join.template.core.context.Content;
import com.join.template.core.grammar.handle.Grammar;

import java.io.Writer;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 处理器
 * @date 2019/8/19 12:18
 */
public interface Process<T extends Element> {


    void setGrammar(Grammar grammar);

    /**
     * 处理监听
     *
     * @param element 节点
     * @param context 参数
     * @param writer  写入
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void process(T element, Content context, Writer writer);

}
