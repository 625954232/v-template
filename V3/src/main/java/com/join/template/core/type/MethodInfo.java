package com.join.template.core.type;

import sun.reflect.CallerSensitive;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author CAOYOU
 * @Title: TypeInfo
 * @date 2019/8/2115:04
 */
public class MethodInfo implements TypeInfo {
    Method method;

    public MethodInfo(Method method) {
        this.method = method;
    }

    public Class<?> getDeclaringClass() {
        return method.getDeclaringClass();
    }

    @Override
    public String getName() {
        return method.getName();
    }

    @Override
    public Class<?> getType() {
        return method.getClass();
    }

    @Override
    public Class getSuperclass() {
        return method.getDeclaringClass();
    }

    @Override
    public Type getGenericSuperclass() {
        return method.getDeclaringClass().getGenericSuperclass();
    }

    @Override
    public int getModifiers() {
        return method.getModifiers();
    }

    public TypeVariable<Method>[] getTypeParameters() {
        return method.getTypeParameters();
    }

    public Class<?> getReturnType() {
        return method.getReturnType();
    }

    public Type getGenericReturnType() {
        return method.getGenericReturnType();
    }

    public Class<?>[] getParameterTypes() {
        return method.getParameterTypes();
    }

    public int getParameterCount() {
        return method.getParameterCount();
    }

    public Type[] getGenericParameterTypes() {
        return method.getGenericParameterTypes();
    }

    public Class<?>[] getExceptionTypes() {
        return method.getExceptionTypes();
    }

    public Type[] getGenericExceptionTypes() {
        return method.getGenericExceptionTypes();
    }

    public String toGenericString() {
        return method.toGenericString();
    }


    public boolean isBridge() {
        return method.isBridge();
    }

    public boolean isVarArgs() {
        return method.isVarArgs();
    }

    public boolean isSynthetic() {
        return method.isSynthetic();
    }

    public boolean isDefault() {
        return method.isDefault();
    }

    public Object getDefaultValue() {
        return method.getDefaultValue();
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return (T) method.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return method.getDeclaredAnnotations();
    }

    public Annotation[][] getParameterAnnotations() {
        return method.getParameterAnnotations();
    }

    public AnnotatedType getAnnotatedReturnType() {
        return method.getAnnotatedReturnType();
    }

    public Parameter[] getParameters() {
        return method.getParameters();
    }

    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return method.getAnnotationsByType(annotationClass);
    }

    public AnnotatedType getAnnotatedReceiverType() {
        return method.getAnnotatedReceiverType();
    }

    public AnnotatedType[] getAnnotatedParameterTypes() {
        return method.getAnnotatedParameterTypes();
    }

    public AnnotatedType[] getAnnotatedExceptionTypes() {
        return method.getAnnotatedExceptionTypes();
    }

    public static void setAccessible(AccessibleObject[] array, boolean flag) throws SecurityException {
        AccessibleObject.setAccessible(array, flag);
    }

    public void setAccessible(boolean flag) throws SecurityException {
        method.setAccessible(flag);
    }

    public boolean isAccessible() {
        return method.isAccessible();
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        return method.getDeclaredAnnotation(annotationClass);
    }

    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return method.getDeclaredAnnotationsByType(annotationClass);
    }

    @Override
    public Type getGenericType() {
        return method.getGenericReturnType();
    }


}
