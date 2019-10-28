package com.hhj.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class BeanDefination {
    private String id;
    private String name;
    private String className;
    private String initMethod;
    private List<PropertyValue> propertyValues = new ArrayList<>();
    public BeanDefination(String id, String name, String className) {
        this.id = id;
        this.name = name;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }
    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValues.add(propertyValue);
    }
}
