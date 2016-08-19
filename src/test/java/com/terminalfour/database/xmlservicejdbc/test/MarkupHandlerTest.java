/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 18 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
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

import com.terminalfour.database.xmlservicejdbc.core.JDBC;

public class MarkupHandlerTest {

	private Connection conn;

	Statement stmt = null;

	@BeforeClass
	public static void forName ()
			throws Exception {
		Class.forName (JDBC.class.getCanonicalName ());
	}

	@Before
	public void connect ()
			throws Exception {
		String fileURI = getClass ().getResource ("/rssTest1.rss").toString ();
		conn = DriverManager.getConnection ("jdbc:xmlservice:" + fileURI);
		stmt = conn.createStatement ();
	}

	@After
	public void close ()
			throws SQLException {
		conn.close ();
	}
	
	@Test
	public void decodeTest ()
			throws Exception {
		String sqlQuery = "SELECT title FROM item DECODE title";
		ResultSet resultSet = stmt.executeQuery (sqlQuery);

		while (resultSet.next ()) {
			String text = resultSet.getString ("title");
			if(text.startsWith ("Brexit")){
				assertEquals(text, "Brexit – The New University Challenge");
			}
		}
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (MarkupHandlerTest.class);

}
