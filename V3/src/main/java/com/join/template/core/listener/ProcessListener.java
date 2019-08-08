package com.join.template.core.listener;

import com.join.template.core.Element;
import com.join.template.core.context.Content;

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
