package com.join.template.core.element;

import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractElement implements Element {

    protected Configuration configuration;
    protected Template template;
    protected Integer nodeType;
    protected String original;
    protected Element parent;
    protected Boolean isEndElement;
    protected Map<String, String> attributes;
    protected List<Element> childs;

    public AbstractElement() {
        this.childs = new ArrayList<>();
        this.attributes = new HashMap<>();

    }

    @Override
    public Element setTemplate(Template template) {
        JoinFactory joinFactory = template.getJoinFactory();
        this.template = template;
        this.configuration = joinFactory.getConfiguration();
        return this;
    }

    @Override
    public Element setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
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
    public Map<String, String> getAttributes() {
        return attributes;
    }

    @Override
    public Boolean isEndElement() {
        return isEndElement;
    }


    @Override
    public Element read(String original, Boolean isEndElement) {
        this.original = original;
        this.parent = parent;
        this.isEndElement = isEndElement;
        ExprAttr exprAttr = template.getExprAttr();
        this.attributes = exprAttr.findAttribute(original);
        this.readAttributes();
        return this;
    }

    @Override
    public Element setParent(Element parent) {
        this.parent = parent;
        return this;
    }

    public abstract void readAttributes();
}
