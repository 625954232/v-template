package com.join.template.parser;

import com.join.template.node.Element;
import com.join.template.reader.Reader;


public interface Parser {
    /**
     * 解析
     *
     * @param root   父节点
     * @param text   文本
     * @param reader 读取器
     * @return
     */
    Element parser(Element root, String text, Reader reader);
}
