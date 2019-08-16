package com.join.template.core.util;


import com.join.template.core.configuration.Configuration;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.verify.TemplateException;

public class TemplateUtil {
    protected final static ThreadLocal<Configuration> configurationLocal = new ThreadLocal<>();
    protected final static ThreadLocal<JoinFactory> joinFactoryLocal = new ThreadLocal<>();

    public static void setConfiguration(Configuration configuration) {
        TemplateUtil.configurationLocal.set(configuration);
    }

    public static Configuration getConfiguration() {
        Configuration configuration = TemplateUtil.configurationLocal.get();
        if (configuration == null) {
            throw new TemplateException("请先创建配置");
        }
        return configuration;
    }

    public static void setJoinFactory(JoinFactory joinFactory) {
        TemplateUtil.joinFactoryLocal.set(joinFactory);
    }

    public static JoinFactory getJoinFactory() {
        JoinFactory joinFactory = TemplateUtil.joinFactoryLocal.get();
        if (joinFactory == null) {
            throw new TemplateException("请先创建模板总工厂");
        }
        return joinFactory;
    }
}
