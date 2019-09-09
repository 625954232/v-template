package com.join.template.core;

import com.join.template.core.expression.ExprHandle;

import java.util.List;
import java.util.Map;

public interface Element {

    /**
     * 获取节点类型
     *
     * @param
     * @return java.lang.Integer
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:15
     */
    Integer getNodeType();

    /**
     * 获取原文
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:14
     */
    String getOriginal();

    /**
     * 获取属性
     *
     * @param name
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:07
     */
    String getAttribute(String name);

    /**
     * 获取节点属性
     *
     * @param name
     * @param defaultValue
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:08
     */
    String getAttribute(String name, String defaultValue);

    /**
     * 获取父节点
     *
     * @param
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:14
     */
    Element getParent();

    /**
     * 获取全部子节点
     *
     * @param
     * @return java.util.List<com.join.template.core.Element>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:14
     */
    List<Element> getChilds();

    /**
     * 获取表达式处理器
     *
     * @param
     * @return com.join.template.core.expression.ExprHandle
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:14
     */
    ExprHandle getExprHandle();

    /**
     * 获取全部属性
     *
     * @param
     * @return java.util.Map<java.lang.String       ,       java.lang.String>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:14
     */
    Map<String, String> getAttributes();

    /**
     * 是不是结束节点
     *
     * @param
     * @return java.lang.Boolean
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:13
     */
    Boolean isEndElement();

    /**
     * 获取模版
     *
     * @param
     * @return com.join.template.core.Template
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:13
     */
    Template getTemplate();
}
