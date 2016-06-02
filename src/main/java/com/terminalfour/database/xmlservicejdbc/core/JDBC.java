/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle
 * Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class JDBC implements Driver {

    public static final String PREFIX = "jdbc:xmlservice:";
    
    static {
        try {
            DriverManager.registerDriver(new JDBC());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connect(String url, Properties info) throws SQLException {

        url = url.trim();
        if (!acceptsURL(url))
            return null;
        
        return new ServiceConnection(url);
    }

    public boolean acceptsURL(String url) throws SQLException {
        return url != null && url.toLowerCase().startsWith(PREFIX);
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[] {};
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 1;
    }

    public boolean jdbcCompliant() {
        return false;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
