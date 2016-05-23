package com.terminalfour.xmlservicejdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceResultSet extends Unused implements ResultSet {

    private int row = 1;

    List<Map<String, Object>> data;

    ServiceResultSet() {
        this.data = new ArrayList<>();
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public boolean next() throws SQLException {
        row++;

        if (row - 1 >= data.size()) {
            return false;
        }

        return true;
    }

    @Override
    public String getString(String columnLabel) throws SQLException {

        Map<String, Object> dataRow = new HashMap<>();
        dataRow = data.get(row - 1);

        return (String) dataRow.get(columnLabel);

    }

}
