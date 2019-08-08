package com.join.template.factory.template;


import com.join.template.core.DefaultTemplate;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.util.IOUtil;
import com.join.template.verify.Assert;

import java.io.File;

/**
 * 单一模版
 */
public class TemplateSingleFactory implements TemplateFactory {
    private final JoinFactory joinFactory;

    public TemplateSingleFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }


    @Override
    public Template putTemplate(String name, String text) {
        Template tb = new DefaultTemplate(joinFactory, name, text);
        return tb;
    }

    @Override
    public Template putTemplate(String fileName) {
        File resource = joinFactory.getConfiguration().getResource(fileName);
        Assert.ifTrue(!resource.exists(), fileName + "该模版不存在");

        String name = resource.getName();
        String text = IOUtil.toString(resource);
        Template template = new DefaultTemplate(joinFactory, name, text);
        return template;
    }

    @Override
    public Template getTemplate(String fileName) {
        return putTemplate(fileName);
    }
}
