package com.xmlservicejdbc.core;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xmlservicejdbc.core.xml.SavedResponseProvider;
import com.xmlservicejdbc.core.xml.XmlQueryHelper;

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

    @Override
    public ResultSet getColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {

        ServiceResultSet resultSet = new ServiceResultSet();
        resultSet.setData(XmlQueryHelper.getColumnNames(arg2));
        return resultSet;
        
    }
    
    @Override
    public boolean isReadOnly() throws SQLException {
        return true;
    }

}
