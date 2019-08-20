package com.join.template.core.grammar;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.EntityType;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.TemplateUtil;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法生成信息
 * @date 2019/8/20 14:08
 */
public interface GrammarInfo {
    /**
     * 设置字段名称
     *
     * @param name 字段名称
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:52
     */
    public GrammarInfo name(String name);

    /**
     * 设置字段描述
     *
     * @param describe 字段描述
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:53
     */
    public GrammarInfo describe(String describe);

    /**
     * 设置字段类型
     *
     * @param type 字段类型
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:54
     */
    public GrammarInfo type(EntityType type);

    /**
     * 设置语法
     *
     * @param grammar 语法
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:54
     */
    public GrammarInfo grammar(String grammar);

    /**
     * 设置语法类型/表达式类型
     *
     * @param grammarType 语法类型/表达式类型
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:55
     */
    public GrammarInfo grammarType(Integer grammarType);

    /**
     * 设置父级字段名称
     *
     * @param parentName 父级字段名称
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:57
     */
    public GrammarInfo parentName(String parentName);

    /**
     * 设置父级字段类型
     *
     * @param parentType 父级字段类型
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:57
     */
    public GrammarInfo parentType(EntityType parentType);

    /**
     * 设置子集字段信息
     *
     * @param grammarInfo 子集字段信息
     * @return com.join.template.core.explain.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 14:59
     */
    public GrammarInfo addChild(GrammarInfo grammarInfo);

    /**
     * 获取字段名称
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public String getName();

    /**
     * 获取字段描述
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public String getDescribe();

    /**
     * 获取字段类型
     *
     * @param
     * @return com.join.template.core.constant.EntityType
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public EntityType getType();

    /**
     * 获取语法
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public String getGrammar();

    /**
     * 获取语法类型/表达式类型
     *
     * @param
     * @return java.lang.Integer
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public Integer getGrammarType();

    /**
     * 获取父级字段名称
     *
     * @param
     * @return java.lang.String
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public String getParentName();

    /**
     * 获取父级字段类型
     *
     * @param
     * @return com.join.template.core.constant.EntityType
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public EntityType getParentType();

    /**
     * 获取子集字段
     *
     * @param
     * @return java.util.List<com.join.template.core.explain.GrammarInfo>
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/20 15:00
     */
    public List<GrammarInfo> getChilds();
}
