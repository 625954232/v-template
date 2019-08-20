package com.join.template.core.grammar.generate;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.verify.TemplateException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class EntityGenerate extends AbstractGrammarGenerate implements GrammarGenerate {

    public EntityGenerate() {
        grammarInfoClass = EntityGrammarInfo.class;
    }

    @Override
    public GrammarInfo generateGrammar(String name, Class clazz) {
        try {
            GrammarInfo grammarInfo = grammarInfoClass.newInstance();
            grammarInfo.name(name);
            grammarInfo.type(EntityType.Object);
            generateGrammar(grammarInfo, clazz);
            return grammarInfo;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }

    @Override
    public GrammarInfo generateGrammar(String name, List<Map> map, GrammarField field) {
        try {
            Map<String, Object> root = new HashMap<>();
            root.put(field.getNameFieldName(), name);
            root.put(field.getTypeFieldName(), EntityType.Object.name());
            root.put(field.getChildFieldName(), map);
            GrammarInfo entityGrammar = this.generateFieldGrammar(null, root, field);
            return entityGrammar;
        } catch (InstantiationException e) {
            throw new TemplateException("语法生成失败", e);
        } catch (IllegalAccessException e) {
            throw new TemplateException("语法生成失败", e);
        }
    }

    private void generateGrammar(GrammarInfo rootClass, Class clazz) throws IllegalAccessException, InstantiationException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (this.getClass() == field.getType()) {
                continue;
            }
            GrammarInfo grammarInfo = grammarInfoClass.newInstance();
            grammarInfo.name(field.getName());
            grammarInfo.type(EntityType.of(field.getType()));
            grammarInfo.parentName(rootClass.getName());
            grammarInfo.parentType(rootClass.getType());
            if (clazz != field.getType() && !ClassUtil.isBaseType(field.getType())) {
                if (Collection.class.isAssignableFrom(field.getType())) {
                    grammarInfo.grammarType(Constant.EXPR_LIST);
                    Class generic = ClassUtil.getGeneric(field.getGenericType());
                    generateGrammar(grammarInfo, generic);
                } else {
                    grammarInfo.grammarType(Constant.EXPR_VAR);
                    generateGrammar(grammarInfo, field.getType());
                }
            } else {
                grammarInfo.grammarType(Constant.EXPR_VAR);
            }
            rootClass.addChild(grammarInfo);
        }
    }

    private void generateGrammar(GrammarInfo parent, Collection<Map> data, GrammarField field) throws InstantiationException, IllegalAccessException {
        for (Map map : data) {
            GrammarInfo entityGrammar = this.generateFieldGrammar(parent, map, field);
            parent.addChild(entityGrammar);
        }
    }

    private GrammarInfo generateFieldGrammar(GrammarInfo parent, Map map, GrammarField field) throws IllegalAccessException, InstantiationException {
        Object fieldName = map.get(field.getNameFieldName());
        if (fieldName == null) {
            throw new TemplateException("缺少名称的对应字段名称");
        }
        Object fieldType = map.get(field.getTypeFieldName());
        Object fieldDescribe = map.get(field.getDescribeFieldName());

        GrammarInfo grammarInfo = grammarInfoClass.newInstance();
        grammarInfo.name(fieldName.toString());
        if (parent != null) {
            grammarInfo.parentName(parent.getName());
            grammarInfo.parentType(parent.getType());
        }
        if (fieldType != null) {
            grammarInfo.type(EntityType.of(fieldType.toString()));
        } else {
            grammarInfo.type(EntityType.of(field.getTypeFieldName()));
        }
        if (fieldDescribe != null) {
            grammarInfo.describe(fieldDescribe.toString());
        } else {
            grammarInfo.describe(fieldName.toString());
        }
        if (grammarGenListener != null) {
            grammarGenListener.onCreate(map, field, grammarInfo);
        }
        if (EntityType.Array == grammarInfo.getType()) {
            grammarInfo.grammarType(Constant.EXPR_LIST);
        } else if (EntityType.Entity == grammarInfo.getType()) {
            grammarInfo.grammarType(Constant.EXPR_VAR);
        } else {
            grammarInfo.grammarType(Constant.EXPR_VAR);
        }
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(grammarInfo.getGrammarType());
        if (expressionHandle != null && expressionHandle.getGrammarExpl() != null) {
            String grammar = expressionHandle.getGrammarExpl().genGrammar(grammarInfo, map, field);
            grammarInfo.grammar(grammar);
        }
        if (EntityType.Array == grammarInfo.getType() || EntityType.Entity == grammarInfo.getType()) {
            Object obj = map.get(field.getChildFieldName());
            if (obj == null) {
                return grammarInfo;
            }
            if (obj instanceof Collection) {
                List<Map> maps = (List<Map>) obj;
                generateGrammar(grammarInfo, maps, field);
            } else {
                throw new TemplateException("错误的集合：" + grammarInfo.getName());
            }
        }
        return grammarInfo;
    }


}
