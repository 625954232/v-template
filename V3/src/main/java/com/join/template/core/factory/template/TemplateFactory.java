package com.join.template.core.factory.template;

import com.join.template.core.Template;
import com.join.template.text.JoinFactoryBase;

/**  
 * @Title: 模版工厂
 * @author CAOYOU/625954232@qq.com
 * @date 2019/8/19 11:53
 */
public interface TemplateFactory<This> {

    /**
     * 缓存模板
     *
     * @param name 模板名称
     * @param text 模板内容
     * @return This
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:43
     */
    This putTemplate(String name, String text);


    /**
     * 缓存模板
     *
     * @param fileName 文件路径
     * @return This
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:44
     */
    This putTemplate(String fileName);


    /**
     * 获取模板
     *
     * @param name 文件名称或模板名称
     * @return com.join.template.core.Template
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:44
     */
    Template getTemplate(String name);
}
