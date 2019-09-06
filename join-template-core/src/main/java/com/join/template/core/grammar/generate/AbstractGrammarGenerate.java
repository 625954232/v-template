package com.join.template.core.grammar.generate;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.explain.Explain;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GrammarGenListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractGrammarGenerate<T extends GrammarInfo> implements GrammarGenerate<T> {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    protected GrammarGenListener grammarGenListener;

    protected GrammarField grammarField = new GrammarField();

    protected Class<T> grammarInfoClass = null;

    protected List<T> grammarInfos = null;

    protected TemplateType templateType = TemplateType.Text;


    public AbstractGrammarGenerate(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
        this.configuration = joinFactory.getConfiguration();
        this.grammarInfos = new ArrayList<>();
    }

    @Override
    public AbstractGrammarGenerate setTemplateType(TemplateType templateType) {
        this.templateType = templateType;
        return this;
    }

    @Override
    public AbstractGrammarGenerate setGrammarInfo(Class<? extends GrammarInfo> grammarInfo) {
        this.grammarInfoClass = (Class<T>) grammarInfo;
        return this;
    }

    @Override
    public AbstractGrammarGenerate setGrammarGenListener(GrammarGenListener grammarGenListener) {
        this.grammarGenListener = grammarGenListener;
        return this;
    }

    @Override
    public AbstractGrammarGenerate setGrammarField(GrammarField grammarField) {
        this.grammarField = grammarField;
        return this;
    }

    @Override
    public Map<Integer, String> generateGrammarExplain() {
        Map<Integer, String> grammars = new HashMap();
        Map<Object, ExprHandle> exprHandles = joinFactory.getExprHandles();
        for (ExprHandle expressionHandle : exprHandles.values()) {
            Explain grammarExpl = expressionHandle.getExplain();
            if (grammarExpl != null) {
                grammars.put(expressionHandle.getNodeType(), grammarExpl.getGrammarExplain());
            }
        }
        return grammars;
    }

    @Override
    public List<T> getGrammarInfos() {
        return grammarInfos;
    }
}
