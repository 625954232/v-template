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
     * 重置
     */
    void reset();


    /**
     * 获取最顶层节点
     *
     * @return
     */
    Element getRootElement();

    /**
     * 获取全部节点
     *
     * @return
     */
    List<Element> getAllElement();


    /**
     * 获取行数
     *
     * @return
     */
    int getLineSize();
}
