package com.join.template.core.grammar.generate;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.TemplateUtil;
import lombok.Getter;

@Getter
public abstract class AbstractGrammarGenerate implements GrammarGenerate {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    protected GrammarGenListener grammarGenListener;

    protected Class<? extends GrammarInfo> grammarInfoClass = null;

    public AbstractGrammarGenerate() {
        this.configuration = TemplateUtil.getConfiguration();
        this.joinFactory = TemplateUtil.getJoinFactory();
    }

    @Override
    public GrammarGenerate setGrammarInfo(Class<? extends GrammarInfo> grammarInfo) {
        this.grammarInfoClass = grammarInfo;
        return this;
    }

    @Override
    public GrammarGenerate setGrammarGenListener(GrammarGenListener grammarGenListener) {
        this.grammarGenListener = grammarGenListener;
        return this;
    }


}
