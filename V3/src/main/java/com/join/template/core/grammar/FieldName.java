package com.join.template.core.grammar;

/**
 * @author CAOYOU
 * @Title: FieldName
 * @date 2019/8/1916:45
 */
public class FieldName {

    private String nameFieldName;

    private String describeFieldName;

    private String typeFieldName;

    private String childFieldName;

    public FieldName() {
    }

    public FieldName(String nameFieldName, String describeFieldName, String typeFieldName) {
        this.nameFieldName = nameFieldName;
        this.describeFieldName = describeFieldName;
        this.typeFieldName = typeFieldName;
    }

    public String getNameFieldName() {
        return nameFieldName;
    }

    public FieldName setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
        return this;
    }

    public String getDescribeFieldName() {
        return describeFieldName;
    }

    public FieldName setDescribeFieldName(String describeFieldName) {
        this.describeFieldName = describeFieldName;
        return this;
    }

    public String getTypeFieldName() {
        return typeFieldName;
    }

    public FieldName setTypeFieldName(String typeFieldName) {
        this.typeFieldName = typeFieldName;
        return this;
    }

    public String getChildFieldName() {
        return childFieldName;
    }

    public FieldName setChildFieldName(String childFieldName) {
        this.childFieldName = childFieldName;
        return this;
    }
}
