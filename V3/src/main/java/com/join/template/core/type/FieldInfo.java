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
