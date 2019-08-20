package com.join.template.core.grammar;

import com.join.template.core.grammar.generate.GrammarField;
import com.join.template.core.listener.GrammarGenListener;

import java.util.List;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: 实体类语法生成器
 * @date 2019/8/1915:49
 */
public interface GrammarGenerate {

    /**
     * 设置语法信息类
     *
     * @param grammarInfo
     * @return com.join.template.core.grammar.GrammarGenerate
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:15
     */
    GrammarGenerate setGrammarInfo(Class<? extends GrammarInfo> grammarInfo);

    /**
     * 生成语法生成监听
     *
     * @param grammarGenListener
     */
    GrammarGenerate setGrammarGenListener(GrammarGenListener grammarGenListener);

    /**
     * 根据实体类生成语法
     *
     * @param name  别名
     * @param clazz 对象属性
     * @return com.join.template.core.grammar.generate.EntityGrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:25
     */
    GrammarInfo generateGrammar(String name, Class clazz);

    /**
     * 根据实体类生成语法
     *
     * @param name      别名
     * @param map       Map结构的字段信息
     * @param fieldName 对应字段信息
     * @return
     */
    GrammarInfo generateGrammar(String name, List<Map> map, GrammarField fieldName);

}
