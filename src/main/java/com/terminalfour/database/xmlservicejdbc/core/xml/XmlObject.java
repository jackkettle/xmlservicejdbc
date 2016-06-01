package com.xmlservicejdbc.core.xml;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import org.dom4j.Element;

import com.google.common.collect.Lists;

public class XmlObject {

    private Document document;

    public XmlObject() {
    }

    public XmlObject(String xmlData) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new InputSource(new StringReader(xmlData)));
        this.setDocument(document);
    }

    public List<Element> getElementsByName(String name, Element parent, List<Element> elementList) {
        if (elementList == null)
            elementList = new ArrayList<Element>();

        for (Iterator<?> i = parent.elementIterator(); i.hasNext();) {
            Element current = (Element) i.next();
            if (current.getName().equalsIgnoreCase(name)) {
                elementList.add(current);
            }

            getElementsByName(name, current, elementList);
        }
        return elementList;
    }

    public List<Element> getElements(Element parent, List<Element> elementList) {
        if (elementList == null)
            elementList = new ArrayList<Element>();

        for (Iterator<?> i = parent.elementIterator(); i.hasNext();) {
            Element current = (Element) i.next();
            elementList.add(current);

            getElements(current, elementList);
        }
        return elementList;
    }
    
    public List<Element> getAllElements() {
        List<Element> allElements = new ArrayList<Element>();
        allElements = getElements(document.getRootElement(), allElements);
        return allElements;
    }
    
    public List<Element> getAllElementsByName(String name) {
        List<Element> allElements = new ArrayList<Element>();
        allElements = getElementsByName(name, document.getRootElement(), allElements);
        return allElements;
    }
    
    public List<String> getUniqueElementNames() {
        Set<String> uniqueNames = new HashSet<>();
        List<Element> allElements = new ArrayList<Element>();
        allElements = getElements(document.getRootElement(), allElements);
        for(Element element: allElements){
            uniqueNames.add(element.getName());
        }
        return Lists.newArrayList(uniqueNames) ;
    }


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
