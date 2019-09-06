package com.join.template.core.grammar.generate;

import com.join.template.core.Template;
import com.join.template.core.constant.Constant;
import com.join.template.core.constant.EntityType;
import com.join.template.core.constant.MarkedWords;
import com.join.template.core.context.HashContext;
import com.join.template.core.expression.ExprHandle;
import com.join.template.core.factory.JoinFactory;
import com.join.template.core.factory.template.TemplateFactory;
import com.join.template.core.grammar.GrammarGenerate;
import com.join.template.core.grammar.GrammarInfo;
import com.join.template.core.type.TypeInfo;
import com.join.template.core.util.ClassUtil;
import com.join.template.core.util.RandomUtil;
import com.join.template.core.util.Utils;
import com.join.template.core.verify.TemplateException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


public class EntityGenerate extends AbstractGrammarGenerate<GrammarInfo> implements GrammarGenerate<GrammarInfo> {


    public EntityGenerate(JoinFactory joinFactory) {
        super(joinFactory);
        super.setGrammarInfo(EntityGrammarInfo.class);
    }

    @Override
    public EntityGenerate generateGrammarRoot(String name, Class clazz) {
        try {
            GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
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
            root.put(this.grammarField.getNameField(), name);
            root.put(this.grammarField.getTypeField(), EntityType.Object.name());
            root.put(this.grammarField.getChildField(), map);
            GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
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
                if (this.getClass() == field.getType()
                        || Modifier.isStatic(field.getModifiers())
                        || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
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
                GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
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

    @Override
    public String preview(String text, int previewSize) {
        Map<String, Object> map = new HashMap<>();
        List<GrammarInfo> grammarInfos = this.getGrammarInfos();
        for (int i = 0; i < grammarInfos.size(); i++) {
            GrammarInfo grammarInfo = grammarInfos.get(i);
            genContent(grammarInfo, map, previewSize);
        }
        TemplateFactory templateFactory = joinFactory.getTemplateFactorys(Constant.TYPE_SINGLE);
        String name = "preview." + this.templateType.name().toLowerCase();
        Template template = (Template) templateFactory.putTemplate(name, text);
        template.putContext(new HashContext(map));
        return template.process();
    }


    /**
     * 创建字段对应语法信息
     *
     * @param current
     * @param clazzOrField
     * @return com.join.template.core.grammar.generate.GrammarInfo
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 16:16
     */
    private GrammarInfo createGrammarInfo(GrammarInfo current, Object clazzOrField) throws IllegalAccessException, InstantiationException {
        TypeInfo typeInfo = TypeInfo.get(clazzOrField);
        current.name(typeInfo.getName());
        current.type(EntityType.of(typeInfo.getType()));
        if (this.grammarGenListener != null) {
            this.grammarGenListener.onCreate(typeInfo, current);
        }
        if (EntityType.Array == current.getType()) {
            current.grammarType(Constant.EXPR_LIST);
        } else {
            current.grammarType(Constant.EXPR_VAR);
        }
        ExprHandle expressionHandle = joinFactory.getExprHandle(current.getGrammarType());
        if (expressionHandle != null && expressionHandle.getExplain() != null && EntityType.Entity != current.getType()) {
            String grammar = expressionHandle.getExplain().genGrammar(this.templateType, current, typeInfo, this.grammarField);
            current.grammar(grammar);
        }
        this.createGrammarChild(current, typeInfo);
        return current;
    }

    /**
     * 创建子集字段对应语法信息
     *
     * @param current
     * @param typeInfo
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 16:17
     */
    private void createGrammarChild(GrammarInfo current, TypeInfo typeInfo) throws InstantiationException, IllegalAccessException {
        if (EntityType.Array == current.getType() || EntityType.Entity == current.getType()) {
            Class clazz_ = null;
            if (Collection.class.isAssignableFrom(typeInfo.getType())) {
                clazz_ = ClassUtil.getClassGenricType(typeInfo.getGenericType());
            } else {
                clazz_ = typeInfo.getType();
            }
            if (EntityType.Array == current.getType())
                createGrammarIndex(current);
            Field[] fields = clazz_.getDeclaredFields();
            for (Field item : fields) {
                if (this.getClass() == item.getType()
                        || typeInfo.getType() == item.getType()
                        || Modifier.isStatic(item.getModifiers())
                        || Modifier.isFinal(item.getModifiers())) {
                    continue;
                }
                GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
                grammarInfo.parentName(current.getName());
                grammarInfo.parentType(current.getType());
                this.createGrammarInfo(grammarInfo, item);
                current.addChild(grammarInfo);
            }
        }
    }

    /**
     * 创建字段对应语法信息
     *
     * @param current
     * @param map
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 16:18
     */
    private void createGrammarInfo(GrammarInfo current, Map map) throws IllegalAccessException, InstantiationException {
        String fieldName = String.valueOf(map.get(this.grammarField.getNameField()));
        if (fieldName == null) {
            throw new TemplateException("缺少名称的对应字段名称");
        }

        String fieldType = String.valueOf(map.get(this.grammarField.getTypeField()));
        String fieldDescribe = String.valueOf(map.get(this.grammarField.getDescribeField()));

        fieldType = Utils.returnOrDefault(fieldType, this.grammarField.getTypeField());
        fieldDescribe = Utils.returnOrDefault(fieldDescribe, fieldName);

        current.name(fieldName);
        current.type(EntityType.of(fieldType));
        current.describe(fieldDescribe);

        if (this.grammarGenListener != null) {
            this.grammarGenListener.onCreate(map, current);
        }
        if (EntityType.Array == current.getType()) {
            current.grammarType(Constant.EXPR_LIST);
        } else {
            current.grammarType(Constant.EXPR_VAR);
        }
        ExprHandle expressionHandle = joinFactory.getExprHandle(current.getGrammarType());
        if (expressionHandle != null && expressionHandle.getExplain() != null) {
            String grammar = expressionHandle.getExplain().genGrammar(this.templateType, current, map, this.grammarField);
            current.grammar(grammar);
        }
        this.createGrammarChild(current, map);
    }

    /**
     * 创建子集字段对应语法信息
     *
     * @param current
     * @param map
     * @return void
     * @author CAOYOU/625954232@qq.com
     * @date 2019/8/21 16:18
     */
    private void createGrammarChild(GrammarInfo current, Map map) throws InstantiationException, IllegalAccessException {
        if (EntityType.Array == current.getType() || EntityType.Entity == current.getType()) {
            Object obj = map.get(this.grammarField.getChildField());
            if (obj == null) {
                return;
            }
            if (obj instanceof Collection) {
                if (EntityType.Array == current.getType())
                    createGrammarIndex(current);
                List<Map> maps = (List<Map>) obj;
                for (Map item : maps) {
                    GrammarInfo grammarInfo = this.grammarInfoClass.newInstance();
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


    private void genContent(GrammarInfo grammarInfo, Map<String, Object> map, int previewSize) {
        Object value = null;
        List<GrammarInfo> childs = grammarInfo.getChilds();
        if (EntityType.Integer == grammarInfo.getType()) {
            value = RandomUtil.toInt(0, 999999999);
        } else if (EntityType.Byte == grammarInfo.getType()) {
            value = RandomUtil.toByte();
        } else if (EntityType.Long == grammarInfo.getType()) {
            value = RandomUtil.toLong();
        } else if (EntityType.Float == grammarInfo.getType()) {
            value = RandomUtil.toFloat();
        } else if (EntityType.BigDecimal == grammarInfo.getType()) {
            value = RandomUtil.toBigDecimal();
        } else if (EntityType.BigInteger == grammarInfo.getType()) {
            value = RandomUtil.toBigInteger();
        } else if (EntityType.Short == grammarInfo.getType()) {
            value = RandomUtil.toShort(3);
        } else if (EntityType.Character == grammarInfo.getType()) {
            value = RandomUtil.toCharacter();
        } else if (EntityType.Time == grammarInfo.getType()) {
            value = RandomUtil.toDouble();
        } else if (EntityType.Double == grammarInfo.getType()) {
            value = RandomUtil.toDouble();
        } else if (EntityType.Boolean == grammarInfo.getType()) {
            value = RandomUtil.toBoolean();
        } else if (EntityType.Object == grammarInfo.getType()) {
            value = RandomUtil.toString(6);
        } else if (EntityType.String == grammarInfo.getType()) {
            value = RandomUtil.toString(6);
        } else if ((EntityType.Array == grammarInfo.getType() || EntityType.Entity == grammarInfo.getType()) && childs != null) {
            Map<String, Object> childMap = new HashMap<>();
            for (int i = 0; i < childs.size(); i++) {
                GrammarInfo child = childs.get(i);
                genContent(child, childMap, previewSize);
            }
            if (EntityType.Array == grammarInfo.getType()) {
                List<Map<String, Object>> list = new ArrayList<>();
                for (int i = 0; i < previewSize; i++) {
                    list.add(childMap);
                }
                value = list;
            } else {
                value = childMap;
            }
        }
        if (value != null) {
            map.put(grammarInfo.getName(), value);
            if (this.grammarGenListener != null) {
                this.grammarGenListener.onPreview(grammarInfo, value, map);
            }

        }
    }


    private void createGrammarIndex(GrammarInfo current) throws IllegalAccessException, InstantiationException {
        GrammarInfo index = this.grammarInfoClass.newInstance();
//        index.parentName(current.getName());
//        index.parentType(current.getType());
        index.name(current.getName().concat("_").concat(configuration.getAttIndex()));
        index.type(EntityType.Integer);
        index.describe(MarkedWords.Attr_Index_Name);
        index.grammarType(Constant.EXPR_VAR);

        ExprHandle expressionHandle = joinFactory.getExprHandle(index.getGrammarType());
        if (expressionHandle != null && expressionHandle.getExplain() != null) {
            Map<String, Object> root = new HashMap<>();
            root.put(this.grammarField.getNameField(), index.getName());
            root.put(this.grammarField.getTypeField(), EntityType.Integer.name());
            String grammar = expressionHandle.getExplain().genGrammar(this.templateType, index, root, this.grammarField);
            index.grammar(grammar);
        }
        current.addChild(index);
    }

}
