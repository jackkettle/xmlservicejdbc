/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.test;

import static org.junit.Assert.assertTrue;

import com.sun.rowset.CachedRowSetImpl;
import com.terminalfour.database.xmlservicejdbc.core.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Strings;

@SuppressWarnings("restriction")
public class QueryTest {

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
		String fileURI = getClass ().getResource ("/testResponse.xml").toString ();
		conn = DriverManager.getConnection ("jdbc:xmlservice:" + fileURI);
		stmt = conn.createStatement ();
	}

	@After
	public void close ()
			throws SQLException {
		stmt = null;
		conn.close ();
	}

	@Test
	public void selectAllQueryTest ()
			throws Exception {
		String sql = "SELECT * FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		int indexCheck = 0;
		while (resultSet.next ()) {
			indexCheck++;
			if (Strings.isNullOrEmpty (resultSet.getString ("session"))) {
				throw new Exception ();
			}
		}
		assertTrue (indexCheck == 6);

	}

	@Test
	public void selectCoulmnQueryTest ()
			throws Exception {
		String sql = "SELECT courseCode FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		int indexCheck = 0;
		while (resultSet.next ()) {
			indexCheck++;
			if (Strings.isNullOrEmpty (resultSet.getString ("courseCode"))) {
				throw new Exception ();
			}
		}
		assertTrue (indexCheck == 6);
	}

	@Test
	public void selectCoulmnQueryShouldFailTest ()
			throws Exception {
		String sql = "SELECT courseCode FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		int indexCheck = 0;
		while (resultSet.next ()) {
			indexCheck++;
			if (!Strings.isNullOrEmpty (resultSet.getString ("session"))) {
				throw new Exception ();
			}
		}
		assertTrue (indexCheck == 6);
	}

	@Test
	public void updateQueryTest ()
			throws Exception {
		String sql = "UPDATE courseInstance SET courseInstance=\"blah\"";
		try {
			stmt.executeQuery (sql);
		}
		catch (SQLException e) {
			return;
		}
		throw new Exception ();
	}

	@Test
	public void cachedRowSetImplBugTest ()
			throws Exception {
		String sql = "SELECT courseCode FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		CachedRowSet cachedRowSet = new CachedRowSetImpl ();
		cachedRowSet.populate (resultSet);
		cachedRowSet.findColumn ("courseCode");
		cachedRowSet.close ();
	}

	@Test
	public void sanitizeXMLTestByAddingMissingColumns ()
			throws Exception {

		conn = DriverManager
				.getConnection ("	jdbc:xmlservice:https://democe.campusit.net/app/democe/solar.qll_web.common?serviceCode=online_reg&auth=demokey");
		stmt = conn.createStatement ();

		String sql = "SELECT * FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		CachedRowSet cachedRowSet = new CachedRowSetImpl ();
		cachedRowSet.populate (resultSet);
		cachedRowSet.findColumn ("session");
		cachedRowSet.close ();
	}

	@Test
	public void columnOrderingTest ()
			throws Exception {

		conn = DriverManager
				.getConnection ("	jdbc:xmlservice:https://democe.campusit.net/app/democe/solar.qll_web.common?serviceCode=online_reg&auth=demokey");
		stmt = conn.createStatement ();

		String sql = "SELECT * FROM courseInstance";
		ResultSet resultSet = stmt.executeQuery (sql);

		@SuppressWarnings("resource")
		CachedRowSet cachedRowSet = new CachedRowSetImpl ();
		cachedRowSet.populate (resultSet);

		boolean first = true;
		int previous = 0;
		while (cachedRowSet.next ()) {
			int index = cachedRowSet.findColumn ("quota");
			if (first) {
				first = false;
				previous = index;
			}
			assertTrue (previous == index);
		}

	}

}
