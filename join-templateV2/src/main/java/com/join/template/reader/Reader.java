package com.join.template.reader;

import com.join.template.node.Element;

public interface Reader {

    Element reader(String matchBeginTag, String matchEndTag, String token);
}
