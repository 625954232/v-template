package com.join.template.core.grammar;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.expression.ExpressionHandle;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.verify.TemplateException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GenEntityGrammar extends AbstractEntityGrammar implements EntityGrammar {


    @Override
    public EntityGrammar generateGrammar(String name, Class clazz) {
        GenEntityGrammar entityGrammarInfo = new GenEntityGrammar();
        entityGrammarInfo.name = name;
        entityGrammarInfo.type = EntityType.Object;
        generateGrammar(entityGrammarInfo, clazz);
        return entityGrammarInfo;
    }

    @Override
    public EntityGrammar generateGrammar(String name, List<Map> map, FieldName field) {
        Map<String, Object> root = new HashMap<>();
        root.put(field.getNameFieldName(), name);
        root.put(field.getTypeFieldName(), EntityType.Object.name());
        GenEntityGrammar entityGrammar = this.generateFieldGrammar(null, root, field);
        this.generateGrammar(entityGrammar, map, field);
        return entityGrammar;
    }

    private void generateGrammar(GenEntityGrammar rootClass, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (this.getClass() == field.getType()) {
                continue;
            }
            GenEntityGrammar entityGrammarInfo = new GenEntityGrammar();
            entityGrammarInfo.name = field.getName();
            entityGrammarInfo.type = EntityType.of(field.getType());
            entityGrammarInfo.parentName = rootClass.name;
            entityGrammarInfo.parentType = rootClass.type;
            if (clazz != field.getType() && !ClassUtil.isBaseType(field.getType())) {
                if (Collection.class.isAssignableFrom(field.getType())) {
                    entityGrammarInfo.grammarType = Constant.EXPR_LIST;
                    Class generic = ClassUtil.getGeneric(field.getGenericType());
                    generateGrammar(entityGrammarInfo, generic);
                } else {
                    entityGrammarInfo.grammarType = Constant.EXPR_VAR;
                    generateGrammar(entityGrammarInfo, field.getType());
                }
            } else {
                entityGrammarInfo.grammarType = Constant.EXPR_VAR;
            }
            rootClass.childs.add(entityGrammarInfo);
        }
    }

    private void generateGrammar(GenEntityGrammar parent, Collection<Map> data, FieldName field) {
        for (Map map : data) {
            GenEntityGrammar entityGrammar = this.generateFieldGrammar(parent, map, field);
            if (EntityType.Array == entityGrammar.type || EntityType.Entity == entityGrammar.type) {
                Object obj = map.get(field.getChildFieldName());
                if (obj == null) {
                    throw new TemplateException("缺少字段子集的映射名称");
                }
                if (obj instanceof Collection) {
                    List<Map> maps = (List<Map>) obj;
                    generateGrammar(entityGrammar, maps, field);
                } else {
                    throw new TemplateException("错误的集合：" + entityGrammar.name);
                }
            }
            parent.childs.add(entityGrammar);
        }
    }

    private GenEntityGrammar generateFieldGrammar(GenEntityGrammar parent, Map map, FieldName field) {
        Object fieldName = map.get(field.getNameFieldName());
        if (fieldName == null) {
            throw new TemplateException("缺少名称的对应字段名称");
        }
        Object fieldType = map.get(field.getTypeFieldName());
        Object fieldDescribe = map.get(field.getDescribeFieldName());

        GenEntityGrammar entityGrammar = new GenEntityGrammar();
        entityGrammar.name = fieldName.toString();
        if (parent != null) {
            entityGrammar.parentName = parent.name;
            entityGrammar.parentType = parent.type;
        }
        if (fieldType != null) {
            entityGrammar.type = EntityType.of(fieldType.toString());
        } else {
            entityGrammar.type = EntityType.of(field.getTypeFieldName());
        }
        if (fieldDescribe != null) {
            entityGrammar.describe = fieldDescribe.toString();
        } else {
            entityGrammar.describe = fieldName.toString();
        }
        if (grammarGenListener != null) {
            grammarGenListener.onCreate(map, field, entityGrammar);
        }
        if (EntityType.Array == entityGrammar.type) {
            entityGrammar.grammarType = Constant.EXPR_LIST;
        } else if (EntityType.Entity == entityGrammar.type) {
            entityGrammar.grammarType = Constant.EXPR_VAR;
        } else {
            entityGrammar.grammarType = Constant.EXPR_VAR;
        }
        ExpressionHandle expressionHandle = joinFactory.getExpressionHandle(entityGrammar.grammarType);
        if (expressionHandle != null && expressionHandle.getGrammarExpl() != null) {
            entityGrammar.grammar = expressionHandle.getGrammarExpl().genGrammar(entityGrammar, map, field);
        }
        return entityGrammar;
    }


}
