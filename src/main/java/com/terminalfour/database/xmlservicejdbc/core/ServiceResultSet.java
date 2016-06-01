package com.xmlservicejdbc.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceResultSet extends Unused implements ResultSet {

    private int row = 0;

    List<Map<String, Object>> data;

    ServiceResultSet() {
        this.data = new ArrayList<>();
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public boolean next() throws SQLException {

        if (row >= data.size()) {
            return false;
        }

        row++;
        return true;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new ServiceResultSetMetaData(data);
    }

    @Override
    public String getString(String columnLabel) throws SQLException {

        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        return (String) dataRow.get(columnLabel);

    }

    @Override
    public String getString(int arg0) throws SQLException {

        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        if (arg0 < 0 || arg0 > dataRow.size()) {
            throw new SQLException("Invalid argument " + arg0);
        }

        int index = 0;
        for (Map.Entry<String, Object> entry : dataRow.entrySet()) {

            if (index == arg0)
                return (String) entry.getValue();

            index++;
        }
        throw new SQLException("Unable to get value from column with index " + arg0);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        int index = 0;
        for (Map.Entry<String, Object> entry : dataRow.entrySet()) {
            if (columnLabel.equals(entry.getKey()))
                return index;

            index++;
        }
        throw new SQLException("Unable to find column with label " + columnLabel);
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        if (columnIndex < 0 || columnIndex > dataRow.size()) {
            throw new SQLException("Invalid argument " + columnIndex);
        }

        int index = 1;
        for (Map.Entry<String, Object> entry : dataRow.entrySet()) {

            if (index == columnIndex)
                return entry.getValue();

            index++;

        }
        throw new SQLException("Unable to get value from column with index " + columnIndex);
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        return dataRow.get(columnLabel);
    }

}
