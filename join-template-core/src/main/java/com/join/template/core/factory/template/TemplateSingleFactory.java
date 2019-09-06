package com.join.template.core.factory.template;


import com.join.template.core.factory.JoinFactory;


/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 单一模版
 * @date 2019/8/19 11:45
 */
public class TemplateSingleFactory extends AbstractTemplateFactory implements TemplateFactory {


    public TemplateSingleFactory(JoinFactory joinFactory) {
        super(joinFactory);
    }
}
