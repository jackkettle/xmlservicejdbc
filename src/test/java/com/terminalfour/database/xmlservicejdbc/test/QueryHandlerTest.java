/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 17 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminalfour.database.xmlservicejdbc.core.handlers.QueryHandler;
import com.terminalfour.database.xmlservicejdbc.core.objects.KeywordStatementPair;

public class QueryHandlerTest {

	@Test
	public void test1 ()
			throws Exception {

		String query = "SELECT * FROM blah";

		List<KeywordStatementPair> actionList = QueryHandler.getActionList (query);

		assertTrue (actionList.size () == 2);

	}

	@Test
	public void test2 ()
			throws Exception {

		String query = "SELECT FROM blah,2, 3, 4 DATE_ELEMENT";

		List<KeywordStatementPair> actionList = QueryHandler.getActionList (query);

		assertTrue (actionList.size () == 3);
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (QueryHandlerTest.class);

}
