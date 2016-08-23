/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 23 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.utils;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtils {

	public static boolean elementMatches (Element element, String fullColumnName) {

		String namespace = KeywordStatementPairUtils.getNamespace (fullColumnName);
		String name = KeywordStatementPairUtils.getElement (fullColumnName);

		return element.getName ().equals (name) && element.getNamespace ().getPrefix ().equals (namespace);

	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (XmlUtils.class);

}
