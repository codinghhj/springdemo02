package com.hhj.converter;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class IntegerConvert implements  TypeConvert {
    @Override
    public boolean isType(Class<?> clazz) {
        return Integer.class == clazz;
    }

    @Override
    public Integer convert(String stringValue) {
        return Integer.parseInt(stringValue);
    }
}
