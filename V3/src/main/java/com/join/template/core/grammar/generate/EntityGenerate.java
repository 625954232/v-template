package com.join.template.core.grammar.generate;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.util.Utils;
import com.join.template.core.verify.TemplateException;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EntityGenerate extends AbstractGrammarGenerate<EntityGrammarInfo> implements GrammarGenerate<EntityGrammarInfo> {

    public EntityGenerate() {
        super.setGrammarInfo(EntityGrammarInfo.class);
    }


    @Override
    public EntityGenerate generateGrammarRoot(String name, Class clazz) {
        try {
            EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
            this.createGrammarInfo(grammarInfo, clazz);
            this.getGrammarInfos().add(grammarInfo);
            return this;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }

    @Override
    public EntityGenerate generateGrammarRoot(String name, List<Map> map) {
        try {
            Map<String, Object> root = new HashMap<>();
            root.put(this.getGrammarField().getNameField(), name);
            root.put(this.getGrammarField().getTypeField(), EntityType.Object.name());
            root.put(this.getGrammarField().getChildField(), map);
            EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
            this.createGrammarInfo(grammarInfo, root);
            this.getGrammarInfos().add(grammarInfo);
            return this;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }


    @Override
    public EntityGenerate generateGrammar(Class clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (this.getClass() == field.getType()) {
                    continue;
                }
                EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
                this.createGrammarInfo(grammarInfo, field);
                this.getGrammarInfos().add(grammarInfo);
            }
            return this;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }

    @Override
    public EntityGenerate generateGrammar(List<Map> maps) {
        try {
            for (Map map : maps) {
                EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
                this.createGrammarInfo(grammarInfo, map);
                this.getGrammarInfos().add(grammarInfo);
            }
            return this;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }

    private EntityGrammarInfo createGrammarInfo(EntityGrammarInfo current, Object clazzOrField) throws IllegalAccessException, InstantiationException {
        TypeInfo typeInfo = TypeInfo.get(clazzOrField);
        current.name(typeInfo.getName());
        current.type(EntityType.of(typeInfo.getType()));
        if (current.getGrammarType() != null) {
            if (EntityType.Array == current.getType()) {
                current.grammarType(Constant.EXPR_LIST);
            } else {
                current.grammarType(Constant.EXPR_VAR);
            }
        }
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(current.getGrammarType());
        if (expressionHandle != null && expressionHandle.getGrammarExpl() != null) {
            String grammar = expressionHandle.getGrammarExpl().genGrammar(current, typeInfo, this.getGrammarField());
            current.grammar(grammar);
        }
        this.createGrammarChild(current, typeInfo);
        return current;
    }

    private void createGrammarChild(EntityGrammarInfo current, TypeInfo typeInfo) throws InstantiationException, IllegalAccessException {
        if (EntityType.Array == current.getType() || EntityType.Entity == current.getType()) {
            Class clazz_ = null;
            if (Collection.class.isAssignableFrom(typeInfo.getType())) {
                clazz_ = ClassUtil.getClassGenricType(typeInfo.getGenericType());
            } else {
                clazz_ = typeInfo.getType();
            }
            Field[] fields = clazz_.getDeclaredFields();
            for (Field item : fields) {
                if (this.getClass() == item.getType()) {
                    continue;
                }
                EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
                grammarInfo.parentName(current.getName());
                grammarInfo.parentType(current.getType());
                this.createGrammarInfo(grammarInfo, item);
                current.addChild(grammarInfo);
            }
        }
    }


    private void createGrammarInfo(EntityGrammarInfo current, Map map) throws IllegalAccessException, InstantiationException {
        String fieldName = String.valueOf(map.get(this.getGrammarField().getNameField()));
        if (fieldName == null) {
            throw new TemplateException("缺少名称的对应字段名称");
        }

        String fieldType = String.valueOf(map.get(this.getGrammarField().getTypeField()));
        String fieldDescribe = String.valueOf(map.get(this.getGrammarField().getDescribeField()));

        fieldType = Utils.returnOrDefault(fieldType, this.getGrammarField().getTypeField());
        fieldDescribe = Utils.returnOrDefault(fieldDescribe, fieldName);

        current.name(fieldName);
        current.type(EntityType.of(fieldType));
        current.describe(fieldDescribe);

        if (this.getGrammarGenListener() != null) {
            this.getGrammarGenListener().onCreate(map, current);
        }

        if (current.getGrammarType() != null) {
            if (EntityType.Array == current.getType()) {
                current.grammarType(Constant.EXPR_LIST);
            } else {
                current.grammarType(Constant.EXPR_VAR);
            }
        }
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(current.getGrammarType());
        if (expressionHandle != null && expressionHandle.getGrammarExpl() != null) {
            String grammar = expressionHandle.getGrammarExpl().genGrammar(current, map, this.getGrammarField());
            current.grammar(grammar);
        }
        this.createGrammarChild(current, map);
    }

    private void createGrammarChild(EntityGrammarInfo current, Map map) throws InstantiationException, IllegalAccessException {
        if (EntityType.Array == current.getType() || EntityType.Entity == current.getType()) {
            Object obj = map.get(this.getGrammarField().getChildField());
            if (obj == null) {
                return;
            }
            if (obj instanceof Collection) {
                List<Map> maps = (List<Map>) obj;
                for (Map item : maps) {
                    EntityGrammarInfo grammarInfo = this.getGrammarInfoClass().newInstance();
                    grammarInfo.parentName(current.getName());
                    grammarInfo.parentType(current.getType());
                    this.createGrammarInfo(grammarInfo, item);
                    current.addChild(grammarInfo);
                }
            } else {
                throw new TemplateException("错误的集合：" + current.getName());
            }
        }
    }


}
