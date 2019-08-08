package com.join.template.core;

import com.join.template.core.Element;


public interface Reader {

    Element reader(String matchBeginTag, String matchEndTag, String token);
}
