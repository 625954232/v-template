package com.join.template.parser;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExpressionConfig;
import com.join.template.constant.Constant;
import com.join.template.context.Context;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;
import lombok.Getter;

@Getter
public abstract class AbstractParser implements Parser {

    protected Context context;
    protected Template template;
    protected JoinFactory joinFactory;
    protected Configuration configuration;


    public AbstractParser(Template template) {
        this.template = template;
        this.context = template.getContext();
        this.joinFactory = template.getJoinFactory();
        this.configuration = joinFactory.getConfiguration();
    }


    @Override
    public Node parser(Element root, String text, Reader reader) {
        ExpressionConfig expressionConfig = joinFactory.hasExpression(text);
        Node element = new Node();
        //解析标签头和内容
        this.parserHead(expressionConfig, element, text);
        this.parserBody(expressionConfig, element, text);
        //解析循环和判断标签的内容
        if (Constant.EXPRESSION_LIST == element.getNodeType() || Constant.EXPRESSION_IF == element.getNodeType()) {
            reader.read(element, element.getBody(), this);
        }
        //解析监听
        ParserListener parserListeners = joinFactory.getParserListeners(element.getNodeType());
        if (parserListeners != null) {
            parserListeners.onParser(element, context);
        }
        element.setParent(root);
        root.addChilds(element);
        return element;
    }


    /**
     * 解析节点内容
     *
     * @param expressionConfig
     * @param element
     * @param text
     * @return
     */
    abstract protected void parserBody(ExpressionConfig expressionConfig, Element element, String text);

    /**
     * 解析节点头
     *
     * @param expressionConfig
     * @param element
     * @param text
     * @return
     */
    abstract protected void parserHead(ExpressionConfig expressionConfig, Element element, String text);


}
