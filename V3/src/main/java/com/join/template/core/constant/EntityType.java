package com.join.template.core.constant;

import com.sun.org.apache.regexp.internal.RE;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author CAOYOU
 * @Title: 实体类字段类型
 * @date 2019/8/1916:43
 */
public enum EntityType {
    Integer, Byte, Long, Double, Float, Character, Short, Boolean, Array, String, Object, Entity, BigDecimal, BigInteger, Map;


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
        } else if (name.toLowerCase().equals("string")) {
            return EntityType.String;
        } else if (name.toLowerCase().equals("bigdecimal")) {
            return EntityType.BigDecimal;
        } else if (name.toLowerCase().equals("biginteger")) {
            return EntityType.BigInteger;
        } else if (name.toLowerCase().equals("array")) {
            return EntityType.Array;
        } else if (name.toLowerCase().equals("map")) {
            return EntityType.Map;
        } else {
            return EntityType.Entity;
        }

    }

    public static EntityType of(Class clazz) {
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
        } else if (clazz.equals(java.util.Map.class)) {
            return EntityType.Map;
        } else if (Collection.class.isAssignableFrom(clazz) || clazz.isArray()) {
            return EntityType.Array;
        } else {
            return EntityType.Entity;
        }

    }
}
