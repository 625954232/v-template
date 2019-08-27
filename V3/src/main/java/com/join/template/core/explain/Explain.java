package com.join.template.core.explain;


import com.join.template.core.Element;
import com.join.template.core.constant.TemplateType;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.type.TypeInfo;

import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法属性解释及效验
 * @date 2019/8/19 12:18
 */
public interface Explain {

    /**
     * 效验节点属性
     *
     * @param element 节点
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:53
     */
    void verifyElement(Element element);

    /**
     * 效验节点属性
     *
     * @param original   语法原文
     * @param endElement 是否是结束节点
     * @param attr       节点属性
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:53
     */
    void verifyElement(String original, Boolean endElement, Map<String, String> attr);

    /**
     * 获取语法解释
     *
     * @param
     * @return java.util.Map<java.lang.String                                                                                                                               ,                                                               java.lang.String>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:54
     */
    String getGrammarExplain();


    /**
     * 根据Map生成语法
     *
     *
     * @param templateType
     * @param grammarInfo
     * @param map
     * @param field
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 10:35
     */
    String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, Map map, GrammarField field);

    /**
     * 根据实体、字段、方法等信息生成语法
     *
     *
     * @param templateType
     * @param grammarInfo
     * @param typeInfo
     * @param grammarField
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 15:59
     */
    String genGrammar(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GrammarField grammarField);

}
