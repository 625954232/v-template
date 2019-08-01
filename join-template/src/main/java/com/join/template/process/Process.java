package com.join.template.process;

import com.join.template.context.Context;
import com.join.template.node.Element;

import java.io.Writer;

public interface Process {

    void process(Element root, Context context, Writer writer);
}
