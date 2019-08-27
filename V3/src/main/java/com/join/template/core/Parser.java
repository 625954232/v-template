package com.join.template.core;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法解析器
 * @date 2019/8/19 11:55
 */
public interface Parser {


    /**
     * 解析
     *
     *
     * @param template
     * @param matchBeginTag 匹配节点的开始标记
     * @param matchEndTag   匹配节点的结束标记
     * @param text          文本内容
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:55
     */
    Element parser(Template template, String matchBeginTag, String matchEndTag, String text);
}
