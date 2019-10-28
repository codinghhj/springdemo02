package com.hhj.config;

import com.hhj.factory.DefaultListableBeanFactory;
import com.hhj.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class XMLDocumentParser {
    private DefaultListableBeanFactory beanFactory;

    public XMLDocumentParser() {
    }

    public XMLDocumentParser(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public void loadDefination(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            if (element.getName().equals("bean")) {
                parseDefault(element);
            } else {
                parseCustom(element);
            }
        }
    }

    private void parseCustom(Element element) {


    }

    private void parsePropertys(List<Element> elements, BeanDefination beanDefination) {
        elements.forEach(element -> parseProperty(element, beanDefination));

    }

    private void parseProperty(Element element, BeanDefination beanDefination) {
       String name =  element.attributeValue("name");
       String stringValue = element.attributeValue("value");
       String ref = element.attributeValue("ref");
       if (stringValue != null && !"".equals(stringValue) && ref != null && !"".equals(ref)) {
           return;
       }
       PropertyValue propertyValue = null;
       if (stringValue != null && !"".equals(stringValue)) {
           Class<?> targetClass = ReflectUtils.getFiledType(beanDefination.getClassName(), name);
           TypeStringValue typeStringValue = new TypeStringValue(targetClass, stringValue);
            propertyValue = new PropertyValue(name, typeStringValue);
       }else if (ref != null && !"".equals(ref)) {
           RuntimeType runtimeType = new RuntimeType(ref);
           propertyValue = new PropertyValue(name, runtimeType);
       }
       if (propertyValue == null) return;
       beanDefination.addPropertyValue(propertyValue);
    }

    private void parseDefault(Element element) {
        try {
            String id = element.attributeValue("id");
            String name = element.attributeValue("name");
            String className = element.attributeValue("class");
            Class<?> clazz = Class.forName(className);
            String beanName = id == null ? name : id;
            beanName = beanName == null ? clazz.getSimpleName() : beanName;
            String initMethod = element.attributeValue("init-method");
            BeanDefination beanDefination = new BeanDefination(id, name, className);
            beanDefination.setInitMethod(initMethod);
            parsePropertys(element.elements(), beanDefination);
            beanFactory.getBeanDefinations().put(beanName, beanDefination);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
