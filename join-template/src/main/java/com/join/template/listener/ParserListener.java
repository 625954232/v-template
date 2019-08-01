package com.join.template.listener;

import com.join.template.context.Context;
import com.join.template.node.Element;

import java.util.EventListener;

public interface ParserListener {

    void onParser(Element element, Context context);
}
