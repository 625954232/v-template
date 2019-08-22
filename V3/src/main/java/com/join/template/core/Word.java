package com.join.template.core;

import java.util.List;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 分词器
 * @date 2019/8/19 12:20
 */
public interface Word {


    /**
     * 读取模版
     *
     * @param text 模版内容
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:20
     */
    void word(String text);


    /**
     * 重置
     *
     * @param
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:21
     */
    void reset();


    /**
     * 获取最顶层节点
     *
     * @param
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:21
     */
    Element getRootElement();

    /**
     * 获取全部节点
     *
     * @param
     * @return java.util.List<com.join.template.core.Element>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:21
     */
    List<Element> getAllElement();


    /**
     * 获取行数
     *
     * @param
     * @return int
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:21
     */
    int getLineSize();

    Template getTemplate();
}
