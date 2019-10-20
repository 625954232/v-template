package com.join.template.core.example;


import com.join.template.core.constant.TemplateType;
import com.join.template.core.grammar.handle.Grammar;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.grammar.generate.GenerateConfig;
import com.join.template.core.util.type.TypeInfo;

import java.util.Map;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法属性解释及效验
 * @date 2019/8/19 12:18
 */
public interface Example {
    /**
     * 设置表达式处理器
     *
     * @param grammar
     * @return com.join.template.core.example.Explain
     * @author CAOYOU/625954232@qq.com
     * @date 2019/9/9 14:06
     */
    Example setGrammar(Grammar grammar);


    /**
     * 获取语法解释
     *
     * @param
     * @return
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 11:54
     */
    String getExample();


    /**
     * 根据Map生成语法
     *
     * @param templateType
     * @param grammarInfo
     * @param map
     * @param generateConfig
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 10:35
     */
    default String genExample(TemplateType templateType, GrammarInfo grammarInfo, Map map, GenerateConfig generateConfig) {
        return "";
    }

    /**
     * 根据实体、字段、方法等信息生成语法
     *
     * @param templateType
     * @param grammarInfo
     * @param typeInfo
     * @param generateConfig
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 15:59
     */
    default String genExample(TemplateType templateType, GrammarInfo grammarInfo, TypeInfo typeInfo, GenerateConfig generateConfig) {
        return "";
    }


}
