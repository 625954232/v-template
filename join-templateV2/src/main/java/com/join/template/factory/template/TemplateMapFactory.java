package com.join.template.factory.template;


import com.join.template.core.DefaultTemplate;
import com.join.template.core.Template;
import com.join.template.factory.JoinFactory;
import com.join.template.util.IOUtil;
import com.join.template.verify.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 模版缓存
 */
public class TemplateMapFactory implements TemplateFactory {
    private JoinFactory joinFactory;

    private Map<String, Template> templateMap = new HashMap<>();

    public TemplateMapFactory(JoinFactory joinFactory) {
        this.joinFactory = joinFactory;
    }


    /**
     * 加载字符串模版
     *
     * @param name
     * @param text
     * @return
     */
    @Override
    public Template putTemplate(String name, String text) {
        if (templateMap.containsKey(name))
            return templateMap.get(name);
        removeUnnecessary();
        //初始化模版
        Template template = new DefaultTemplate(joinFactory, name, text);
        templateMap.put(name, template);
        return template;
    }

    /**
     * 加载文件模版
     *
     * @param fileName
     * @return
     */
    @Override
    public Template putTemplate(String fileName) {
        File resource = joinFactory.getConfiguration().getResource(fileName);
        Assert.ifTrue(!resource.exists(), fileName + "该模版不存在");
        //判断模版是否存在
        if (templateMap.containsKey(resource.getName())) {
            return templateMap.get(resource.getName());
        }
        removeUnnecessary();
        //初始化模版
        String text = IOUtil.toString(resource);
        Template template = new DefaultTemplate(joinFactory, resource.getName(), text);
        templateMap.put(fileName, template);
        return template;
    }

    @Override
    public Template getTemplate(String name) {
        return templateMap.get(name);
    }


    //超过设置的模版缓存数就随机删除
    private void removeUnnecessary() {
        int size = templateMap.size();
        if (size == joinFactory.getConfiguration().getTemplateCacheSize()) {
            int ran = (int) (Math.random() * (size + 1));
            int i = 0;
            for (String e : templateMap.keySet()) {
                if (i >= ran) {
                    templateMap.remove(e);
                    return;
                }
            }
        }
    }

}
