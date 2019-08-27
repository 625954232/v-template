package com.join.template.core.factory.template;


import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.util.IOUtil;
import com.join.template.core.util.ResourceFind;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.Assert;
import com.join.template.core.verify.TemplateException;
import com.join.template.text.DefaultTemplate;
import com.join.template.text.JoinFactoryBase;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 单一模版
 * @date 2019/8/19 11:45
 */
public class TemplateSingleFactory implements TemplateFactory<Template> {

    private String name;
    private String text;
    private Template template;


    @Override
    public Template putTemplate(String name, String text) {
        this.name = name;
        this.text = text;
        this.template = new DefaultTemplate(name, text);
        return template;
    }

    @Override
    public Template putTemplate(String fileName) {
        JoinFactory joinFactory = TemplateUtil.getJoinFactory();
        ResourceFind.ResourceInfo resource = joinFactory.getConfiguration().getResource(fileName);
        this.name = resource.getName();
        this.text = IOUtil.toString(resource.getIO());
        this.template = new DefaultTemplate(name, text);
        return template;
    }

    @Override
    public Template getTemplate(String fileName) {
        if (StringUtils.isBlank(name)) {
            return putTemplate(fileName);
        }
        if (this.template == null && !name.equals(this.template.getName())) {
            throw new TemplateException("未找到该模板");
        }
        return this.template;
    }
}
