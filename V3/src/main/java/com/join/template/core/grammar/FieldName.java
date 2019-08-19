package com.join.template.core.grammar;

import com.join.template.core.constant.EntityType;
import lombok.Data;

/**
 * @author CAOYOU
 * @Title: EntityGrammarTag
 * @date 2019/8/1916:45
 */
@Data
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
}
