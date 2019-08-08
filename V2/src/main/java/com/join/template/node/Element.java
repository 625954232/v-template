package com.join.template.node;

import com.join.template.configuration.ExprConfig;

import java.util.List;
import java.util.Map;

public interface Element {
    /**
     * 设置节点类型
     *
     * @param nodeType
     * @return
     */
    Element setNodeType(Integer nodeType);

    /**
     * 设置原文
     *
     * @param original
     * @return
     */
    Element setOriginal(String original);

    /**
     * 设置父节点
     *
     * @param parent
     * @return
     */
    Element setParent(Element parent);

    /**
     * 新增属性
     *
     * @param name
     * @param value
     * @return
     */
    Element addAttributes(String name, String value);

    /**
     * 新增属性
     *
     * @param attributes
     * @return
     */
    Element addAttributes(Map<String, String> attributes);

    /**
     * 新增子节点
     *
     * @param child
     * @return
     */
    Element addChilds(Element child);

    /**
     * 获取节点类型
     *
     * @return
     */
    Integer getNodeType();

    /**
     * 获取原文
     *
     * @return
     */
    String getOriginal();

    void setExprConfig(ExprConfig exprConfig);

    /**
     * 获取节点属性
     *
     * @param name
     * @return
     */
    String getAttribute(String name);

    /**
     * 获取父节点
     *
     * @return
     */
    Element getParent();

    /**
     * 获取子节点
     *
     * @return
     */
    List<Element> getChilds();

    ExprConfig getExprConfig();
}