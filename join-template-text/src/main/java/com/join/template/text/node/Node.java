package com.join.template.text.node;

import com.join.template.core.Element;
import com.join.template.core.Template;
import com.join.template.core.expression.ExprHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements Element {

    protected Template template;
    protected Integer nodeType;
    protected String original;
    protected Element parent;
    private Boolean isEndElement = false;
    private Map<String, String> attributes = new HashMap<>();
    private List<Element> childs = new ArrayList<>();
    protected ExprHandle exprHandle;


    public Node(Template template, Integer nodeType, String original, Element parent) {
        this.template = template;
        this.nodeType = nodeType;
        this.original = original;
        this.parent = parent;
        this.isEndElement = isEndElement;
    }

    public Node(Template template) {
        this.template = template;
    }

    @Override
    public Element setEndElement(Boolean endElement) {
        this.isEndElement = endElement;
        return this;
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

    @Override
    public Element setExprHandle(ExprHandle exprHandle) {
        this.exprHandle = exprHandle;
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
    public String getAttribute(String name, String defaultValue) {
        return attributes.getOrDefault(name, defaultValue);
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
    public ExprHandle getExprHandle() {
        return exprHandle;
    }

    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public Boolean isEndElement() {
        return isEndElement;
    }

    @Override
    public Template getTemplate() {
        return template;
    }
}
