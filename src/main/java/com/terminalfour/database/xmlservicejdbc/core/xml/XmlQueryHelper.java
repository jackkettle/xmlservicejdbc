package com.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.dom4j.Element;

import com.google.common.base.Optional;
import com.xmlservicejdbc.core.Constants;

public class XmlQueryHelper {

    public static List<Map<String, Object>> getTablesFromXml(XmlObject xmlObject) {

        List<Map<String, Object>> data = new ArrayList<>();

        for (String name : xmlObject.getUniqueElementNames()) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.TABLE_NAME, name);
            data.add(map);
        }

        return data;
    }

    public static List<Map<String, Object>> handleSelectQuery(String sqlQuery) throws SQLException {

        if (!SavedResponseProvider.isSet())
            throw new SQLException("The connection is not set");

        Optional<String> tableNameWrapper = getTableNameFromQuery(sqlQuery);
        if (!tableNameWrapper.isPresent())
            throw new SQLException("Unable to get table name from sqlQuery: " + sqlQuery);

        Optional<List<String>> columnNamesWrapper = getColumnNamesFromQuery(sqlQuery);
        if (!columnNamesWrapper.isPresent())
            throw new SQLException("Unable to get column names from sqlQuery: " + sqlQuery);

        boolean getAllColumnNames = false;
        List<String> columnNames = columnNamesWrapper.get();
        if (columnNames.size() == 1) {
            if (columnNames.get(0).equals("*"))
                getAllColumnNames = true;
        }

        List<Element> elements = new ArrayList<>();
        elements = SavedResponseProvider.getXmlObject().getAllElementsByName(tableNameWrapper.get());

        List<Map<String, Object>> data = new ArrayList<>();
        if (getAllColumnNames)
            data = XmlQueryUtils.getAllColumnValues(elements);
        else
            data = XmlQueryUtils.getColumnValues(elements, columnNamesWrapper.get());

        return data;
    }

    public static List<Map<String, Object>> getColumnNames(String table) throws SQLException {

        if (!SavedResponseProvider.isSet())
            throw new SQLException("The connection is not set");

        List<Element> elements = new ArrayList<>();
        elements = SavedResponseProvider.getXmlObject().getAllElementsByName(table);

        return XmlQueryUtils.getColumnNames(elements);
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
        for (String token : tokens) {
            data.add(token.trim());
        }

        if (data.size() > 0)
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
