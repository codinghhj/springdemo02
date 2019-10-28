package com.hhj.converter;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public interface TypeConvert {
    boolean isType(Class<?> clazz);
    Object convert(String stringValue);
}
