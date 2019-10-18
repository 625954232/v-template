package com.join.template.text.word;

import com.join.template.core.element.Element;
import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.Word;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.element.ElementBuilder;
import com.join.template.text.node.Node;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWord implements Word {
    protected Template template;
    protected Configuration configuration;
    protected Parser parser;
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
        this.template = template;
        this.configuration = joinFactory.getConfiguration();
        this.parser = joinFactory.createParser();

        ElementBuilder elementBuilder = new Node(template);
        elementBuilder.nodeType(Constant.EXPR_ROOT);
        Element element = elementBuilder.build(template);
        this.root = element;
        this.current = element;
        this.parent = element;

    }


    @Override
    public void word(String text) {
        this.text = text;
        this.length = text.length();
        while (index < length) {
            if (text.startsWith(configuration.getVarTagStart(), index)) {
                this.match(configuration.getVarTagStart(), configuration.getVarTagEnd(), true, false);
            } else if (text.startsWith(configuration.getExprLastBegin(), index)) {
                this.match(configuration.getExprLastBegin(), configuration.getExprEndSupport(), false, true);
            } else if (text.startsWith(configuration.getExprFirstBegin(), index)) {
                this.match(configuration.getExprFirstBegin(), configuration.getExprEndSupport(), false, false);
            } else {
                if (text.charAt(index) == '\n') {
                    lineSize++;
                }
                this.index++;
            }
        }
        this.match(null, null, false, true);
    }

    /**
     * 匹配标签
     *
     * @param matchBeginTag
     * @param matchEndTag
     * @param isVarTag
     * @param isEndElement
     */
    private void match(String matchBeginTag, String matchEndTag, Boolean isVarTag, Boolean isEndElement) {
        ElementBuilder elementBuilder = null;
        if (this.start < this.index) {
            String token = this.text.substring(this.start, this.index);
            elementBuilder = this.parser.parser(template, Constant.EXPR_TEXT, matchBeginTag, matchEndTag, token, false);
            this.arrange(elementBuilder);
            elementss.add(elementBuilder.build(template));
        }
        if (matchBeginTag == null || matchEndTag == null) {
            return;
        }
        int index = this.text.indexOf(matchEndTag, this.index);
        String token = this.text.substring(this.index + matchBeginTag.length(), index);
        if (isVarTag) {
            elementBuilder = this.parser.parser(template, Constant.EXPR_VAR, matchBeginTag, matchEndTag, token, isEndElement);
        } else {
            elementBuilder = this.parser.parser(template, null, matchBeginTag, matchEndTag, token, isEndElement);
        }
        this.arrange(elementBuilder);
        elementss.add(elementBuilder.build(template));
        this.start = this.index = index + matchEndTag.length();


    }

    /**
     * 标签排列
     *
     * @param elementBuilder
     */
    protected abstract void arrange(ElementBuilder elementBuilder);


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
