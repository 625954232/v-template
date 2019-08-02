package com.join.template.core;

import com.join.template.context.Content;
import com.join.template.factory.JoinFactory;
import com.join.template.node.Element;

import java.io.Writer;

public interface Template {
    /**
     * 模版初始化
     *
     * @param name 模版名称
     * @param text 模版内容
     * @return
     */
    Template init(String name, String text);

    /**
     * 写入模版所需的参数
     *
     * @param name  值名称
     * @param value 值
     * @return
     */
    Template putValue(String name, Object value);

    /**
     * 写入模版所需的内容
     *
     * @param content 模版所需的内容,类似MAP结构
     * @return
     */
    Template putContext(Content content);

    /**
     * c
     *
     * @param writer
     * @return
     */
    Template process(Writer writer);

    /**
     * 获取总工厂
     *
     * @return
     */
    JoinFactory getJoinFactory();

    /**
     * 获取模版所需的内容
     *
     * @return
     */
    Content getContent();

    /**
     * 获取父节点
     *
     * @return
     */
    Element getRoot();

    /**
     * 处理模版返回字符串
     *
     * @return
     */
    String process();

    /**
     * 获取模版内容
     *
     * @return
     */
    String getTemplateContent();

    /**
     * 获取模版名称     *
     *
     * @return
     */
    String getTemplateName();


    /**
     * 获取模版行数
     *
     * @return
     */
    int getLineSize();
}
