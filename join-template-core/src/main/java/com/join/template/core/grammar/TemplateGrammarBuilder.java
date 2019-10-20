package com.join.template.core.grammar;

import com.join.template.core.Template;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.element.Element;
import com.join.template.core.example.Example;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.grammar.handle.GrammarBuilder;
import com.join.template.core.process.Process;
import com.join.template.core.listener.ParserListener;
import com.join.template.core.listener.ProcessListener;
import com.join.template.core.util.TemplateException;

import java.util.ArrayList;
import java.util.List;

public class TemplateGrammarBuilder implements GrammarBuilder {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    //标记
    protected String tag;
    //标记
    protected Integer nodeType;
    //处理器
    protected Process process;
    //语法示例
    protected Example explain;
    //解析监听
    protected List<ParserListener> parserListeners;
    //处理器监听
    protected List<ProcessListener> processListeners;
    //语法示例
    protected Class<? extends Element> elementClass;

    public TemplateGrammarBuilder(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.parserListeners = new ArrayList<>();
        this.processListeners = new ArrayList<>();
    }

    @Override
    public GrammarBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public GrammarBuilder nodeType(Integer nodeType) {
        this.nodeType = nodeType;
        return this;
    }

    @Override
    public GrammarBuilder process(Process process) {
        process.setGrammar(grammar);
        this.process = process;
        return this;
    }

    @Override
    public GrammarBuilder explain(Example explain) {
        explain.setGrammar(grammar);
        this.explain = explain;
        return this;
    }


    @Override
    public GrammarBuilder addParserListeners(ParserListener parserListener) {
        parserListener.setGrammar(grammar);
        this.parserListeners.add(parserListener);
        return this;
    }

    @Override
    public GrammarBuilder addProcessListeners(ProcessListener parserListener) {
        parserListener.setExprHandle(grammar);
        this.processListeners.add(parserListener);
        return this;
    }

    @Override
    public GrammarBuilder element(Class<? extends Element> elementClass) {
        this.elementClass = elementClass;
        return this;
    }

    @Override
    public JoinFactoryBuilder addIn(JoinFactoryBuilder joinFactoryBuilder) {
        return joinFactoryBuilder.addExprHandle(grammar);
    }


    public void read(Element element) {
        element.setNodeType(this.nodeType);
    }

    @Override
    public Grammar build() {
        return grammar;
    }

    public Grammar grammar = new Grammar() {


        @Override
        public Element createElement(Template template) {
            try {
                Element element = TemplateGrammarBuilder.this.elementClass.newInstance();
                element.setGrammar(this);
                TemplateGrammarBuilder.this.read(element);
                return element;
            } catch (Exception e) {
                System.out.println(TemplateGrammarBuilder.this.elementClass);
                e.printStackTrace();
                throw new TemplateException("创建节点失败", e);
            }
        }

        @Override
        public String getTag() {
            return TemplateGrammarBuilder.this.tag;
        }

        @Override
        public Integer getNodeType() {
            return TemplateGrammarBuilder.this.nodeType;
        }

        @Override
        public Process getProcess() {
            return TemplateGrammarBuilder.this.process;
        }

        @Override
        public Example getExample() {
            return TemplateGrammarBuilder.this.explain;
        }

        @Override
        public List<ParserListener> getParserListeners() {
            return TemplateGrammarBuilder.this.parserListeners;
        }

        @Override
        public List<ProcessListener> getProcessListeners() {
            return TemplateGrammarBuilder.this.processListeners;
        }

        @Override
        public JoinFactory getJoinFactory() {
            return TemplateGrammarBuilder.this.joinFactory;
        }
    };

}
