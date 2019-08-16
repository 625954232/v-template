package com.join.template.core;

public interface Parser {

    Element parser(String matchBeginTag, String matchEndTag, String text);
}
