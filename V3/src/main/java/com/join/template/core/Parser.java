package com.join.template.core;

public interface Parser {

    /**
     * j解析
     *
     * @param matchBeginTag
     * @param matchEndTag
     * @param text
     * @return
     */
    Element parser(String matchBeginTag, String matchEndTag, String text);
}
