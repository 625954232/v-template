package com.join.template.listener;

import com.join.template.context.Content;
import com.join.template.node.Element;

public interface ParserListener {

    void onParser(Element element, Content context);
}
