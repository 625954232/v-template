package com.join.template.core;

import com.join.template.core.entity.ExprConfig;

public interface Parser {

    Element parser(String matchBeginTag, String matchEndTag, String text, ExprConfig exprConfig);
}
