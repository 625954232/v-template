package com.join.template.parser;

import com.join.template.configuration.ExprConfig;
import com.join.template.node.Element;

public interface Parser {

    Element parser(String matchBeginTag, String matchEndTag, String text, ExprConfig exprConfig);
}
