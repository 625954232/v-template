package com.join.template.core.grammar.generate;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.TemplateUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractGrammarGenerate<T extends GrammarInfo> implements GrammarGenerate<T> {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    protected GrammarGenListener grammarGenListener;

    protected GrammarField grammarField = new GrammarField();

    protected Class<T> grammarInfoClass = null;

    protected List<T> grammarInfos = null;

    protected TemplateType templateType = TemplateType.Text;

    public AbstractGrammarGenerate() {
        this.configuration = TemplateUtil.getConfiguration();
        this.joinFactory = TemplateUtil.getJoinFactory();
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
    public List<T> getGrammarInfos() {
        return grammarInfos;
    }
}
