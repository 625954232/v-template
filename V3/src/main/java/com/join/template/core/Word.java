package com.join.template.core;

import java.util.List;

public interface Word {


    /**
     * 读取模版
     *
     * @param text
     */
    void word(String text);


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