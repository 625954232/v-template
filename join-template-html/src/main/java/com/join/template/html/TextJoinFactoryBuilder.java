package com.join.template.html;

import com.join.template.core.Parser;
import com.join.template.core.configuration.Configuration;
import com.join.template.core.expression.ExprActuator;
import com.join.template.core.expression.ExprAttr;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.expression.ExprHandleBuilder;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.JoinFactoryBuilder;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.grammar.GrammarGenerate;

/**
 * @author CAOYOU
 * @Title: JoinFactoryBuilder
 * @date 2019/9/616:03
 */
public class TextJoinFactoryBuilder implements JoinFactoryBuilder {
    private JoinFactoryBuilder joinFactoryBuilder;

    public TextJoinFactoryBuilder() {
        this(new Configuration());
    }

    public TextJoinFactoryBuilder(Configuration configuration) {
        this.joinFactoryBuilder = new HtmlJoinFactory(configuration);
    }


    @Override
    public JoinFactoryBuilder setParser(Class<? extends Parser> parser) {
        return joinFactoryBuilder.setParser(parser);
    }

    @Override
    public JoinFactoryBuilder setExprAttr(Class<? extends ExprAttr> exprAttr) {
        return joinFactoryBuilder.setExprAttr(exprAttr);
    }

    @Override
    public JoinFactoryBuilder setExprActuator(Class<? extends ExprActuator> expression) {
        return joinFactoryBuilder.setExprActuator(expression);
    }

    @Override
    public JoinFactoryBuilder setGrammarGenerate(Class<? extends GrammarGenerate> grammarGenerate) {
        return joinFactoryBuilder.setGrammarGenerate(grammarGenerate);
    }

    @Override
    public JoinFactoryBuilder addFactory(String nodeType, TemplateFactory templateFactory) {
        return joinFactoryBuilder.addFactory(nodeType, templateFactory);
    }

    @Override
    public JoinFactoryBuilder addExprHandle(ExprHandle exprHandle) {
        return joinFactoryBuilder.addExprHandle(exprHandle);
    }

    @Override
    public JoinFactoryBuilder buildExprHandle() {
        return joinFactoryBuilder.buildExprHandle();
    }

    @Override
    public JoinFactoryBuilder addTemplate(String name, String text) {
        return joinFactoryBuilder.addTemplate(name, text);
    }

    @Override
    public ExprHandleBuilder builderExprHandle() {
        return joinFactoryBuilder.builderExprHandle();
    }

    @Override
    public JoinFactory build() {
        return this.joinFactoryBuilder.build();
    }

}
