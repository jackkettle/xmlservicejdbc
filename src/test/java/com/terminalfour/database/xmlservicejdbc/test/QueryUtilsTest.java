/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 23 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.utils.QueryUtils;

public class QueryUtilsTest {

	Document document;

	Element root;

	@Before
	public void connect ()
			throws Exception {
		document = DocumentHelper.createDocument ();
		root = document.addElement ("root");
	}

	@Test
	public void getValueFromElementBasedOnColumnNameNoNamespace ()
			throws Exception {

		final String elementName = "subject";
		final String value = "This is the subject value";
		final String columnName = "subject";
		
		root.addElement (elementName);

		Element subject = root.element (elementName);
		subject.setText (value);
		
		Optional<String> valueWrapper = QueryUtils.getValueFromElement (subject, columnName);

		if(!valueWrapper.isPresent ())
			throw new Exception("");
		
		if(!valueWrapper.get ().equals (value))
			throw new Exception("");
		
	}

	@Test
	public void getValueFromElementBasedOnColumnNameWithNamespace ()
			throws Exception {

		final String elementName = "subject";
		final String attributeName = "location";
		final String value = "This is the subject value";
		final String columnName = "subject$location";
		
		root.addElement (elementName);

		Element subject = root.element (elementName);
		subject.addAttribute (attributeName, value);

		Optional<String> valueWrapper = QueryUtils.getValueFromElement (subject, columnName);
		if(!valueWrapper.isPresent ())
			throw new Exception("");
		
		if(!valueWrapper.get ().equals (value))
			throw new Exception("");
		
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (QueryUtilsTest.class);

}
