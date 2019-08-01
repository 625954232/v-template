package com.join.template.factory.template;

import com.join.template.core.Template;

public interface TemplateFactory {

    /**
     * 缓存模板
     *
     * @param name    模板名称
     * @param content 模板内容
     * @return
     */
    Template putTemplate(String name, String content);

    /**
     * 缓存模板
     *
     * @param fileName 文件路径
     * @return
     */
    Template putTemplate(String fileName);

    /**
     * 获取模板
     *
     * @param name 文件名称或模板名称
     * @return
     */
    Template getTemplate(String name);
}
