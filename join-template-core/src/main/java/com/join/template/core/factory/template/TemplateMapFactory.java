package com.join.template.core.factory.template;


import com.join.template.core.Template;
import com.join.template.core.factory.JoinFactory;


import java.util.HashMap;
import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 模版缓存
 * @date 2019/8/19 11:45
 */
public class TemplateMapFactory extends AbstractTemplateFactory {

    private Map<String, Template> templateMap = new HashMap<>();


    public TemplateMapFactory(JoinFactory joinFactory) {
        super(joinFactory);
    }

    @Override
    public Template putTemplate(String name, String text) {
        if (templateMap.containsKey(name)) {
            return templateMap.get(name);
        }
        removeUnnecessary();
        return super.putTemplate(name, text);
    }


    @Override
    public Template getTemplate(String name) {
        if (templateMap.containsKey(name)) {
            return templateMap.get(name);
        }
        removeUnnecessary();
        return super.getTemplate(name);
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
