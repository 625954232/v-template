package com.join.template.listener;

import com.join.template.context.Content;
import com.join.template.node.Element;

public interface ParserListener {
    /**
     * 解析监听
     *
     * @param element 节点
     */
    void onParser(Element element );
}
