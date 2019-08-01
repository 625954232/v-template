package com.join.template.node;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Node implements Element {
    protected String nodeType;
    protected String head = "";
    protected String body = "";
    protected String end = "";
    protected Element parent;
    private Map<String, String> attributes = new HashMap<>();
    private List<Element> childs = new ArrayList<>();

    public Node setNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    public Node setHead(String beginOriginal) {
        this.head = beginOriginal;
        return this;
    }

    public Node setEnd(String endOriginal) {
        this.end = endOriginal;
        return this;
    }

    public Node setParent(Element parent) {
        this.parent = parent;
        return this;
    }

    public Node setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public Element addAttributes(String name, String value) {
        this.attributes.put(name, value);
        return this;
    }

    @Override
    public Element addAttributes(Map<String, String> attributes) {
        this.attributes.putAll(attributes);
        return this;
    }


    @Override
    public Element addChilds(Element child) {
        this.childs.add(child);
        return this;
    }

    @Override
    public String getAttribute(String name) {
        return attributes.get(name);
    }


}
