package com.join.template.core.grammar.generate;

/**
 * @author CAOYOU
 * @Title: 语法字段对应信息
 * @date 2019/8/1916:45
 */
public class GrammarField {
    /**
     * 字段名称对应的别名
     */
    private String nameField;
    /**
     * 字段描述对应的别名
     */
    private String describeField;
    /**
     * 字段类型对应的别名
     */
    private String typeField;
    /**
     * 子集字段列表对应的别名
     */
    private String childField;

    public GrammarField() {
    }

    public GrammarField(String nameFieldName, String describeFieldName, String typeFieldName) {
        this.nameField = nameFieldName;
        this.describeField = describeFieldName;
        this.typeField = typeFieldName;
    }

    public GrammarField setNameField(String nameField) {
        this.nameField = nameField;
        return this;
    }

    public GrammarField setDescribeField(String describeField) {
        this.describeField = describeField;
        return this;
    }

    public GrammarField setTypeField(String typeField) {
        this.typeField = typeField;
        return this;
    }

    public GrammarField setChildField(String childField) {
        this.childField = childField;
        return this;
    }

    public String getNameField() {
        return nameField;
    }

    public String getDescribeField() {
        return describeField;
    }

    public String getTypeField() {
        return typeField;
    }

    public String getChildField() {
        return childField;
    }


}
