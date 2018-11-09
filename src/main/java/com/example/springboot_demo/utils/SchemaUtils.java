package com.example.springboot_demo.utils;//import org.springframework.core.io.ClassPathResource;

import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class SchemaUtils {
    public static void validateBySchema(Object jaxb, String filePath) throws SAXException, IOException, JAXBException, SAXParseException {

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new ClassPathResource(filePath).getFile());
            JAXBContext jaxbContext = JAXBContext.newInstance(jaxb.getClass());
            Validator validator = schema.newValidator();
            Source source = new JAXBSource(jaxbContext, jaxb);
            validator.validate(source);


    }
}