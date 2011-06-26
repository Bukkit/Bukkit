package org.bukkit.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author t3hk0d3
 */
public class AnnotationHelper {
    public static <T extends Annotation> Map<T, Method> getAnnotatedMethods(Class<T> annotationClass, Class<?> cls) {
        Map<T, Method> annotatedMethod = new HashMap<T, Method>();

        collectMethods(annotationClass, cls, annotatedMethod);

        return annotatedMethod;
    }

    protected static <T extends Annotation> void collectMethods(Class<T> antClass, Class<?> targetClass, Map<T, Method> map) {
        for (Method method : targetClass.getMethods()) {
            putAnnotatedMethod(method, antClass, map);
        }
    }

    protected static <T extends Annotation> void putAnnotatedMethod(Method parentMethod, Class<T> annotationClass, Map<T, Method> map) {
        if (parentMethod.isAnnotationPresent(annotationClass)) {
            map.put(parentMethod.getAnnotation(annotationClass), parentMethod);
            return;
        }

        Class superClass = parentMethod.getDeclaringClass().getSuperclass();
        if (superClass == null) {
            return;
        }

        try {
            Method superMethod = superClass.getMethod(parentMethod.getName(), parentMethod.getParameterTypes());
            putAnnotatedMethod(superMethod, annotationClass, map);
        } catch (NoSuchMethodException e) {
        }
    }
}
