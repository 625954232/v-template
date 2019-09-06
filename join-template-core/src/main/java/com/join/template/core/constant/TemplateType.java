package com.join.template.core.constant;

/**
 * @author CAOYOU
 * @Title: TemplateType
 * @date 2019/8/2714:18
 */
public enum TemplateType {
    Text, Html;


    public static TemplateType of(String fileName) {
        TemplateType templateType = TemplateType.Text;
        if (fileName.endsWith(".html")) {
            templateType = TemplateType.Html;
        }
        return templateType;
    }
}
