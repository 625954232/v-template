package com.join.template.core.util;


import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.verify.TemplateException;

public class TemplateUtil {

    protected final static TemplateUtil instance = new TemplateUtil();

    protected Configuration configuration = null;

    protected JoinFactory joinFactory = null;


    public static void setConfiguration(Configuration configuration) {
        instance.configuration = configuration;
    }

    public static Configuration getConfiguration() {
        Configuration configuration = instance.configuration;
        if (configuration == null) {
            throw new TemplateException("请先创建配置");
        }
        return configuration;
    }

    public static void setJoinFactory(JoinFactory joinFactory) {
        instance.joinFactory = joinFactory;
    }

    public static JoinFactory getJoinFactory() {
        JoinFactory joinFactory = instance.joinFactory;
        if (joinFactory == null) {
            throw new TemplateException("请先创建模板总工厂");
        }
        return joinFactory;
    }
}
