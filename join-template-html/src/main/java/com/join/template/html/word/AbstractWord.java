package com.join.template.html.word;

import com.join.template.core.element.Element;
import com.join.template.core.Parser;
import com.join.template.core.Template;
import com.join.template.core.Word;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractWord implements Word {
    private final JoinFactory joinFactory;
    protected final Template template;
    protected final Configuration configuration;

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
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();

        ExprHandle exprHandle = joinFactory.getExprHandle(Constant.EXPR_ROOT);
        Element element = exprHandle.createElement(template);
        this.root = element;
        this.current = element;
        this.parent = element;

    }


    @Override
    public void word(String text) {
        this.root.read(text, false);
        this.text = text;
        this.length = text.length();
        while (index < length) {
            while (index < length) {
                if (text.startsWith(configuration.getVarTagStart(), index)) {
                    this.matchHtml();
                    this.matchVar(configuration.getVarTagStart(), configuration.getVarTagEnd());
                } else if (text.startsWith(configuration.getExprLastBegin(), index)) {
                    this.matchHtml();
                    this.matchGrammar(configuration.getExprLastBegin(), configuration.getExprEndSupport(), true);
                } else if (text.startsWith(configuration.getExprFirstBegin(), index)) {
                    this.matchHtml();
                    this.matchGrammar(configuration.getExprFirstBegin(), configuration.getExprEndSupport(), false);
                } else {
                    if (text.charAt(index) == '\n') {
                        lineSize++;
                    }
                    this.index++;
                }
            }
        }
        this.matchHtml();
    }

    protected void matchHtml() {
        if (this.start < this.index) {
            Parser parser = joinFactory.createParser(template);
            parser.setContent(this.text.substring(this.start, this.index));
            Element element = parser.parser();
            this.arrange(element);
            elementss.add(element);
        }
    }

    protected void matchGrammar(String exprLastBegin, String exprEndSupport, boolean endElement) {
        int index = this.text.indexOf(exprEndSupport, this.index);
        String content = this.text.substring(this.index + exprLastBegin.length(), index);

        Parser parser = joinFactory.createParser(template);
        parser.setMatchBeginTag(exprLastBegin);
        parser.setMatchEndTag(exprEndSupport);
        parser.setGrammar(true);
        parser.setEndElement(endElement);
        parser.setContent(content);
        Element element = parser.parser();

        this.arrange(element);
        elementss.add(element);
        this.start = this.index = index + exprEndSupport.length();
    }


    protected void matchVar(String varTagStart, String varTagEnd) {
        int index = this.text.indexOf(varTagEnd, this.index);
        String content = this.text.substring(this.index + varTagStart.length(), index);

        Parser parser = joinFactory.createParser(template);
        parser.setMatchBeginTag(varTagStart);
        parser.setMatchEndTag(varTagEnd);
        parser.setVarExpr(true);
        parser.setContent(content);
        Element element = parser.parser();

        this.arrange(element);
        elementss.add(element);
        this.start = this.index = index + varTagEnd.length();
    }


    /**
     * 标签排列
     *
     * @param elementBuilder
     */
    protected abstract void arrange(Element elementBuilder);


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
