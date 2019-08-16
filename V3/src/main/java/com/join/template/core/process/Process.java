package com.join.template.core.process;

import com.join.template.core.Element;
import com.join.template.core.context.Content;

import java.io.Writer;

public interface Process {
    /**
     * 处理
     *
     * @param root
     * @param context
     * @param writer
     */
    void process(Element root, Content context, Writer writer);
}
