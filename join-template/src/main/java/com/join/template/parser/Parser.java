package com.join.template.parser;

import com.join.template.node.Element;
import com.join.template.reader.Reader;


public interface Parser {

    Element parser(Element root, String text, Reader reader);
}
