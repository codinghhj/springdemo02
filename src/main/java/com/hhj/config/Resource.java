package com.hhj.config;

import java.io.InputStream;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public interface Resource {
    boolean isCanRead(String location);
    InputStream getInputStream();

}
