/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.objects.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.xml.XmlQueryHelper;

public class ServiceStatement
		extends Unused
		implements Statement {

	@Override
	public ResultSet executeQuery (String sqlQuery)
			throws SQLException {
		sqlQuery = sqlQuery.trim ();

		String firstToken = sqlQuery.substring (0, sqlQuery.indexOf (" "));
		Optional<Keyword> keyword = Keyword.getKeyword (firstToken);

		if (!keyword.isPresent ())
			throw new SQLException ("Invalid sql statemenet");

		List<Map<String, Object>> data = null;
		if (keyword.get () == Keyword.SELECT)
			data = XmlQueryHelper.handleSelectQuery (sqlQuery);

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
