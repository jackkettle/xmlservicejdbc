/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 17 August 2016
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

public class DateTest {

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
	public void dateTestWorks ()
			throws Exception {
		String sqlQuery = "SELECT pubDate FROM item DATE_ELEMENT pubDate DATE_FORMAT \"EEE, dd MMM yyyy HH:mm:ss z\" ";
		ResultSet resultSet = stmt.executeQuery (sqlQuery);

		while (resultSet.next ()) {
			assertEquals(resultSet.getString ("pubDate"), "2016-08-217 11:08:00");
			return;
		}
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (DateTest.class);

}
