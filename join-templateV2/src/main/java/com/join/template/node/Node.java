package com.join.template.node;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements Element {
    protected String nodeType;
    protected String original;
    protected Element parent;
    private Map<String, String> attributes = new HashMap<>();
    private List<Element> childs = new ArrayList<>();

    @Override
    public Element setNodeType(String nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public Element setOriginal(String original) {
        this.original = original;
        return this;
    }

    @Override
    public Element setParent(Element parent) {
        this.parent = parent;
        return this;
    }


    public Element setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
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

    @Override
    public String getOriginal() {
        return original;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public Element getParent() {
        return parent;
    }

    @Override
    public List<Element> getChilds() {
        return childs;
    }


}
