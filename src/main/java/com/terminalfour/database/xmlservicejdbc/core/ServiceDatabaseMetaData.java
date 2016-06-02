/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.terminalfour.database.xmlservicejdbc.core.xml.SavedResponseProvider;
import com.terminalfour.database.xmlservicejdbc.core.xml.XmlQueryHelper;

public class ServiceDatabaseMetaData
		extends Unused
		implements DatabaseMetaData {

	@Override
	public ResultSet getTables (String catalog, String schemaPattern, String tableNamePattern, String[] types)
			throws SQLException {

		if (!SavedResponseProvider.isSet ()) {
			throw new SQLException ("The connection is not set");
		}

		ServiceResultSet resultSet = new ServiceResultSet ();
		resultSet.setData (XmlQueryHelper.getTablesFromXml (SavedResponseProvider.getXmlObject ()));
		return resultSet;
	}

	@Override
	public ResultSet getColumns (String arg0, String arg1, String arg2, String arg3)
			throws SQLException {

		ServiceResultSet resultSet = new ServiceResultSet ();
		resultSet.setData (XmlQueryHelper.getColumnNames (arg2));
		return resultSet;

	}

	@Override
	public boolean isReadOnly ()
			throws SQLException {
		return true;
	}

	@Override
	public int getDriverMajorVersion () {
		return 0;
	}

	@Override
	public int getDriverMinorVersion () {
		return 0;
	}

}
