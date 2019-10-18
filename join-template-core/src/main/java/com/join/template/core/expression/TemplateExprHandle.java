package com.join.template.core.expression;

import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.element.ElementBuilder;
import com.join.template.core.explain.Explain;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.verify.TemplateException;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class TemplateExprHandle implements ExprHandleBuilder {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    //标记
    protected String tag;
    //标记
    protected Integer nodeType;
    //处理器
    protected Process process;
    //语法示例
    protected Explain explain;
    //表达式属性处理器
    protected ExprAttr exprAttr;
    //解析监听
    protected List<ParserListener> parserListeners;
    //处理器监听
    protected List<ProcessListener> processListeners;
    //语法示例
    protected Class<? extends ElementBuilder> elementClass;

    public TemplateExprHandle(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.parserListeners = new ArrayList<>();
        this.processListeners = new ArrayList<>();
    }

    @Override
    public ExprHandleBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public ExprHandleBuilder nodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public ExprHandleBuilder process(Process process) {
        process.setExprHandle(this.build());
        this.process = process;
        return this;
    }

    @Override
    public ExprHandleBuilder explain(Explain explain) {
        explain.setExprHandle(this.build());
        this.explain = explain;
        return this;
    }


    @Override
    public ExprHandleBuilder addParserListeners(ParserListener parserListener) {
        parserListener.setExprHandle(this.build());
        this.parserListeners.add(parserListener);
        return this;
    }

    @Override
    public ExprHandleBuilder addProcessListeners(ProcessListener parserListener) {
        parserListener.setExprHandle(this.build());
        this.processListeners.add(parserListener);
        return this;
    }


    @Override
    public JoinFactoryBuilder addIn(JoinFactoryBuilder joinFactoryBuilder) {
        return joinFactoryBuilder.addExprHandle(this.build());
    }

    @Override
    public ExprHandle build() {
        return new ExprHandle() {

            @Override
            public ElementBuilder getElementBuilder(Template template) {
                try {
                    Constructor<? extends ElementBuilder> constructor = elementClass.getConstructor(Template.class);
                    ElementBuilder builder = constructor.newInstance(template);
                    builder.nodeType(nodeType);
                    builder.exprHandle(this);
                    return builder;
                } catch (Exception e) {
                    throw new TemplateException("创建节点失败", e);
                }
            }

            @Override
            public String getTag() {
                return tag;
            }

            @Override
            public Integer getNodeType() {
                return nodeType;
            }

            @Override
            public Process getProcess() {
                return process;
            }

            @Override
            public Explain getExplain() {
                return explain;
            }

            @Override
            public List<ParserListener> getParserListeners() {
                return parserListeners;
            }

            @Override
            public List<ProcessListener> getProcessListeners() {
                return processListeners;
            }

            @Override
            public JoinFactory getJoinFactory() {
                return joinFactory;
            }
        };
    }

}
