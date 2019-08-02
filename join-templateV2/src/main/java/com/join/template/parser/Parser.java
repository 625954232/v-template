package com.join.template.parser;

import com.join.template.configuration.ExprConfig;
import com.join.template.node.Element;
import com.join.template.token.TokenMatcher;

public interface Parser {

    Element parser(String text, TokenMatcher tokenMatcher, ExprConfig exprConfig);
}
