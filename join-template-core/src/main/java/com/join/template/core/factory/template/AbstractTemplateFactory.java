package com.join.template.core.factory.template;

import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;

/**
 * @author CAOYOU
 * @Title: TemplateAFactory
 * @date 2019/9/614:17
 */
public abstract class AbstractTemplateFactory implements TemplateFactory {

    protected final JoinFactory joinFactory;

    public AbstractTemplateFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }

    @Override
    public Template putTemplate(String name, String text) {
        return joinFactory.putTemplate(name, text);
    }

    @Override
    public Template getTemplate(String name) {
        return joinFactory.getTemplate(name);
    }
}
