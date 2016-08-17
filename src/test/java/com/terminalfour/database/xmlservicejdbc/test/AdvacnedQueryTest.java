/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 02 June 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.terminalfour.database.xmlservicejdbc.core.Constants;
import com.terminalfour.database.xmlservicejdbc.core.JDBC;
import com.terminalfour.database.xmlservicejdbc.core.ServiceResultSet;

public class AdvacnedQueryTest {

	private Connection conn;

	private DatabaseMetaData meta;

	Statement stmt = null;

	@BeforeClass
	public static void forName ()
			throws Exception {
		Class.forName (JDBC.class.getCanonicalName ());
	}

	@Before
	public void connect ()
			throws Exception {
		String fileURI = getClass ().getResource ("/testResponse3.xml").toString ();
		conn = DriverManager.getConnection ("jdbc:xmlservice:" + fileURI);
		meta = conn.getMetaData ();
		stmt = conn.createStatement ();
	}

	@After
	public void close ()
			throws SQLException {
		meta = null;
		conn.close ();
	}

	@Test
	public void advancedQueryTestFail ()
			throws Exception {
		String sqlQuery = "ADVANCED_SELECT * FROM size, catalog_item PARENT size";
		try {
			stmt.executeQuery (sqlQuery);
		}
		catch (SQLException e) {
			assertEquals (e.getMessage (), "Unsupported * operator found");
		}
	}

	@Test
	public void advancedQueryTestPass ()
			throws Exception {
		String sqlQuery = "ADVANCED_SELECT catalog_item.gender, catalog_item.item_number, size.description, size. FROM catalog_item, size PARENT catalog_item";
		ResultSet resultSet = stmt.executeQuery (sqlQuery);
		
		while (resultSet.next ()) {
			
		}
		
	}

	private static final Logger logger = LoggerFactory.getLogger (AdvacnedQueryTest.class);

}
