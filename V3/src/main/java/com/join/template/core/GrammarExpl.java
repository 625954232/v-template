package com.join.template.core;


import java.util.List;
import java.util.Map;

public interface GrammarExpl {
    /**
     * 效验节点属性
     *
     * @param element
     */
    default void verifyElement(Element element) {
        verifyElement(element.getOriginal(), element.isEndElement(), element.getAttributes());
    }

    /**
     * 效验节点属性
     *
     * @param original
     * @param endElement
     * @param attr
     */
    void verifyElement(String original, Boolean endElement, Map<String, String> attr);

    /**
     * 获取语法表达式属性解释
     *
     * @return
     */
    Map<String, String> getElementAttrExpl();


}
