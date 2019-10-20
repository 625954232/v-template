package com.join.template.core.grammar.generate;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.example.Example;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GenerateListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractGenerate<T extends GrammarInfo> implements GrammarGenerate<T> {

    protected JoinFactory joinFactory;

    protected Configuration configuration;

    protected GenerateListener generateListener;

    protected GenerateConfig generateConfig;

    protected Class<T> grammarInfoClass = null;

    protected List<T> grammarInfos = null;

    protected TemplateType templateType = TemplateType.Text;


    public AbstractGenerate() {
        this.grammarInfos = new ArrayList<>();
    }

    @Override
    public GrammarGenerate setJoinFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        return this;
    }

    @Override
    public AbstractGenerate setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
        return this;
    }

    @Override
    public AbstractGenerate setGrammarInfo(Class<? extends GrammarInfo> grammarInfo) {
        this.grammarInfoClass = (Class<T>) grammarInfo;
        return this;
    }

    @Override
    public AbstractGenerate setGenerateListener(GenerateListener generateListener) {
        this.generateListener = generateListener;
        return this;
    }

    @Override
    public AbstractGenerate setGenerateConfig(GenerateConfig generateConfig) {
        this.generateConfig = generateConfig;
        return this;
    }

    @Override
    public Map<Integer, String> generateGrammarExplain() {
        Map<Integer, String> grammars = new HashMap();
        Map<Object, Grammar> exprHandles = joinFactory.getExprHandles();
        for (Grammar expressionHandle : exprHandles.values()) {
            Example grammarExpl = expressionHandle.getExample();
            if (grammarExpl != null) {
                grammars.put(expressionHandle.getNodeType(), grammarExpl.getExample());
            }
        }
        return grammars;
    }

    @Override
    public List<T> getGrammarInfos() {
        return grammarInfos;
    }
}
