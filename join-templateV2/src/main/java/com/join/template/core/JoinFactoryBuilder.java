package com.join.template.core;

import com.join.template.configuration.Configuration;
import com.join.template.configuration.ExprConfig;
import com.join.template.factory.JoinFactory;
import com.join.template.factory.JoinFactoryBase;
import com.join.template.factory.template.TemplateFactory;
import com.join.template.process.Process;


public class JoinFactoryBuilder {

    JoinFactory joinFactory;

    public JoinFactoryBuilder() {
        this(new Configuration());
    }

    public JoinFactoryBuilder(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }

    public JoinFactoryBuilder(Configuration configuration) {
        this.joinFactory = new JoinFactoryBase(configuration);
    }


    /**
     * 缓存模板
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    public Template putTemplate(String name, String content) {
        return joinFactory.putTemplate(name, content);
    }

    /**
     * 缓存模板
     *
     * @param fileName 文件路径
     * @return
     */
    public Template putTemplate(String fileName) {
        return joinFactory.putTemplate(fileName);
    }

    /**
     * 返回模版总工厂
     *
     * @return
     */
    public JoinFactory builder() {
        return joinFactory;
    }
}
