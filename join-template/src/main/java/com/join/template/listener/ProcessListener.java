package com.join.template.listener;

import com.join.template.context.Context;
import com.join.template.node.Element;

import java.io.Writer;

public interface ProcessListener {

    void onProcess(Element element, Context context, Writer writer);
}
