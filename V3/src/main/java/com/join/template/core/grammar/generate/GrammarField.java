package com.join.template.core.grammar.generate;

/**
 * @author CAOYOU
 * @Title: 语法字段对应信息
 * @date 2019/8/1916:45
 */
public class GrammarField {

    private String nameFieldName;

    private String describeFieldName;

    private String typeFieldName;

    private String childFieldName;

    public GrammarField() {
    }

    public GrammarField(String nameFieldName, String describeFieldName, String typeFieldName) {
        this.nameFieldName = nameFieldName;
        this.describeFieldName = describeFieldName;
        this.typeFieldName = typeFieldName;
    }

    public GrammarField setNameFieldName(String nameFieldName) {
        this.nameFieldName = nameFieldName;
        return this;
    }

    public GrammarField setDescribeFieldName(String describeFieldName) {
        this.describeFieldName = describeFieldName;
        return this;
    }

    public GrammarField setTypeFieldName(String typeFieldName) {
        this.typeFieldName = typeFieldName;
        return this;
    }

    public GrammarField setChildFieldName(String childFieldName) {
        this.childFieldName = childFieldName;
        return this;
    }

    public String getNameFieldName() {
        return nameFieldName;
    }


    public String getDescribeFieldName() {
        return describeFieldName;
    }


    public String getTypeFieldName() {
        return typeFieldName;
    }


    public String getChildFieldName() {
        return childFieldName;
    }


}
