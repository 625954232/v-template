package com.join.template.core.util.type;

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
public class ClassInfo implements TypeInfo {
    private Class aClass;

    public ClassInfo(Class aClass) {
        this.aClass = aClass;
    }


    public boolean isInstance(Object obj) {
        return aClass.isInstance(obj);
    }

    public boolean isAssignableFrom(Class cls) {
        return aClass.isAssignableFrom(cls);
    }

    public boolean isInterface() {
        return aClass.isInterface();
    }

    public boolean isArray() {
        return aClass.isArray();
    }

    public boolean isPrimitive() {
        return aClass.isPrimitive();
    }

    public boolean isAnnotation() {
        return aClass.isAnnotation();
    }


    public TypeVariable<Class>[] getTypeParameters() {
        return aClass.getTypeParameters();
    }

    @Override
    public Class getSuperclass() {
        return aClass.getSuperclass();
    }

    @Override
    public Type getGenericSuperclass() {
        return aClass.getGenericSuperclass();
    }

    public Package getPackage() {
        return aClass.getPackage();
    }

    public Class<?>[] getInterfaces() {
        return aClass.getInterfaces();
    }

    public Type[] getGenericInterfaces() {
        return aClass.getGenericInterfaces();
    }

    public Class<?> getComponentType() {
        return aClass.getComponentType();
    }

    public int getModifiers() {
        return aClass.getModifiers();
    }

    public Object[] getSigners() {
        return aClass.getSigners();
    }

    public String getSimpleName() {
        return aClass.getSimpleName();
    }

    public String getTypeName() {
        return aClass.getTypeName();
    }

    public String getCanonicalName() {
        return aClass.getCanonicalName();
    }

    public boolean isAnonymousClass() {
        return aClass.isAnonymousClass();
    }

    public boolean isLocalClass() {
        return aClass.isLocalClass();
    }

    public boolean isMemberClass() {
        return aClass.isMemberClass();
    }


    public InputStream getResourceAsStream(String name) {
        return aClass.getResourceAsStream(name);
    }

    public URL getResource(String name) {
        return aClass.getResource(name);
    }

    public ProtectionDomain getProtectionDomain() {
        return aClass.getProtectionDomain();
    }

    public boolean desiredAssertionStatus() {
        return aClass.desiredAssertionStatus();
    }

    public boolean isEnum() {
        return aClass.isEnum();
    }

    public Object[] getEnumConstants() {
        return aClass.getEnumConstants();
    }

    public Object cast(Object obj) {
        return aClass.cast(obj);
    }

    public Class asSubclass(Class clazz) {
        return aClass.asSubclass(clazz);
    }


    public Annotation[] getAnnotations() {
        return aClass.getAnnotations();
    }


    public Annotation[] getDeclaredAnnotations() {
        return aClass.getDeclaredAnnotations();
    }

    public AnnotatedType getAnnotatedSuperclass() {
        return aClass.getAnnotatedSuperclass();
    }

    public AnnotatedType[] getAnnotatedInterfaces() {
        return aClass.getAnnotatedInterfaces();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return aClass.isAnnotationPresent(annotationClass);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return (T) aClass.getAnnotation(annotationClass);
    }

    @Override
    public String getName() {
        return aClass.getName();
    }

    @Override
    public Class<?> getType() {
        return aClass;
    }

    @Override
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return (T[]) aClass.getAnnotationsByType(annotationClass);
    }


    @Override
    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        return (T) aClass.getDeclaredAnnotation(annotationClass);
    }

    @Override
    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return (T[]) aClass.getDeclaredAnnotationsByType(annotationClass);
    }

    @Override
    public Type getGenericType() {
        return aClass.getGenericInterfaces()[0];
    }


}
