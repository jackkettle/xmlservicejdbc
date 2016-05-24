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

}
