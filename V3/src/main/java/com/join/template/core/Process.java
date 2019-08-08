package com.join.template.core;

import com.join.template.core.context.Content;

import java.io.Writer;

public interface Process {

    void process(Element root, Content context, Writer writer);
}
