package com.join.template.text.node;

import com.join.template.core.element.Element;
import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.element.ElementBuilder;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Node implements ElementBuilder {

    protected Template template;
    protected JoinFactory joinFactory;
    protected Configuration configuration;
    protected ExprHandle exprHandle;

    protected Integer nodeType;
    protected String original;
    protected Element parent;
    protected Boolean isEndElement = false;
    protected Map<String, String> attributes = new HashMap<>();
    protected List<Element> childs = new ArrayList<>();


    public Node(Template template) {
        this.template = template;
        this.joinFactory = template.getJoinFactory();
        this.configuration = template.getJoinFactory().getConfiguration();
    }

    @Override
    public ElementBuilder isEndElement(Boolean endElement) {
        this.isEndElement = endElement;
        return this;
    }

    @Override
    public ElementBuilder nodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public ElementBuilder original(String original) {
        this.original = original;
        onLoad(original);
        return this;
    }

    protected void onLoad(String original) {
        ExprAttr exprAttr = this.joinFactory.createExprAttr();
        this.attributes = exprAttr.findAttribute(original);
    }

    @Override
    public ElementBuilder parent(Element parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public ElementBuilder exprHandle(ExprHandle exprHandle) {
        this.exprHandle = exprHandle;
        return this;
    }

    @Override
    public ElementBuilder attribute(String name, String value) {
        this.attributes.put(name, value);
        return this;
    }

    @Override
    public ElementBuilder child(Element child) {
        this.childs.add(child);
        return this;
    }

    @Override
    public Element build(Template template) {
        return new Element() {
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
                return Node.this.template;
            }
        };
    }


}
