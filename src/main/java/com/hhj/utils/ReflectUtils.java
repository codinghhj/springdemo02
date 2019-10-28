package com.hhj.utils;

import com.hhj.config.BeanDefination;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class ReflectUtils {
    public static Class<?> getFiledType(String className, String name) {
        try {
            Class<?> aClass = Class.forName(className);
            Field field = aClass.getDeclaredField(name);
            return field.getType();
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createInstance(String className, Object... args) {
        try {
            Class<?> aClass = Class.forName(className);
            Constructor<?> constructor = aClass.getConstructor();
            return constructor.newInstance(args);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object instance, String name, Object value) {

        try {
            Class<?> aClass = instance.getClass();
            Field field = aClass.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void invokeMethod(String initMethod, Object instance) {

        try {
            if (initMethod == null || "".equals(initMethod)) return;
            Class<?> aClass = instance.getClass();
            Method method = aClass.getDeclaredMethod(initMethod);
            method.setAccessible(true);
            method.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
