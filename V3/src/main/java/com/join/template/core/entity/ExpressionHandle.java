package com.join.template.core.entity;


import com.join.template.core.Element;
import com.join.template.core.GrammarExpl;
import com.join.template.core.Parser;
import com.join.template.core.Process;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.util.Utils;
import com.join.template.text.node.Node;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
public class ExpressionHandle implements Parser {
    /**
     * 标记
     */
    private String tag;
    /**
     * 标记
     */
    private Integer nodeType;
    /**
     * 解析器
     */
    private Parser parser;
    /**
     * 处理器
     */
    private Process process;

    /**
     * 语法示例
     */
    private GrammarExpl grammarExpl;

    /**
     * 解析监听
     */
    private List<ParserListener> parserListeners = new ArrayList<>();

    /**
     * 处理器监听
     */
    private List<ProcessListener> processListeners = new ArrayList<>();

    public ExpressionHandle(String tag, Integer nodeType, Parser parser, Process process, GrammarExpl grammarExpl) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.parser = parser;
        this.process = process;
        this.grammarExpl = grammarExpl;
    }

    public ExpressionHandle(String tag, Integer nodeType, Process process, GrammarExpl grammarExpl) {
        this.tag = tag;
        this.nodeType = nodeType;
        this.parser = this;
        this.process = process;
        this.grammarExpl = grammarExpl;
    }

    @Override
    public Element parser(String matchBeginTag, String matchEndTag, String text) {
        Configuration configuration = TemplateUtil.getConfiguration();
        Map<String, String> attr = Utils.findAttr(text);

        Node node = new Node();
        node.setExprConfig(this);
        if (matchBeginTag != null && matchEndTag != null) {
            if (matchBeginTag.startsWith(configuration.getVarTagStart()) &&
                    matchEndTag.startsWith(configuration.getVarTagEnd())) {
                node.setNodeType(Constant.EXPR_VAR);
                node.addAttributes(configuration.getAttVar(), text);
            } else {
                node.setNodeType(this.nodeType);
            }
            node.setOriginal(matchBeginTag + text + matchEndTag);
        } else {
            node.setNodeType(this.nodeType);
            node.setOriginal(text);
        }
        node.addAttributes(attr);
        if (grammarExpl != null)
            grammarExpl.verifyGrammarAttr(node.getOriginal(), node.getAttributes());

        for (ParserListener parserListener : parserListeners) {
            parserListener.onParser(node);
        }
        return node;
    }
}
