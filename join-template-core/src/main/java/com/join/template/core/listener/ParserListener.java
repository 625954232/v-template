package com.join.template.core.listener;

import com.join.template.core.element.Element;
import com.join.template.core.grammar.handle.Grammar;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 解析监听器
 * @date 2019/8/19 11:58
 */
public interface ParserListener {
    /**
     * 设置表达式处理器
     *
     * @param grammar
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 14:07
     */
    void setGrammar(Grammar grammar);

    /**
     * 解析监听
     *
     * @param element 节点
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:51
     */
    void onParser(Element element);


}
