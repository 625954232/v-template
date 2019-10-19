package com.join.template.core.expression;

import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.element.AbstractElement;
import com.join.template.core.element.Element;
import com.join.template.core.explain.Explain;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.verify.TemplateException;

import java.util.ArrayList;
import java.util.List;

public class TemplateExprHandleBuilder implements ExprHandleBuilder {

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
    //解析监听
    protected List<ParserListener> parserListeners;
    //处理器监听
    protected List<ProcessListener> processListeners;
    //语法示例
    protected Class<? extends Element> elementClass;

    public TemplateExprHandleBuilder(JoinFactory joinFactory) {
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
    public ExprHandleBuilder elementClass(Class<? extends Element> elementClass) {
        this.elementClass = (Class<? extends AbstractElement>) elementClass;
        return this;
    }

    @Override
    public JoinFactoryBuilder addIn(JoinFactoryBuilder joinFactoryBuilder) {
        return joinFactoryBuilder.addExprHandle(this.build());
    }


    public void read(Element element) {
        element.setNodeType(this.nodeType);
    }

    @Override
    public ExprHandle build() {
        return new TemplateExprHandle(this);
    }

    public class TemplateExprHandle implements ExprHandle {

        private final TemplateExprHandleBuilder base;

        public TemplateExprHandle(TemplateExprHandleBuilder templateExprHandle) {
            this.base = templateExprHandle;
        }

        @Override
        public Element createElement(Template template) {
            try {
                Element element = base.elementClass.newInstance();
                element.setTemplate(template);
                base.read(element);
                return element;
            } catch (Exception e) {
                System.out.println(TemplateExprHandleBuilder.this.elementClass);
                e.printStackTrace();
                throw new TemplateException("创建节点失败", e);
            }
        }

        @Override
        public String getTag() {
            return base.tag;
        }

        @Override
        public Integer getNodeType() {
            return base.nodeType;
        }

        @Override
        public Process getProcess() {
            return base.process;
        }

        @Override
        public Explain getExplain() {
            return base.explain;
        }

        @Override
        public List<ParserListener> getParserListeners() {
            return base.parserListeners;
        }

        @Override
        public List<ProcessListener> getProcessListeners() {
            return base.processListeners;
        }

        @Override
        public JoinFactory getJoinFactory() {
            return base.joinFactory;
        }
    }


    ;

}
