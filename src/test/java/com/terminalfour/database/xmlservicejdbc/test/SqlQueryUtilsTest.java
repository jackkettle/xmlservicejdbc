/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 07 June 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.utils.SqlQueryUtils;

public class SqlQueryUtilsTest {

	@Test
	public void getTableName ()
			throws Exception {
		
		List<String> list = new ArrayList<>(Arrays.asList("one"));
		String sqlQuery = "SELECT * FROM one";

		Optional<List<String>> responseWrapper = SqlQueryUtils.getTableNamesFromQuery (sqlQuery);

		if (!responseWrapper.isPresent ())
			throw new Exception ();

		assertEquals(list, responseWrapper.get ());
		
	}

	@Test
	public void getTableNames ()
			throws Exception {

		List<String> list = new ArrayList<> (Arrays.asList ("one", "two", "three"));
		String sqlQuery = "SELECT * FROM one, two, three";

		Optional<List<String>> responseWrapper = SqlQueryUtils.getTableNamesFromQuery (sqlQuery);

		if (!responseWrapper.isPresent ())
			throw new Exception ();

		assertEquals (list, responseWrapper.get ());

	}
	
	@Test
	public void getTableNamesAdvanced ()
			throws Exception {

		List<String> list = new ArrayList<> (Arrays.asList ("one", "two", "three"));
		String sqlQuery = "SELECT * FROM one, two, three WHERE one IS_PARENT";

		Optional<List<String>> responseWrapper = SqlQueryUtils.getTableNamesFromQuery (sqlQuery);

		if (!responseWrapper.isPresent ())
			throw new Exception ();

		assertEquals (list, responseWrapper.get ());

	}

}
