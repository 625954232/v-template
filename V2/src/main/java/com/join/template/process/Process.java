package com.join.template.process;

import com.join.template.context.Content;
import com.join.template.node.Element;

import java.io.Writer;

public interface Process {

    void process(Element root, Content context, Writer writer);
}
