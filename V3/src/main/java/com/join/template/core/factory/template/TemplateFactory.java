package com.join.template.core.factory.template;

import com.join.template.core.Template;
import com.join.template.text.JoinFactoryBase;

public interface TemplateFactory<This> {

    /**
     * 缓存模板
     *
     * @param name 模板名称
     * @param text 模板内容
     * @return
     */
    This putTemplate(String name, String text);

    /**
     * 缓存模板
     *
     * @param fileName 文件路径
     * @return
     */
    This putTemplate(String fileName);

    /**
     * 获取模板
     *
     * @param name 文件名称或模板名称
     * @return
     */
    Template getTemplate(String name);
}
