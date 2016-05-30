package com.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.google.common.base.Strings;
import com.xmlservicejdbc.core.Constants;

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
        return allRowData;

    }

    private static List<Element> getAllChilden(Element element) {

        List<Element> children = new ArrayList<>();
        for (Iterator<?> i = element.elementIterator(); i.hasNext();) {
            Element childElement = (Element) i.next();
            children.add(childElement);
        }
        return children;
    }

    private static List<Attribute> getAllAttributes(Element element) {

        List<Attribute> attributes = new ArrayList<>();
        for (Iterator<?> i = element.attributeIterator(); i.hasNext();) {
            Attribute attribute = (Attribute) i.next();
            attributes.add(attribute);
        }
        return attributes;
    }

    private static Map<String, Object> getAllChildValues(Element element) {

        Map<String, Object> childValues = new HashMap<>();
        for (Element childElement : getAllChilden(element)) {
            String childName = childElement.getName();
            String childValue = childElement.getText();
            childValues.put(childName, childValue);
        }
        return childValues;
    }

    private static Map<String, Object> getAllAttributeValues(Element element) {

        Map<String, Object> attributeValues = new HashMap<>();
        for (Attribute attribute : getAllAttributes(element)) {
            String attributeName = attribute.getName();
            String attributeValue = attribute.getValue();
            attributeValues.put(attributeName, attributeValue);
        }
        return attributeValues;
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
                throw new SQLException("Unable to find either attribute or element with key: " + columnName);
            }

            if (key.contains(" "))
                key = key.replace(" ", "_");

            values.put(key, value);
        }
        return values;
    }

    public static List<Map<String, Object>> getColumnNames(List<Element> elements) {

        List<Map<String, Object>> allRowData = new ArrayList<>();

        Set<String> columnNames = new HashSet<>();
        for (Element element : elements) {
            for (Attribute attribute : getAllAttributes(element)) {
                columnNames.add(attribute.getName());
            }
            for (Element childElement : getAllChilden(element)) {
                columnNames.add(childElement.getName());
            }
        }

        for (String name : columnNames) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.COLUMN_NAME, name);
            allRowData.add(map);
        }
        return allRowData;
    }

    public static List<Map<String, Object>> getColumnValuesAdvanced(List<String> columnNames, Map<String, List<Element>> elementMap)
            throws SQLException {
        List<Map<String, Object>> allRowData = new ArrayList<>();

        for (String columnName : columnNames) {
            String column = "";
            String tableName = "";

            if (!columnName.contains(".") && elementMap.size() > 1) {
                throw new SQLException("A column name value must have a table specified when selecting from multiple tables: " + columnName);
            }

            if (elementMap.size() > 1) {
                String[] columnSegments = columnName.split(".");
                if (columnSegments.length != 2)
                    throw new SQLException("Invalid column name supplied: +", columnName);

                column = columnSegments[0];
                tableName = columnSegments[1];
            }

            List<Element> elementsToSearch = null;
            if (elementMap.size() == 1)
                elementsToSearch = elementMap.entrySet().iterator().next().getValue();
            else
                elementsToSearch = elementMap.get(tableName);
           
            

        }

        return allRowData;
    }

    private static String getValue(Element element, String columnName) throws SQLException {
        String value = "";

        if (columnName.startsWith(Constants.ATTRIBUTE_PREFIX)) {
            columnName = columnName.substring(Constants.ATTRIBUTE_PREFIX.length(), columnName.length());
            Attribute attribute = element.attribute(columnName);
            if (attribute != null)
                value = attribute.getValue();
        } else if (columnName.startsWith(Constants.CHILD_PREFIX)) {
            columnName = columnName.substring(Constants.CHILD_PREFIX.length(), columnName.length());
            Element childElement = element.element(columnName);
            if (childElement != null)
                value = childElement.getText();
        } else {
            Attribute attribute = element.attribute(columnName);
            Element childElement = element.element(columnName);
            if (attribute != null) {
                value = attribute.getValue();
            }
            if (childElement != null) {
                value = childElement.getText();
            }
        }
        return value;
    }

}
