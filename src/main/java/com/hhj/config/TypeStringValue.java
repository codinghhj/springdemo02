package com.hhj.config;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class TypeStringValue {

    private String value;
    private Class<?> targetClass;

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeStringValue(Class<?> targetClass, String value) {
        this.targetClass = targetClass;
        this.value = value;
    }
}
