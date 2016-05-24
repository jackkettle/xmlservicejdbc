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
    public String getString(String columnLabel) throws SQLException {

        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        return (String) dataRow.get(columnLabel);

    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new ServiceResultSetMetaData();
    }

}
