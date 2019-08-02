package com.join.template.node;

import java.util.List;
import java.util.Map;

public interface Element {
    /**
     * 设置内容
     *
     * @param body
     * @return
     */
    Element setBody(String body);

    /**
     * 获取父节点
     *
     * @return
     */
    Element getParent();

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
    String getNodeType();

    /**
     * 获取节点头
     *
     * @return
     */
    String getHead();

    /**
     * 获取节点结尾
     *
     * @return
     */
    String getEnd();

    /**
     * 获取节点内容
     *
     * @return
     */
    String getBody();

    /**
     * 获取节点属性
     *
     * @param name
     * @return
     */
    String getAttribute(String name);

    /**
     * 获取子节点
     *
     * @return
     */
    List<Element> getChilds();


}
