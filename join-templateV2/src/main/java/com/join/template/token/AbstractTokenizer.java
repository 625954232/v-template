package com.join.template.token;

import com.join.template.configuration.Configuration;
import com.join.template.constant.Constant;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;


public abstract class AbstractTokenizer implements Tokenizer {
    protected Configuration configuration;
    protected Reader reader;

    protected Element root = null;
    protected Element current = null;
    protected Element parent = null;
    private int lineSize;
    private int length = 0;
    private int index = 0;
    private int start = 0;
    protected String text;


    public AbstractTokenizer(JoinFactory joinFactory) {
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
                this.index++;
                if (index < length)
                    if (text.charAt(index) == '\r' && text.charAt(index + 1) == '\n') {
                        lineSize++;
                    }
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
        }
        int index = this.text.indexOf(matchEndTag, this.index);
        String token = this.text.substring(this.index + matchBeginTag.length(), index);
        Element element = this.reader.reader(matchBeginTag, matchEndTag, token);
        this.arrange(element);
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
    public Element getRoot() {
        return root;
    }

    @Override
    public int getLineSize() {
        return lineSize;
    }
}
