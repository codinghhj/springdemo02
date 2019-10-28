package com.hhj.config;


import com.hhj.factory.DefaultListableBeanFactory;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class XMLBeanDefinationParser {
    public XMLBeanDefinationParser() {
    }
    public void loadDefinations(DefaultListableBeanFactory beanFactory, Resource resource) {
        InputStream inputStream = resource.getInputStream();
        Document document =  DocumentReader.createDocument(inputStream);
        XMLDocumentParser xmlDocumentParser = new XMLDocumentParser(beanFactory);
        xmlDocumentParser.loadDefination(document.getRootElement());

    }
}
