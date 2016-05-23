package com.terminalfour.xmlservicejdbc.core;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminalfour.xmlservicejdbc.core.xml.SavedResponseProvider;
import com.terminalfour.xmlservicejdbc.core.xml.XmlQueryHelper;

public class ServiceDatabaseMetaData extends Unused implements DatabaseMetaData {

    @Override
    public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types) throws SQLException {

        if (!SavedResponseProvider.isSet()) {
            throw new SQLException("The connection is not set");
        }

        List<Map<String, Object>> data = new ArrayList<>();

        data = XmlQueryHelper.getTablesFromXml(SavedResponseProvider.getXmlObject());

        ServiceResultSet resultSet = new ServiceResultSet();
        resultSet.setData(data);
        return resultSet;
    }

    private static final Logger logger = LoggerFactory.getLogger(ServiceDatabaseMetaData.class);

}
