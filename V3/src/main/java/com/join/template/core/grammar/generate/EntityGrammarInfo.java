package com.join.template.core.grammar.generate;

import com.join.template.core.constant.EntityType;
import com.join.template.core.grammar.GrammarInfo;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAOYOU/625954232@qq.com
 * @Title: 语法生成信息
 * @date 2019/8/20 14:08
 */
@Getter
public class EntityGrammarInfo implements GrammarInfo {
    /**
     * 字段名称
     */
    protected String name;
    /**
     * 字段描述
     */
    protected String describe;
    /**
     * 字段类型
     */
    protected EntityType type;
    /**
     * 字段
     */
    protected String grammar;
    /**
     * 语法类型/表达式类型
     */
    protected Integer grammarType;
    /**
     * 父级字段名称
     */
    protected String parentName;
    /**
     * 父级字段类型
     */
    protected EntityType parentType;
    /**
     * 子集字段
     */
    protected List<GrammarInfo> childs = new ArrayList<>();

    @Override
    public GrammarInfo setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public GrammarInfo setDescribe(String describe) {
        this.describe = describe;
        return this;
    }

    @Override
    public GrammarInfo setType(EntityType type) {
        this.type = type;
        return this;
    }

    @Override
    public GrammarInfo setGrammar(String grammar) {
        this.grammar = grammar;
        return this;
    }

    @Override
    public GrammarInfo setGrammarType(Integer grammarType) {
        this.grammarType = grammarType;
        return this;
    }

    @Override
    public GrammarInfo setParentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    @Override
    public GrammarInfo setParentType(EntityType parentType) {
        this.parentType = parentType;
        return this;
    }

    @Override
    public GrammarInfo setChilds(List<GrammarInfo> childs) {
        this.childs = childs;
        return this;
    }
}
