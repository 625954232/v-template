package com.join.template.core;

public interface Reader {

    /**
     * 读取
     *
     * @param matchBeginTag
     * @param matchEndTag
     * @param text
     * @return
     */
    Element reader(String matchBeginTag, String matchEndTag, String text);
}
