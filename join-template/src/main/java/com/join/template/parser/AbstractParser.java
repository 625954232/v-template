package com.join.template.parser;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.constant.Constant;
import com.join.template.context.Content;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.listener.ParserListener;
import com.join.template.node.Element;
import com.join.template.node.Node;
import com.join.template.reader.Reader;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
public abstract class AbstractParser implements Parser {

    protected Content context;
    protected Template template;
    protected JoinFactory joinFactory;
    protected Configuration configuration;


    public AbstractParser(Template template) {
        this.template = template;
        this.context = template.getContent();
        this.joinFactory = template.getJoinFactory();
        this.configuration = joinFactory.getConfiguration();
    }


    @Override
    public Node parser(Element root, String text, Reader reader) {
        ExprConfig expressionConfig = joinFactory.hasExpression(text);
        Node element = new Node();
        //解析标签头和内容
        this.parserHead(expressionConfig, element, text);
        this.parserBody(expressionConfig, element, text);
        //解析循环和判断标签的内容
        if (Constant.EXPR_LIST == element.getNodeType()) {
            //读取子节点
            reader.read(element, element.getBody(), this);
        } else if (Constant.EXPR_IF == element.getNodeType()) {
            //如果是判断标签，就将当前节点变成判断总节点
            element.setNodeType(Constant.EXPR_IF_ROOT);
            Map<String, String> attributes = element.getAttributes();
            String body = element.getBody();
            String head = element.getHead();
            //拿取当前节点信息，就将其变成判断节点并添加到成判断总节点的子节点
            Node ifelement = new Node();
            ifelement.setParent(root);
            ifelement.setNodeType(Constant.EXPR_IF);
            ifelement.setHead(head);
            ifelement.setAttributes(attributes);
            element.addChilds(ifelement);
            element.getAttributes().clear();
            //读取子节点
            reader.read(element, body, this);
        }
        //解析监听
        Set<ParserListener> parserListeners = joinFactory.getParserListeners(element.getNodeType());
        if (parserListeners != null) {
            for (ParserListener parserListener : parserListeners) {
                parserListener.onParser(element, context);
            }
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
    abstract protected void parserBody(ExprConfig expressionConfig, Element element, String text);

    /**
     * 解析节点头
     *
     * @param expressionConfig
     * @param element
     * @param text
     * @return
     */
    abstract protected void parserHead(ExprConfig expressionConfig, Element element, String text);


}
