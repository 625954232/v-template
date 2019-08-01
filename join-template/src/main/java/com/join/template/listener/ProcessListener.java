package com.join.template.listener;

import com.join.template.context.Content;
import com.join.template.node.Element;

import java.io.Writer;

public interface ProcessListener {

    void onProcess(Element element, Content context, Writer writer);
}
