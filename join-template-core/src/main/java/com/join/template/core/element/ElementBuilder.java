package com.join.template.core.element;

import com.join.template.core.Element;
import com.join.template.core.expression.ExprHandle;

import java.util.Map;

/**
 * @author CAOYOU
 * @Title: ElementBuilder
 * @date 2019/9/910:56
 */
public interface ElementBuilder {
    /**
     * 设置节点类型
     *
     * @param nodeType
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:08
     */
    ElementBuilder nodeType(Integer nodeType);

    /**
     * 设置原文
     *
     * @param original
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    ElementBuilder original(String original);

    /**
     * 设置父节点
     *
     * @param parent
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    ElementBuilder parent(Element parent);

    /**
     * 新增属性
     *
     * @param name
     * @param value
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    ElementBuilder attribute(String name, String value);

    /**
     * 新增属性
     *
     * @param attributes
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    ElementBuilder attributes(Map<String, String> attributes);

    /**
     * 设置是不是结束节点
     *
     * @param endElement
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    ElementBuilder isEndElement(Boolean endElement);

    /**
     * 新增子节点
     *
     * @param child
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:15
     */
    ElementBuilder child(Element child);

    /**
     * 设置表达式处理器
     *
     * @param exprHandle
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:16
     */
    ElementBuilder exprHandle(ExprHandle exprHandle);

    Element build();
}
