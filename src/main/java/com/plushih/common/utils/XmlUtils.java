package com.plushih.common.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by sangyong on 10/21/14.
 */
public class XmlUtils {

  public static <T> String getStringToXmlFile(T pojoObject,
                                                    String fileName) throws JAXBException, FileNotFoundException {
    JAXBContext jc = JAXBContext.newInstance(pojoObject.getClass());
    Marshaller marshaller = jc.createMarshaller();
    marshaller.marshal(pojoObject, new FileOutputStream(fileName));

    return fileName;
  }

  public static Object getXmlStrToObject(String xmlStr, Class<?> clazz) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(clazz);
    Unmarshaller unMarshaller = jc.createUnmarshaller();
    Object obj = unMarshaller.unmarshal(new StringReader(xmlStr));

    return obj;
  }

  public static <T> String objectToXmlStr(T object) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(object.getClass());
    Marshaller marshaller = jc.createMarshaller();
    StringWriter stringWriter = new StringWriter();
    marshaller.marshal(object, stringWriter);
    String result = stringWriter.toString();
    stringWriter.close();

    return result;
  }
}
