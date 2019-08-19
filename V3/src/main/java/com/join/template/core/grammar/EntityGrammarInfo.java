package com.join.template.core.grammar;

import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.listener.GrammarGenListener;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.verify.TemplateException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class EntityGrammarInfo implements EntityGrammar {
    private String name;
    private String describe;
    private EntityType type;
    private String grammar;
    private Integer grammarType;
    private String parentName;
    private EntityType parentType;
    private List<EntityGrammar> childs = new ArrayList<>();

    private GrammarGenListener grammarGenListener;

    @Override
    public EntityGrammar setGrammarGenListener(GrammarGenListener grammarGenListener) {
        this.grammarGenListener = grammarGenListener;
        return this;
    }

    @Override
    public EntityGrammar generateGrammar(String name, Class clazz) {
        EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo();
        entityGrammarInfo.name = name;
        entityGrammarInfo.type = EntityType.Object;
        generateGrammar(entityGrammarInfo, clazz);
        return entityGrammarInfo;
    }

    @Override
    public EntityGrammar generateGrammar(String name, List<Map> map, FieldName fieldName) {
        EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo();
        entityGrammarInfo.name = name;
        entityGrammarInfo.type = EntityType.Object;
        generateGrammar(entityGrammarInfo, map, fieldName);
        return entityGrammarInfo;
    }

    private void generateGrammar(EntityGrammarInfo rootClass, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (this.getClass() == field.getType()) {
                continue;
            }
            EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo();
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

    private void generateGrammar(EntityGrammarInfo rootClass, Collection<Map> data, FieldName field) {
        for (Map map : data) {
            Object fieldName = map.get(field.getNameFieldName());
            if (fieldName == null) {
                throw new TemplateException("缺少名称的对应字段名称");
            }
            Object fieldType = map.get(field.getTypeFieldName());
            Object fieldDescribe = map.get(field.getDescribeFieldName());
            EntityGrammarInfo entityGrammarInfo = new EntityGrammarInfo();
            entityGrammarInfo.name = fieldName.toString();
            if (fieldType != null) {
                entityGrammarInfo.type = EntityType.of(fieldType.toString());
            } else {
                entityGrammarInfo.type = EntityType.of(field.getTypeFieldName());
            }
            if (fieldDescribe != null) {
                entityGrammarInfo.describe = fieldDescribe.toString();
            } else {
                entityGrammarInfo.describe = fieldName.toString();
            }
            entityGrammarInfo.parentName = rootClass.name;
            entityGrammarInfo.parentType = rootClass.type;
            if (EntityType.Array == entityGrammarInfo.type) {
                entityGrammarInfo.grammarType = Constant.EXPR_LIST;
            } else if (EntityType.Entity == entityGrammarInfo.type) {
                entityGrammarInfo.grammarType = Constant.EXPR_VAR;
            } else {
                entityGrammarInfo.grammarType = Constant.EXPR_VAR;
            }
            if (grammarGenListener != null) {
                grammarGenListener.onCreate(map, field, entityGrammarInfo);
            }

            if (EntityType.Array == entityGrammarInfo.type || EntityType.Entity == entityGrammarInfo.type) {
                Object obj = map.get(field.getChildFieldName());
                if (obj == null) {
                    throw new TemplateException("缺少字段子集的映射名称");
                }
                if (obj instanceof Collection) {
                    List<Map> maps = (List<Map>) obj;
                    generateGrammar(entityGrammarInfo, maps, field);
                } else {
                    throw new TemplateException("错误的集合：" + entityGrammarInfo.name);
                }
            }
            rootClass.childs.add(entityGrammarInfo);
        }
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
