package com.join.template.core.constant;

import java.util.Collection;

/**
 * @author CAOYOU
 * @Title: 实体类字段类型
 * @date 2019/8/1916:43
 */
public enum EntityType {
    Integer, Byte, Long, Double, Float, Character, Short, Boolean, Array, String, Object, Entity, BigDecimal, BigInteger, Map, Time;


    public static EntityType of(String name) {
        if (name.toLowerCase().equals("integer")) {
            return EntityType.Integer;
        } else if (name.toLowerCase().equals("byte")) {
            return EntityType.Byte;
        } else if (name.toLowerCase().equals("long")) {
            return EntityType.Long;
        } else if (name.toLowerCase().equals("double")) {
            return EntityType.Double;
        } else if (name.toLowerCase().equals("float")) {
            return EntityType.Float;
        } else if (name.toLowerCase().equals("character")) {
            return EntityType.Character;
        } else if (name.toLowerCase().equals("short")) {
            return EntityType.Short;
        } else if (name.toLowerCase().equals("boolean")) {
            return EntityType.Boolean;
        } else if (name.toLowerCase().equals("object")) {
            return EntityType.Object;
        } else if (name.toLowerCase().equals("entity")) {
            return EntityType.Entity;
        } else if (name.toLowerCase().equals("bigdecimal")) {
            return EntityType.BigDecimal;
        } else if (name.toLowerCase().equals("biginteger")) {
            return EntityType.BigInteger;
        } else if (name.toLowerCase().equals("array")) {
            return EntityType.Array;
        } else if (name.toLowerCase().equals("map")) {
            return EntityType.Map;
        } else if (name.toLowerCase().equals("time")) {
            return EntityType.Time;
        } else {
            return EntityType.String;
        }

    }

    public static EntityType of(Class clazz) {
        if (clazz.equals(java.util.Map.class)) {
            return EntityType.Map;
        } else if (Collection.class.isAssignableFrom(clazz) || clazz.isArray()) {
            return EntityType.Array;
        } else if (clazz.getName().startsWith("java") || clazz.getName().startsWith("javax")) {
            if (clazz.equals(java.lang.Integer.class)) {
                return EntityType.Integer;
            } else if (clazz.equals(java.lang.Byte.class)) {
                return EntityType.Byte;
            } else if (clazz.equals(java.lang.Long.class)) {
                return EntityType.Long;
            } else if (clazz.equals(java.lang.Double.class)) {
                return EntityType.Double;
            } else if (clazz.equals(java.lang.Float.class)) {
                return EntityType.Float;
            } else if (clazz.equals(java.lang.Character.class)) {
                return EntityType.Character;
            } else if (clazz.equals(java.lang.Short.class)) {
                return EntityType.Short;
            } else if (clazz.equals(java.lang.Boolean.class)) {
                return EntityType.Boolean;
            } else if (clazz.equals(java.lang.Object.class)) {
                return EntityType.Object;
            } else if (clazz.equals(java.lang.String.class)) {
                return EntityType.String;
            } else if (clazz.equals(java.math.BigDecimal.class)) {
                return EntityType.BigDecimal;
            } else if (clazz.equals(java.math.BigInteger.class)) {
                return EntityType.BigInteger;
            } else if (clazz.equals(java.util.Date.class)
                    || clazz.equals(java.sql.Date.class)
                    || clazz.equals(java.time.LocalTime.class)
                    || clazz.equals(java.time.LocalDate.class)
                    || clazz.equals(java.time.LocalDateTime.class)) {
                return EntityType.Time;
            } else {
                return EntityType.String;
            }
        } else {
            return EntityType.Entity;
        }
    }
}
