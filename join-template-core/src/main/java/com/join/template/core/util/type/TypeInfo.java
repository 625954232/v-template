package com.join.template.core.util.type;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author CAOYOU
 * @Title: TypeInfo
 * @date 2019/8/2115:04
 */
public interface TypeInfo {

    static TypeInfo get(Object type) {
        if (type instanceof Field) {
            return new FieldInfo((Field) type);
        } else if (type instanceof Method) {
            return new MethodInfo((Method) type);
        } else if (type instanceof Class) {
            return new ClassInfo((Class) type);
        } else {
            throw new IllegalArgumentException("未知Type");
        }
    }


    String getName();

    Class getType();

    Class getSuperclass();

    Type getGenericSuperclass();

    int getModifiers();

    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    Annotation[] getDeclaredAnnotations();

    <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass);

    boolean isAnnotationPresent(Class<? extends Annotation> annotationClass);

    Annotation[] getAnnotations();

    <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass);

    <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass);

    Type getGenericType();
}
