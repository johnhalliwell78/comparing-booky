package com.example.springboot_demo.utils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class XmlUtils {

    public static XMLStreamReader parseContentToCursor(String source) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        factory.setProperty(XMLInputFactory.IS_VALIDATING, false);
        StringReader reader = new StringReader(source);
        return factory.createXMLStreamReader(reader);
    }


    public static String getNodeValue(XMLStreamReader reader, String elementName,
                                      String namespaceUri, String attrName) throws XMLStreamException {
        if (reader != null) {
            while (reader.hasNext()) {
                int cursor = reader.getEventType();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals(elementName)) {
                        return reader.getAttributeValue(namespaceUri, attrName);
                    }
                }
                reader.next();
            }
        }
        return null;
    }

    public static String getTextContent(XMLStreamReader reader, String elementName)
            throws XMLStreamException {
        if (reader == null) {
            return null;
        }
        String result = "";
        boolean isInElement = false;
        while (reader.hasNext()) {
            int cursor = reader.getEventType();
            if (cursor == XMLStreamConstants.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (tagName.equals(elementName)) {
                    isInElement = true;
                }
            } else if (isInElement && cursor == XMLStreamConstants.CHARACTERS || cursor == XMLStreamConstants.ENTITY_REFERENCE) {
                result += reader.getText() + " ";
            } else if (isInElement && cursor == XMLStreamConstants.END_ELEMENT) {
                return result;
            }
            reader.next();
        }
        return null;
    }
}
