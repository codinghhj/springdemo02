package com.hhj.factory;

import com.hhj.config.*;
import com.hhj.converter.IntegerConvert;
import com.hhj.converter.StringConvert;
import com.hhj.converter.TypeConvert;
import com.hhj.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    private Map<String, BeanDefination> definations = new HashMap<>();
    private Map<String, Object> singletons = new HashMap<>();
    private List<Resource> resources = new ArrayList<>();
    private List<TypeConvert> typeConverts = new ArrayList<>();

    public DefaultListableBeanFactory(String location) {
        registerResource();
        registerConvert();
        Resource resource = getResource(location);
        XMLBeanDefinationParser xmlBeanDefinationParser = new XMLBeanDefinationParser();
        xmlBeanDefinationParser.loadDefinations(this, resource);
    }

    private void registerConvert() {
        this.typeConverts.add(new IntegerConvert());
        this.typeConverts.add(new StringConvert());
    }

    private Resource getResource(String location) {
        for (Resource resource : resources) {
            if (resource.isCanRead(location)) {
                return resource;
            }
        }
        return null;

    }

    private void registerResource() {
        resources.add(new ClasspathResource());
    }

    @Override
    public Object getBean(String beanName) {
        Object object = singletons.get(beanName);
        if (object != null) {
            return object;
        }
        BeanDefination beanDefination = definations.get(beanName);
        String className = beanDefination.getClassName();
        Object instance = ReflectUtils.createInstance(className, null);
        setProperties(beanDefination, instance);
        invokeMethod(beanDefination.getInitMethod(), instance);
        return instance;
    }

    private void setProperties(BeanDefination beanDefination, Object instance) {
        List<PropertyValue> propertyValues = beanDefination.getPropertyValues();
        propertyValues.forEach(propertyValue -> setProperty(propertyValue, instance));
    }

    private void invokeMethod(String initMethod, Object instance) {
        ReflectUtils.invokeMethod(initMethod, instance);
    }

    private void setProperty(PropertyValue propertyValue, Object instance) {
        Object value = propertyValue.getValue();
        String name = propertyValue.getName();
        Object convert = null;
        if (value instanceof TypeStringValue) {
            TypeStringValue typeStringValue = (TypeStringValue) value;
            Class<?> targetClass = typeStringValue.getTargetClass();

            for (TypeConvert typeConvert : typeConverts) {

                if (typeConvert.isType(targetClass)) {
                    convert = typeConvert.convert(typeStringValue.getValue());
                }
            }
        } else if (value instanceof RuntimeType) {
            RuntimeType runtimeType = (RuntimeType) value;
            String ref = runtimeType.getRef();
            convert = getBean(ref);
        }
        ReflectUtils.setFieldValue(instance, name, convert);
    }


    @Override
    public Object getBean(Class<?> beanClass) {
        return super.getBean(beanClass);
    }

    public Map<String, BeanDefination> getBeanDefinations() {
        return this.definations;
    }
}
