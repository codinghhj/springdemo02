package com.hhj.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author ：   huanghju
 * @date ：   2019/10/27
 */
public class DocumentReader {

    public static Document createDocument(InputStream inputStream) {

        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
            return null;
    }
}
