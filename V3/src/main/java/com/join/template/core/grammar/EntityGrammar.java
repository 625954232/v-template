package com.join.template.core.grammar;

import com.join.template.core.constant.EntityType;
import com.join.template.core.listener.GrammarGenListener;

import java.util.List;
import java.util.Map;

/**
 * @author CAOYOU
 * @Title: 实体类语法生成器
 * @date 2019/8/1915:49
 */
public interface EntityGrammar {

    /**
     * 生成语法生成监听
     *
     * @param grammarGenListener
     */
    EntityGrammar setGrammarGenListener(GrammarGenListener grammarGenListener);

    /**
     * 根据实体类生成语法
     *
     * @param name  别名
     * @param clazz 对象属性
     * @return com.join.template.core.grammar.EntityGrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/19 16:25
     */
    EntityGrammar generateGrammar(String name, Class clazz);

    /**
     * 根据实体类生成语法
     *
     * @param name      别名
     * @param map       Map结构的字段信息
     * @param fieldName 对应字段信息
     * @return
     */
    EntityGrammar generateGrammar(String name, List<Map> map, FieldName fieldName);

    void setName(String name);

    void setDescribe(String describe);

    void setType(EntityType type);

    void setGrammar(String grammar);

    void setGrammarType(Integer grammarType);

    void setParentName(String parentName);

    void setParentType(EntityType parentType);

    void setChilds(List<EntityGrammar> childs);

    String getName();

    String getDescribe();

    EntityType getType();

    String getGrammar();

    Integer getGrammarType();

    String getParentName();

    EntityType getParentType();

    List<EntityGrammar> getChilds();

    GrammarGenListener getGrammarGenListener();
}
