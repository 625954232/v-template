package com.join.template.token;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractTokenizer implements Tokenizer {
    protected Configuration configuration;
    protected Reader reader;

    protected Element root = null;
    protected Element current = null;
    protected Element parent = null;
    protected List<Element> elementss = null;
    private int lineSize;
    private int length = 0;
    private int index = 0;
    private int start = 0;
    protected String text;


    public AbstractTokenizer(JoinFactory joinFactory) {
        this.elementss = new ArrayList<>();
        this.configuration = joinFactory.getConfiguration();
        this.reader = joinFactory.getReader();
        this.root = new Node();
        this.root.setNodeType(Constant.EXPR_ROOT);
        this.current = this.root;
        this.parent = this.root;
    }

    @Override
    public void read(String text) {
        this.text = text;
        this.length = text.length();
        while (index < length) {
            if (text.startsWith(configuration.getVarTagStart(), index)) {
                this.match(configuration.getVarTagStart(), configuration.getVarTagEnd());
            } else if (text.startsWith(configuration.getExprLastBegin(), index)) {
                this.match(configuration.getExprLastBegin(), configuration.getExprEndSupport());
            } else if (text.startsWith(configuration.getExprFirstBegin(), index)) {
                this.match(configuration.getExprFirstBegin(), configuration.getExprEndSupport());
            } else {
                if (text.charAt(index) == '\n') {
                    lineSize++;
                }
                this.index++;
            }
        }
    }

    /**
     * 匹配标签
     *
     * @param matchBeginTag
     * @param matchEndTag
     */
    private void match(String matchBeginTag, String matchEndTag) {
        if (this.start < this.index) {
            String token = this.text.substring(this.start, this.index);
            Element element = new Node(Constant.EXPR_TEXT, token, this.parent);
            this.arrange(element);
            this.elementss.add(element);
        }
        int index = this.text.indexOf(matchEndTag, this.index);
        String token = this.text.substring(this.index + matchBeginTag.length(), index);
        Element element = this.reader.reader(matchBeginTag, matchEndTag, token);
        this.arrange(element);
        this.elementss.add(element);
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
}
