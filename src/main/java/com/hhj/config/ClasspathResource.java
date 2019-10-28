package com.hhj.config;

import java.io.InputStream;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class ClasspathResource implements Resource {
    private String location;
    @Override
    public boolean isCanRead(String location) {
       if (location == null || "".equals(location)) {
           return false;
       }
       if (location.startsWith("classpath:")){
           this.location = location.replaceAll("classpath:", "");
           return true;
       }
       return false;
    }

    @Override
    public InputStream getInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
