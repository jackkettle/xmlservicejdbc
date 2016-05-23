package com.terminalfour.xmlservicejdbc.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminalfour.xmlservicejdbc.MetaDataTest;

public class ServiceDatabaseMetaData extends Unused implements DatabaseMetaData {

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {

        
        ResultSet resultSet = new ServiceResultSet(null);
        
        return resultSet;
    }
        
    private static final Logger logger = LoggerFactory.getLogger (ServiceDatabaseMetaData.class);


}
