package com.join.template.text.word;

import com.join.template.core.Element;
import com.join.template.core.Reader;
import com.join.template.core.Template;
import com.join.template.core.Word;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.text.node.Node;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWord implements Word {
    protected Template template;
    protected Configuration configuration;
    protected Reader reader;
    protected List<Element> elementss = new ArrayList<>();
    protected Element root = null;
    protected Element current = null;
    protected Element parent = null;
    protected int lineSize;
    private int length = 0;
    private int index = 0;
    private int start = 0;
    protected String text;


    public AbstractWord(Template template, JoinFactory joinFactory) {
        this.configuration = joinFactory.getConfiguration();
        this.reader = joinFactory.getReader();
        this.root = new Node(template);
        this.root.setNodeType(Constant.EXPR_ROOT);
        this.current = this.root;
        this.parent = this.root;
        this.template = template;
    }


    @Override
    public void word(String text) {
        this.text = text;
        this.length = text.length();
        while (index < length) {
            if (text.startsWith(configuration.getVarTagStart(), index)) {
                this.match(configuration.getVarTagStart(), configuration.getVarTagEnd(), false);
            } else if (text.startsWith(configuration.getExprLastBegin(), index)) {
                this.match(configuration.getExprLastBegin(), configuration.getExprEndSupport(), true);
            } else if (text.startsWith(configuration.getExprFirstBegin(), index)) {
                this.match(configuration.getExprFirstBegin(), configuration.getExprEndSupport(), false);
            } else {
                if (text.charAt(index) == '\n') {
                    lineSize++;
                }
                this.index++;
            }
        }
        this.match(null, null, true);
    }

    /**
     * 匹配标签
     *
     * @param matchBeginTag
     * @param matchEndTag
     * @param isEndElement
     */
    private void match(String matchBeginTag, String matchEndTag, Boolean isEndElement) {
        if (this.start < this.index) {
            String token = this.text.substring(this.start, this.index);
            Element element = new Node(template, Constant.EXPR_TEXT, token, this.parent);
            element.setEndElement(isEndElement);
            this.arrange(element);
            elementss.add(element);
        }
        if (matchBeginTag == null || matchEndTag == null) {
            return;
        }
        int index = this.text.indexOf(matchEndTag, this.index);
        String token = this.text.substring(this.index + matchBeginTag.length(), index);
        Element element = this.reader.reader(template, matchBeginTag, matchEndTag, token, isEndElement);
        this.arrange(element);
        elementss.add(element);
        this.start = this.index = index + matchEndTag.length();
    }

    /**
     * 标签排列
     *
     * @param element
     */
    protected abstract void arrange(Element element);


    @Override
    public void reset() {
        this.lineSize = 0;
        this.index = 0;
        this.start = 0;
        this.current = this.root;
        this.parent = this.root;
    }

    @Override
    public Element getRootElement() {
        return root;
    }

    @Override
    public int getLineSize() {
        return lineSize;
    }

    @Override
    public List<Element> getAllElement() {
        return elementss;
    }

    @Override
    public Template getTemplate() {
        return template;
    }
}
