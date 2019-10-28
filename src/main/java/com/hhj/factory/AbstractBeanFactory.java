package com.hhj.factory;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class AbstractBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(Class<?> beanClass) {
        return null;
    }
}
