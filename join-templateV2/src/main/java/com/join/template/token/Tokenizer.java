package com.join.template.token;

import com.join.template.node.Element;

import java.util.List;

public interface Tokenizer {


    /**
     * 读取模版
     *
     * @param text
     */
    void read(String text);

    /**
     * 获取最顶层节点
     *
     * @return
     */
    Element getRoot();

    /**
     * 获取行数
     *
     * @return
     */
    int getLineSize();

    /**
     * 重置
     */
    void reset();

    List<Element> getAllElements();
}
