package com.join.template.core;

import com.join.template.core.expression.ExprHandle;
import com.join.template.text.expression.DefaultExpressionHandle;
import com.join.template.text.node.Node;

import java.util.List;
import java.util.Map;

public interface Element {
    /**
     * 设置节点类型
     *
     * @param nodeType
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:08
     */
    Element setNodeType(Integer nodeType);

    /**
     * 设置原文
     *
     * @param original
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    Element setOriginal(String original);

    /**
     * 设置父节点
     *
     * @param parent
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    Element setParent(Element parent);

    /**
     * 新增属性
     *
     * @param name
     * @param value
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    Element addAttributes(String name, String value);

    /**
     * 新增属性
     *
     * @param attributes
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    Element addAttributes(Map<String, String> attributes);

    /**
     * 设置是不是结束节点
     *
     * @param endElement
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:09
     */
    Element setEndElement(Boolean endElement);

    /**
     * 新增子节点
     *
     * @param child
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:15
     */
    Element addChilds(Element child);

    /**
     * 设置表达式处理器
     *
     * @param exprHandle
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:16
     */
    Element setExprHandle(ExprHandle exprHandle);

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
     * @return java.util.Map<java.lang.String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               java.lang.String>
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
