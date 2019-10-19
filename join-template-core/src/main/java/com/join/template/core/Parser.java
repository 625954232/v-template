package com.join.template.core;

import com.join.template.core.element.Element;
import com.join.template.core.factory.JoinFactory;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法解析器
 * @date 2019/8/19 11:55
 */
public interface Parser {

    /**
     * 设置模版工厂
     *
     * @param joinFactory
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:51
     */
    Parser setJoinFactory(JoinFactory joinFactory);

    /**
     * 设置模版
     *
     * @param template
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:51
     */
    Parser setTemplate(Template template);


    /**
     * 设置匹配节点的开始标记
     *
     * @param matchBeginTag
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:48
     */
    Parser setMatchBeginTag(String matchBeginTag);

    /**
     * 设置匹配节点的结束标记
     *
     * @param matchEndTag
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:49
     */
    Parser setMatchEndTag(String matchEndTag);

    Parser setGrammar(Boolean grammar);

    Parser setVarExpr(Boolean varExpr);

    /**
     * 设置文本内容
     *
     * @param content
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:51
     */
    Parser setContent(String content);

    /**
     * 设置是否是结束节点
     *
     * @param endElement
     * @return com.join.template.core.Parser
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:52
     */
    Parser setEndElement(Boolean endElement);

    /**
     * 解析
     *
     * @param
     * @return com.join.template.core.elementClass.ElementBuilder
     * @author CAOYOU/625954232@qq.com
     * @date 2019/10/18 17:52
     */
    Element parser();


}
