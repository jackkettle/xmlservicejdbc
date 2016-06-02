/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle 
 * Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.terminalfour.database.xmlservicejdbc.core.xml.XmlQueryHelper;

public class ServiceStatement
		extends Unused
		implements Statement {

	@Override
	public ResultSet executeQuery (String sqlQuery)
			throws SQLException {
		sqlQuery = sqlQuery.trim ();

		if (!sqlQuery.toLowerCase ().startsWith (Constants.SQL_KEYWORD_SELECT.toLowerCase ()))
			throw new SQLException ("Only select statements are currently supported");

		List<Map<String, Object>> data = XmlQueryHelper.handleSelectQuery (sqlQuery);

		ServiceResultSet resultSet = new ServiceResultSet ();
		resultSet.setData (data);
		return resultSet;

	}

	@Override
	public void close ()
			throws SQLException {

	}

	@Override
	public boolean isClosed ()
			throws SQLException {
		return false;
	}

	@Override
	public void clearWarnings ()
			throws SQLException {

	}

}
