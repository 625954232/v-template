package com.join.template.core.factory.template;


import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.IOUtil;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.Assert;
import com.join.template.text.DefaultTemplate;
import com.join.template.text.JoinFactoryBase;

import java.io.File;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 单一模版
 * @date 2019/8/19 11:45
 */
public class TemplateSingleFactory implements TemplateFactory<Template> {

    @Override
    public Template putTemplate(String name, String text) {
        Template tb = new DefaultTemplate(name, text);
        return tb;
    }

    @Override
    public Template putTemplate(String fileName) {
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        File resource = joinFactory.getConfiguration().getResource(fileName);
        Assert.ifTrue(!resource.exists(), fileName + "该模版不存在");

        String name = resource.getName();
        String text = IOUtil.toString(resource);
        Template template = new DefaultTemplate(name, text);
        return template;
    }

    @Override
    public Template getTemplate(String fileName) {
        return putTemplate(fileName);
    }
}
