package com.join.template.reader;

import com.join.template.configuration.Configuration;
import com.join.template.node.Element;
import com.join.template.parser.Parser;


public interface Reader {
    /**
     * 读取
     *
     * @param root   父节点
     * @param text   文本
     * @param parser 解析器
     * @return
     */
    int read(Element root, String text, Parser parser);

    /**
     * 行数
     *
     * @return
     */
    int getLineSize();

    /**
     * 获取配置
     *
     * @return
     */
    Configuration getConfiguration();


}
