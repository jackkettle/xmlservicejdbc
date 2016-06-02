/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class ServiceResultSetMetaData
		extends Unused
		implements ResultSetMetaData {

	List<Map<String, Object>> data;

	public ServiceResultSetMetaData (List<Map<String, Object>> data) {
		this.data = data;
	}

	@Override
	public int getColumnCount ()
			throws SQLException {
		if (data.size () > 0)
			return data.get (0).size ();

		return 0;
	}

	@Override
	public String getColumnName (int arg0)
			throws SQLException {

		if (data.size () <= 0)
			throw new SQLException ("Connection not set");
		int index = 1;
		for (String keyName : data.get (0).keySet ()) {
			if (index == arg0) {
				return keyName;
			}
			index++;
		}
		throw new SQLException ("Failed to get Column Name");
	}

	@Override
	public boolean isAutoIncrement (int arg0)
			throws SQLException {
		return false;
	}

	@Override
	public boolean isCaseSensitive (int column)
			throws SQLException {
		return true;
	}

	@Override
	public boolean isCurrency (int column)
			throws SQLException {
		return false;
	}

	@Override
	public int isNullable (int column)
			throws SQLException {
		return 0;
	}

	@Override
	public boolean isSigned (int column)
			throws SQLException {
		return false;
	}

	@Override
	public boolean isSearchable (int column)
			throws SQLException {
		return false;
	}

	@Override
	public int getColumnDisplaySize (int column)
			throws SQLException {
		return 0;
	}

	@Override
	public String getColumnLabel (int column)
			throws SQLException {

		if (data.size () <= 0)
			throw new SQLException ("Connection not set");
		int index = 1;
		for (String keyName : data.get (0).keySet ()) {
			if (index == column) {
				return keyName;
			}
			index++;
		}
		throw new SQLException ("Failed to get Column Name");
	}

	@Override
	public String getSchemaName (int column)
			throws SQLException {
		return null;
	}

	@Override
	public int getPrecision (int column)
			throws SQLException {
		return 0;
	}

	@Override
	public int getScale (int column)
			throws SQLException {
		return 0;
	}

	@Override
	public String getTableName (int column)
			throws SQLException {
		return null;
	}

	@Override
	public String getCatalogName (int column)
			throws SQLException {
		return null;
	}

	@Override
	public int getColumnType (int column)
			throws SQLException {
		return Types.VARCHAR;
	}

	@Override
	public String getColumnTypeName (int column)
			throws SQLException {
		return "VARCHAR";
	}

}
