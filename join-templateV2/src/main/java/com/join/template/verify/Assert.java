package com.join.template.verify;

import org.apache.commons.lang.StringUtils;


/**
 * 数据校验
 */
public abstract class Assert {
    /**
     * 空字符效验
     *
     * @param str
     * @param message
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new TemplateException(message);
        }
    }

    public static void isBlank(String str, String message, String... args) {
        if (StringUtils.isBlank(str)) {
            throw new TemplateException(message, args);
        }
    }

    /**
     * 空效验
     *
     * @param object
     * @param message
     */
    public static void isNull(Object object, String message) {
        if (object == null || object.toString().length() <= 0) {
            throw new TemplateException(message);
        }
    }

    public static void isNull(Object object, String message, String... args) {
        if (object == null || object.toString().length() <= 0) {
            throw new TemplateException(message, args);
        }
    }

    /**
     * 条件成立
     *
     * @param judge
     * @param message
     */
    public static void ifTrue(boolean judge, String message) {
        if (judge) {
            throw new TemplateException(message);
        }
    }

    public static void ifTrue(boolean judge, String message, String... args) {
        if (judge) {
            throw new TemplateException(message, args);
        }
    }

    /**
     * 条件不成立
     *
     * @param judge
     * @param message
     */
    public static void ifFalse(boolean judge, String message) {
        if (!judge) {
            throw new TemplateException(message);
        }
    }

    public static void ifFalse(boolean judge, String message, String... args) {
        if (!judge) {
            throw new TemplateException(message, args);
        }
    }


}
