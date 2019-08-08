package com.join.template.text.node;

import com.join.template.core.Element;
import com.join.template.core.configuration.ExprConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements Element {
    protected Integer nodeType;
    protected String original;
    protected Element parent;
    private Map<String, String> attributes = new HashMap<>();
    private List<Element> childs = new ArrayList<>();
    protected ExprConfig exprConfig;

    public Node() {
    }

    public Node(Integer nodeType, String original, Element parent) {
        this.nodeType = nodeType;
        this.original = original;
        this.parent = parent;
    }

    @Override
    public Element setNodeType(Integer nodeType) {
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

    public void setChilds(List<Element> childs) {
        this.childs = childs;
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
    public void setExprConfig(ExprConfig exprConfig) {
        this.exprConfig = exprConfig;
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
    public Integer getNodeType() {
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

    @Override
    public ExprConfig getExprConfig() {
        return exprConfig;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
