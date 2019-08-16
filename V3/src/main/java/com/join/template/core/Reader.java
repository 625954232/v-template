package com.join.template.core;

public interface Reader {

    Element reader(String matchBeginTag, String matchEndTag, String text);
}
