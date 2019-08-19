package com.join.template.core;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 读取器
 * @date 2019/8/19 11:57
 */
public interface Reader {


    /**
     * 读取
     *
     * @param matchBeginTag 匹配节点的开始标记
     * @param matchEndTag   匹配节点的结束标记
     * @param text          文本内容
     * @return com.join.template.core.Element
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:55
     */
    Element reader(String matchBeginTag, String matchEndTag, String text);
}
