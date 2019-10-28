package com.hhj.converter;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class StringConvert implements TypeConvert {
    @Override
    public boolean isType(Class<?> clazz) {
        return String.class == clazz;
    }

    @Override
    public String convert(String stringValue) {
        return stringValue;
    }
}
