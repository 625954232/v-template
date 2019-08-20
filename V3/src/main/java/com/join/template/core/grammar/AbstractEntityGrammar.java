package com.join.template.core.grammar;

import com.join.template.core.configuration.Configuration;
import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.util.TemplateUtil;
import com.join.template.core.verify.TemplateException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractEntityGrammar implements EntityGrammar {

    protected final JoinFactory joinFactory;

    protected final Configuration configuration;

    protected String name;

    protected String describe;

    protected EntityType type;

    protected String grammar;

    protected Integer grammarType;

    protected String parentName;

    protected EntityType parentType;

    protected List<EntityGrammar> childs = new ArrayList<>();

    protected GrammarGenListener grammarGenListener;


    public AbstractEntityGrammar() {
        this.configuration = TemplateUtil.getConfiguration();
        this.joinFactory = TemplateUtil.getJoinFactory();
    }

    @Override
    public EntityGrammar setGrammarGenListener(GrammarGenListener grammarGenListener) {
        this.grammarGenListener = grammarGenListener;
        return this;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public void setType(EntityType type) {
        this.type = type;
    }

    @Override
    public void setGrammar(String grammar) {
        this.grammar = grammar;
    }

    @Override
    public void setGrammarType(Integer grammarType) {
        this.grammarType = grammarType;
    }

    @Override
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public void setParentType(EntityType parentType) {
        this.parentType = parentType;
    }

    @Override
    public void setChilds(List<EntityGrammar> childs) {
        this.childs = childs;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescribe() {
        return describe;
    }

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public String getGrammar() {
        return grammar;
    }

    @Override
    public Integer getGrammarType() {
        return grammarType;
    }

    @Override
    public String getParentName() {
        return parentName;
    }

    @Override
    public EntityType getParentType() {
        return parentType;
    }

    @Override
    public List<EntityGrammar> getChilds() {
        return childs;
    }


}
