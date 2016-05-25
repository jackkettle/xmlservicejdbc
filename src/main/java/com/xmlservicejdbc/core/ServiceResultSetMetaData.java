package com.xmlservicejdbc.core;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ServiceResultSetMetaData extends Unused implements ResultSetMetaData {

    List<Map<String, Object>> data;

    public ServiceResultSetMetaData(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public int getColumnCount() throws SQLException {
        if (data.size() > 0)
            return data.get(0).size();

        return 0;
    }

    @Override
    public String getColumnName(int arg0) throws SQLException {
        
        if (data.size() <= 0)
            new SQLException("Connection not set");
        int index = 1;
        for (String keyName : data.get(0).keySet()) {
            if (index == arg0) {
                return keyName;
            }
            index++;
        }
        new SQLException("Failed to get Column Name");
        return null;
    }

}
