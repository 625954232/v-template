package com.join.template.node;

import java.util.List;
import java.util.Map;

public interface Element {
    Element setBody(String body);

    Element getParent();

    Element addAttributes(String name, String value);

    Element addAttributes(Map<String, String> attributes);

    Element addChilds(Element child);

    String getNodeType();

    String getHead();

    String getEnd();

    String getBody();

    String getAttribute(String name);

    List<Element> getChilds();


}
