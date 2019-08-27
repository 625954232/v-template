package com.join.template.core;

import com.join.template.core.constant.TemplateType;
import com.join.template.core.context.Content;
import com.join.template.core.factory.JoinFactory;

import java.io.Writer;
import java.util.List;

public interface Template {

    /**
     * 写入模版所需的参数
     *
     * @param name  值名称
     * @param value 值
     * @return com.join.template.core.Template
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:17
     */
    Template putValue(String name, Object value);

    /**
     * 写入模版所需的内容
     *
     * @param content 模版所需的内容,类似MAP结构
     * @return com.join.template.core.Template
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    Template putContext(Content content);

    /**
     * 处理
     *
     * @param writer 写入
     * @return com.join.template.core.Template
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    Template process(Writer writer);

    /**
     * 获取模版类型
     *
     * @param
     * @return com.join.template.core.constant.TemplateType
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/27 14:22
     */
    TemplateType getTemplateType();

    /**
     * 获取总工厂
     *
     * @param
     * @return com.join.template.core.factory.JoinFactory
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    JoinFactory getJoinFactory();

    /**
     * 获取模版所需的内容
     *
     * @param
     * @return com.join.template.core.context.Content
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    Content getContent();

    /**
     * 获取全部节点
     *
     * @param
     * @return java.util.List<com.join.template.core.Element>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    List<Element> getAllElement();

    /**
     * 获取父节点
     *
     * @param
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:16
     */
    Element getRootElement();

    /**
     * 处理模版返回字符串
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:15
     */
    String process();

    /**
     * 获取模版内容
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:15
     */
    String getText();

    /**
     * 获取模版名称
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:02
     */
    String getName();

    /**
     * 获取模版行数
     *
     * @param
     * @return int
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 12:02
     */
    int getLineSize();
}
