/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.terminalfour.database.xmlservicejdbc.core.Constants;

public class QueryUtils {

	public static List<Map<String, Object>> getColumnValues (List<Element> elements, List<String> columnNames)
			throws SQLException {
		return getAllColumnValues (elements, columnNames);
	}

	public static List<Map<String, Object>> getAllColumnValues (List<Element> elements)
			throws SQLException {
		return getAllColumnValues (elements, null);
	}

	private static List<Map<String, Object>> getAllColumnValues (List<Element> elements, List<String> columnNames)
			throws SQLException {

		boolean allNames = false;
		if (columnNames == null)
			allNames = true;

		List<Map<String, Object>> allRowData = new ArrayList<> ();
		for (Element element : elements) {

			Map<String, Object> attributeValues;
			Map<String, Object> childValues;
			Map<String, Object> childAttributeValues;

			if (allNames) {
				attributeValues = getAllAttributeValues (element, null);
				childValues = getAllChildValues (element, null);
				childAttributeValues = getAllChildAttributeValues (element, null);
			}
			else {
				attributeValues = getAllAttributeValues (element, columnNames);
				childValues = getAllChildValues (element, columnNames);
				childAttributeValues = getAllChildAttributeValues (element, columnNames);
			}

			Map<String, Object> allValues = new TreeMap<> ();
			allValues.putAll (attributeValues);
			allValues.putAll (childValues);
			allValues.putAll (childAttributeValues);
			allRowData.add (allValues);
		}
		return allRowData;

	}

	private static List<Element> getAllChilden (Element element) {

		List<Element> children = new ArrayList<> ();
		for (Iterator<?> i = element.elementIterator (); i.hasNext ();) {
			Element childElement = (Element)i.next ();
			children.add (childElement);
		}
		return children;
	}

	private static List<Attribute> getAllAttributes (Element element) {

		List<Attribute> attributes = new ArrayList<> ();
		for (Iterator<?> i = element.attributeIterator (); i.hasNext ();) {
			Attribute attribute = (Attribute)i.next ();
			attributes.add (attribute);
		}
		return attributes;
	}

	private static Map<String, Object> getAllChildValues (Element element, List<String> columnNames) {

		Map<String, Object> childValues = new HashMap<> ();
		for (Element childElement : getAllChilden (element)) {
			String childName = childElement.getName ();
			String childElementNameSpacePrefix = childElement.getNamespacePrefix ();
			if (!Strings.isNullOrEmpty (childElementNameSpacePrefix))
				childName = childElementNameSpacePrefix + Constants.NAMESPACE_DELIMITER + childName;

			if (columnNames != null && !columnNames.contains (childName))
				continue;

			String childValue = childElement.getText ();
			childValues.put (childName, childValue);
		}
		return childValues;
	}

	private static Map<String, Object> getAllAttributeValues (Element element, List<String> columnNames) {

		String childElementNameSpacePrefix = element.getNamespacePrefix ();

		Map<String, Object> attributeValues = new HashMap<> ();
		for (Attribute attribute : getAllAttributes (element)) {

			String attributeName = attribute.getName ();

			if (!Strings.isNullOrEmpty (childElementNameSpacePrefix))
				attributeName = childElementNameSpacePrefix + Constants.NAMESPACE_DELIMITER + attributeName;

			if (columnNames != null && !columnNames.contains (attributeName))
				continue;

			String attributeValue = attribute.getValue ();
			attributeValues.put (attributeName, attributeValue);
		}
		return attributeValues;
	}

	private static Map<String, Object> getAllChildAttributeValues (Element element, List<String> columnNames) {
		Map<String, Object> attributeValues = new HashMap<> ();
		for (Iterator<?> i = element.elementIterator (); i.hasNext ();) {
			Element childElement = (Element)i.next ();

			String childElementName = childElement.getName ();
			String childElementNameSpacePrefix = childElement.getNamespacePrefix ();

			for (Attribute attribute : getAllAttributes (childElement)) {

				String attributeName = childElementName + Constants.ATTRIBUTE_DELIMITER + attribute.getName ();

				if (!Strings.isNullOrEmpty (childElementNameSpacePrefix))
					attributeName = childElementNameSpacePrefix + Constants.NAMESPACE_DELIMITER + attributeName;

				if (columnNames != null && !columnNames.contains (attributeName))
					continue;

				String attributeValue = attribute.getValue ();
				attributeValues.put (attributeName, attributeValue);
			}

		}
		return attributeValues;
	}

	public static List<Map<String, Object>> getColumnNames (List<Element> elements) {

		List<Map<String, Object>> allRowData = new ArrayList<> ();

		Set<String> columnNames = new HashSet<> ();
		for (Element element : elements) {
			for (Attribute attribute : getAllAttributes (element)) {
				columnNames.add (attribute.getName ());
			}
			for (Element childElement : getAllChilden (element)) {
				columnNames.add (childElement.getName ());
			}
			for (Element childElement : getAllChilden (element)) {
				columnNames.add (childElement.getName ());
			}
		}

		for (String name : columnNames) {
			Map<String, Object> map = new HashMap<> ();
			map.put (Constants.COLUMN_NAME, name);
			allRowData.add (map);
		}
		return allRowData;
	}

	public static Optional<String> getValueFromElement (Element element, String columnName) {

		String attribute = KeywordStatementPairUtils.getAttribute (columnName);
		String name = KeywordStatementPairUtils.getElement (columnName);
		
		if(!element.getName ().equals (name)){
			return Optional.absent ();
		}
		
		if(Strings.isNullOrEmpty (attribute)){
			return Optional.of (element.getText ());
		}
		
		return Optional.of (element.attributeValue (attribute));
		
	}
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (QueryUtils.class);
	
}
