package com.join.template.core.grammar;

/**
 * @author CAOYOU
 * @Title: 实体类语法生成器
 * @date 2019/8/1915:49
 */
public interface EntityGrammar {

    /**
     * 根据实体类生成语法
     *
     * @param name
     * @param clazz
     * @return com.join.template.core.grammar.EntityGrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:25
     */
    EntityGrammarInfo generateGrammar(String name, Class clazz);
}
