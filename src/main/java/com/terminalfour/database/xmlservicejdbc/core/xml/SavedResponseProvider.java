/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.xml;

import org.dom4j.DocumentException;

public class SavedResponseProvider {

	private static XmlObject xmlObject;

	private static String responseString;

	private static boolean isSet = false;

	public static String getResponseString () {
		return responseString;
	}

	public static void setResponseString (String responseString)
			throws DocumentException {
		isSet = true;
		SavedResponseProvider.responseString = responseString;
		String XString = responseString.replaceAll("[^\\x20-\\x7e\\x0A]", "");
		setXmlObject (new XmlObject (XString));

	}

	public static boolean isSet () {
		return isSet;
	}

	public static void unSet () {
		isSet = false;
		setXmlObject (null);
		responseString = "";
	}

	public static XmlObject getXmlObject () {
		return xmlObject;
	}

	public static void setXmlObject (XmlObject xmlObject) {
		SavedResponseProvider.xmlObject = xmlObject;
	}

}
