package com.join.template.core.grammar;

import com.join.template.core.constant.Constant;
import com.join.template.core.util.ClassUtil;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class EntityGrammarInfo implements EntityGrammar {
    private String fieldName;
    private String fieldDescribe;
    private Class fieldType;
    private String fielGgrammar;
    private Integer ggrammarType;
    private String parentName;
    private Class parentType;
    private List<EntityGrammarInfo> childs = new ArrayList<>();


    public EntityGrammarInfo(String name, Class clazz) {
        this.fieldName = name;
        this.fieldType = clazz;

    }

    private void generateGrammar(Class clazz, EntityGrammarInfo rootClass) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (this.getClass() == field.getType()) {
                continue;
            }
            EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo(null, null);
            entityGrammarInfo.fieldName = field.getName();
            entityGrammarInfo.fieldType = field.getType();
            entityGrammarInfo.parentName = rootClass.fieldName;
            entityGrammarInfo.parentType = rootClass.fieldType;
            if (clazz != field.getType() && !ClassUtil.isBaseType(field.getType())) {
                if (Collection.class.isAssignableFrom(field.getType())) {
                    entityGrammarInfo.ggrammarType = Constant.EXPR_LIST;
                    Class generic = ClassUtil.getGeneric(field.getGenericType());
                    generateGrammar(generic, entityGrammarInfo);
                } else {
                    entityGrammarInfo.ggrammarType = Constant.EXPR_VAR;
                    generateGrammar(field.getType(), entityGrammarInfo);
                }
            } else {
                entityGrammarInfo.ggrammarType = Constant.EXPR_VAR;
            }
            rootClass.childs.add(entityGrammarInfo);
        }
    }

    @Override
    public EntityGrammarInfo generateGrammar(String name, Class clazz) {
        EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo(name, clazz);
        return entityGrammarInfo;
    }

}
