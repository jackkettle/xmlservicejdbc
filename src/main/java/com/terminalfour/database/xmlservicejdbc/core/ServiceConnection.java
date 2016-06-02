/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.terminalfour.database.xmlservicejdbc.core.xml.SavedResponseProvider;

public class ServiceConnection
		extends Unused
		implements Connection {

	private boolean isClosed = true;

	public ServiceConnection (String urlString)
			throws SQLException {

		urlString = urlString.replace (JDBC.PREFIX, "");
		urlString = urlString.trim ();

		if (Strings.isNullOrEmpty (urlString))
			return;

		Protocol urlProtocol = Protocol.getProtocol (urlString);
		if (urlProtocol == Protocol.INVALID) {
			throw new SQLException ("Invalid protocol (only file, http, https supported) on url: {}", urlString);
		}

		Optional<String> responseDataWrapper = Optional.absent ();
		if (urlProtocol == Protocol.FILE)
			responseDataWrapper = Utils.getStringFromFile (urlString);
		else
			responseDataWrapper = Utils.getStringFromUrl (urlString);

		if (!responseDataWrapper.isPresent ())
			throw new SQLException ("No data recieved from url: " + urlString);

		try {
			SavedResponseProvider.setResponseString (responseDataWrapper.get ());
		}
		catch (DocumentException e) {
			throw new SQLException ("Unable to convert response from url to xmlObject: " + urlString + "\n" + e.getMessage ());
		}

		this.isClosed = false;

		logger.info ("Connections successfully established");
	}

	public DatabaseMetaData getMetaData ()
			throws SQLException {
		return new ServiceDatabaseMetaData ();
	}

	public Statement createStatement ()
			throws SQLException {
		return new ServiceStatement ();
	}

	@Override
	public boolean isReadOnly ()
			throws SQLException {
		return true;
	}

	@Override
	public void setClientInfo (Properties properties)
			throws SQLClientInfoException {}

	@Override
	public void setClientInfo (String name, String value)
			throws SQLClientInfoException {}

	@Override
	public void close ()
			throws SQLException {
		this.isClosed = true;
		SavedResponseProvider.unSet ();
	}

	@Override
	public boolean isClosed ()
			throws SQLException {
		return this.isClosed;
	}

	@Override
	public boolean getAutoCommit ()
			throws SQLException {
		return false;
	}

	@Override
	public void setAutoCommit (boolean arg0)
			throws SQLException {}

	@Override
	public void clearWarnings ()
			throws SQLException {}

	private static final Logger logger = LoggerFactory.getLogger (ServiceConnection.class);

}
