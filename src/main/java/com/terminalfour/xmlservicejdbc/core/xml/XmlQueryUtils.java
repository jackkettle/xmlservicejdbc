package com.terminalfour.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.terminalfour.xmlservicejdbc.core.Constants;

public class XmlQueryUtils {

    public static List<Map<String, Object>> getColumnValues(List<Element> elements, List<String> columnNames) throws SQLException {
        return getAllColumnValues(elements, columnNames);
    }

    public static List<Map<String, Object>> getAllColumnValues(List<Element> elements) throws SQLException {
        return getAllColumnValues(elements, null);
    }

    private static List<Map<String, Object>> getAllColumnValues(List<Element> elements, List<String> columnNames) throws SQLException {

        boolean allNames = false;
        if (columnNames == null)
            allNames = true;

        List<Map<String, Object>> allRowData = new ArrayList<>();
        for (Element element : elements) {
            Map<String, Object> allValues = new HashMap<>();
            if (allNames) {
                Map<String, Object> attributeValues = getAllAttributeValues(element);
                Map<String, Object> childValues = getAllChildValues(element);
                allValues.putAll(attributeValues);
                allValues.putAll(childValues);
            } else {
                allValues.putAll(getAllValues(element, columnNames));
            }
            allRowData.add(allValues);
        }
        logger.info("Total rows returned: {}", allRowData.size());
        return allRowData;

    }

    private static Map<String, Object> getAllAttributeValues(Element element) {

        Map<String, Object> attributeValues = new HashMap<>();
        for (Iterator<?> i = element.attributeIterator(); i.hasNext();) {
            Attribute attribute = (Attribute) i.next();
            String attributeName = attribute.getName();
            String attributeValue = attribute.getValue();
            attributeValues.put(attributeName, attributeValue);
        }
        return attributeValues;
    }

    private static Map<String, Object> getAllChildValues(Element element) {

        Map<String, Object> childValues = new HashMap<>();
        for (Iterator<?> i = element.elementIterator(); i.hasNext();) {
            Element childElement = (Element) i.next();
            String childName = childElement.getName();
            String childValue = childElement.getText();
            childValues.put(childName, childValue);
        }
        return childValues;
    }

    private static Map<String, String> getAllValues(Element element, List<String> columnNames) throws SQLException {
        Map<String, String> values = new HashMap<>();
        for (String columnName : columnNames) {

            String key = "";
            String value = "";

            if (columnName.startsWith(Constants.ATTRIBUTE_PREFIX)) {
                columnName = columnName.substring(Constants.ATTRIBUTE_PREFIX.length(), columnName.length());
                Attribute attribute = element.attribute(columnName);
                if (attribute == null)
                    break;
                key = columnName;
                value = attribute.getValue();
            } else if (columnName.startsWith(Constants.CHILD_PREFIX)) {
                columnName = columnName.substring(Constants.CHILD_PREFIX.length(), columnName.length());
                Element childElement = element.element(columnName);
                if (childElement == null)
                    break;
                key = columnName;
                value = childElement.getText();
            } else {
                Attribute attribute = element.attribute(columnName);
                Element childElement = element.element(columnName);
                if (attribute != null) {
                    key = columnName;
                    value = attribute.getValue();
                }
                if (childElement != null) {
                    key = columnName;
                    value = childElement.getText();
                }
            }

            if (Strings.isNullOrEmpty(key)) {
                throw new SQLException("Unable to find either attribute or element with key: {}", columnName);
            }

            values.put(key, value);
        }
        return values;
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(XmlQueryUtils.class);

}
