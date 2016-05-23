package com.terminalfour.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.common.base.Optional;
import com.terminalfour.xmlservicejdbc.core.Constants;

public class XmlQueryHelper {

    public static List<Map<String, Object>> getTablesFromXml(XmlObject xmlObject) {

        List<Map<String, Object>> data = new ArrayList<>();

        for (String name : xmlObject.getUniqueElementNames()) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ELEMENT_NAME_COLUMN_NAME, name);
            data.add(map);
        }

        return data;

    }

    public static List<Map<String, Object>> handleSelectQuery(String sqlQuery) throws SQLException {

        List<Map<String, Object>> data = new ArrayList<>();

        Optional<String> tableNameWrapper = getTableNameFromQuery(sqlQuery);
        if (!tableNameWrapper.isPresent())
            throw new SQLException("Unable to get table name from sqlQuery: " + sqlQuery);

        Optional<List<String>> columnNamesWrapper = getColumnNamesFromQuery(sqlQuery);
        if (!columnNamesWrapper.isPresent())
            throw new SQLException("Unable to get column names from sqlQuery: " + sqlQuery);

        return data;

    }

    private static Optional<List<String>> getColumnNamesFromQuery(String sqlQuery) {

        if (!sqlQuery.startsWith(Constants.SQL_KEYWORD_SELECT))
            return Optional.absent();

        if (!sqlQuery.contains(Constants.SQL_KEYWORD_FROM))
            return Optional.absent();

        String stringToExplode = sqlQuery.substring(Constants.SQL_KEYWORD_SELECT.length(), sqlQuery.indexOf(Constants.SQL_KEYWORD_FROM));
        stringToExplode = stringToExplode.trim();

        List<String> data = new ArrayList<>(); 
        
        String[] tokens = stringToExplode.split(",");
        for(String token: tokens){
            data.add(token.trim());
        }

        if(data.size() > 0)
            return Optional.of(data);
        
        return Optional.absent();

    }

    public static Optional<String> getTableNameFromQuery(String sqlQuery) {

        StringTokenizer tokens = new StringTokenizer(sqlQuery);

        boolean lastTokenEqualsFrom = false;
        while (tokens.hasMoreElements()) {
            if (lastTokenEqualsFrom)
                return Optional.of(tokens.nextToken());

            if (tokens.nextToken().toLowerCase().equals(Constants.SQL_KEYWORD_FROM.toLowerCase()))
                lastTokenEqualsFrom = true;
        }

        return Optional.absent();

    }

}
