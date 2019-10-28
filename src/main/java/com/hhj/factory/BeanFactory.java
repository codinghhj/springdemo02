package com.hhj.factory;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public interface BeanFactory {
    /**
     *功能描述
     * @date 2019/10/27
     * @param
     * @return java.lang.Object
     */
    Object getBean(String beanName);
    /***
     *功能描述
     * @date 2019/10/27
     * @param
     * @return java.lang.Object
     */
    Object getBean(Class<?> beanClass);
}
