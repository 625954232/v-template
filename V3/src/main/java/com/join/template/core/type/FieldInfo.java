package com.join.template.core.type;

import sun.reflect.CallerSensitive;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.security.ProtectionDomain;

/**
 * @author CAOYOU
 * @Title: ClassInfo
 * @date 2019/8/2115:04
 */
public class FieldInfo implements TypeInfo {
    private Field field;

    public FieldInfo(Field field) {
        this.field = field;
    }


    public Class<?> getDeclaringClass() {
        return field.getDeclaringClass();
    }

    public int getModifiers() {
        return field.getModifiers();
    }

    public boolean isEnumConstant() {
        return field.isEnumConstant();
    }

    public boolean isSynthetic() {
        return field.isSynthetic();
    }

    public Type getGenericType() {
        return field.getGenericType();
    }

    public String toGenericString() {
        return field.toGenericString();
    }

    @CallerSensitive
    public Object get(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.get(obj);
    }

    @CallerSensitive
    public boolean getBoolean(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getBoolean(obj);
    }

    @CallerSensitive
    public byte getByte(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getByte(obj);
    }

    @CallerSensitive
    public char getChar(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getChar(obj);
    }

    @CallerSensitive
    public short getShort(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getShort(obj);
    }

    @CallerSensitive
    public int getInt(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getInt(obj);
    }

    @CallerSensitive
    public long getLong(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getLong(obj);
    }

    @CallerSensitive
    public float getFloat(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getFloat(obj);
    }

    @CallerSensitive
    public double getDouble(Object obj) throws IllegalArgumentException, IllegalAccessException {
        return field.getDouble(obj);
    }

    @CallerSensitive
    public void set(Object obj, Object value) throws IllegalArgumentException, IllegalAccessException {
        field.set(obj, value);
    }

    @CallerSensitive
    public void setBoolean(Object obj, boolean z) throws IllegalArgumentException, IllegalAccessException {
        field.setBoolean(obj, z);
    }

    @CallerSensitive
    public void setByte(Object obj, byte b) throws IllegalArgumentException, IllegalAccessException {
        field.setByte(obj, b);
    }

    @CallerSensitive
    public void setChar(Object obj, char c) throws IllegalArgumentException, IllegalAccessException {
        field.setChar(obj, c);
    }

    @CallerSensitive
    public void setShort(Object obj, short s) throws IllegalArgumentException, IllegalAccessException {
        field.setShort(obj, s);
    }

    @CallerSensitive
    public void setInt(Object obj, int i) throws IllegalArgumentException, IllegalAccessException {
        field.setInt(obj, i);
    }

    @CallerSensitive
    public void setLong(Object obj, long l) throws IllegalArgumentException, IllegalAccessException {
        field.setLong(obj, l);
    }

    @CallerSensitive
    public void setFloat(Object obj, float f) throws IllegalArgumentException, IllegalAccessException {
        field.setFloat(obj, f);
    }

    @CallerSensitive
    public void setDouble(Object obj, double d) throws IllegalArgumentException, IllegalAccessException {
        field.setDouble(obj, d);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    public Annotation[] getDeclaredAnnotations() {
        return field.getDeclaredAnnotations();
    }

    public AnnotatedType getAnnotatedType() {
        return field.getAnnotatedType();
    }


    public void setAccessible(boolean flag) throws SecurityException {
        field.setAccessible(flag);
    }

    public boolean isAccessible() {
        return field.isAccessible();
    }

    public Annotation[] getAnnotations() {
        return field.getAnnotations();
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }

    @Override
    public Class getSuperclass() {
        return field.getDeclaringClass();
    }

    @Override
    public Type getGenericSuperclass() {
        return field.getDeclaringClass().getGenericSuperclass();
    }

    @Override
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return field.getAnnotationsByType(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return field.isAnnotationPresent(annotationClass);
    }

    @Override
    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        return null;
    }

    @Override
    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return null;
    }
}
