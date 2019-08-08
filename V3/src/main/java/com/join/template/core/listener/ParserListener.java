package com.join.template.core.listener;

import com.join.template.core.Element;

public interface ParserListener {
    /**
     * 解析监听
     *
     * @param element 节点
     */
    void onParser(Element element );
}
