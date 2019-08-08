package com.join.template.listener;

import com.join.template.context.Content;
import com.join.template.node.Element;

import java.io.Writer;

public interface ProcessListener {
    /**
     * 处理监听
     *
     * @param element 节点
     * @param context 参数
     * @param writer  写入
     */
    void onProcess(Element element, Content context, Writer writer);
}
