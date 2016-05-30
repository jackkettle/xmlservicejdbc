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

        if (!sqlQuery.contains(Constants.SQL_KEYWORD_FROM))
            throw new SQLException("Invlaid sql statemenet, A select statement must contain the FROM keyword");

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

    // TODO Integrate this into above method
    public static List<Map<String, Object>> handleAdvancedSelectQuery(String sqlQuery) throws SQLException {

        if (!SavedResponseProvider.isSet())
            throw new SQLException("The connection is not set");

        if (!sqlQuery.contains(Constants.SQL_KEYWORD_FROM))
            throw new SQLException("Invlaid sql statemenet, A select statement must contain the FROM keyword");

        Optional<List<String>> tableNameWrapper = getTableNamesFromQuery(sqlQuery);
        if (!tableNameWrapper.isPresent())
            throw new SQLException("Unable to get any table names from sqlQuery: " + sqlQuery);

        boolean multipleTables = false;
        if (tableNameWrapper.get().size() > 1)
            multipleTables = true;

        Optional<List<String>> columnNamesWrapper = getColumnNamesFromQuery(sqlQuery);
        if (!columnNamesWrapper.isPresent())
            throw new SQLException("Unable to get column names from sqlQuery: " + sqlQuery);

        boolean getAllColumnNames = false;
        List<String> columnNames = columnNamesWrapper.get();
        if (columnNames.size() == 1) {
            if (columnNames.get(0).equals("*"))
                getAllColumnNames = true;
        }

        if (multipleTables && getAllColumnNames) {
            throw new SQLException("You must specify column names when selecting from multiple tables.");
        }

        List<Element> elements = null;
        if (getAllColumnNames) {
            elements = SavedResponseProvider.getXmlObject().getAllElementsByName(tableNameWrapper.get().get(0));
        }

        Map<String, List<Element>> elementMap = new HashMap<>();
        for (String tableName : tableNameWrapper.get()) {
            List<Element> tempElements = SavedResponseProvider.getXmlObject().getAllElementsByName(tableName);
            elementMap.put(tableName, tempElements);
        }

        List<Map<String, Object>> data = new ArrayList<>();
        if (getAllColumnNames)
            data = XmlQueryUtils.getAllColumnValues(elements);
        else
            data = XmlQueryUtils.getColumnValuesAdvanced(columnNamesWrapper.get(), elementMap);

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

    private static Optional<List<String>> getTableNamesFromQuery(String sqlQuery) throws SQLException {

        sqlQuery = sqlQuery.trim().toLowerCase();

        int index = sqlQuery.indexOf(Constants.SQL_KEYWORD_FROM.toLowerCase());
        if (index < 0) {
            System.out.println("Breakpint 1");
            return Optional.absent();
        }
        sqlQuery = sqlQuery.substring(index + Constants.SQL_KEYWORD_FROM.length());
        sqlQuery = sqlQuery.trim();
        System.out.println(sqlQuery);

        StringTokenizer stringTokenizer = new StringTokenizer(sqlQuery, " ");
        StringBuilder Stringbuilder = new StringBuilder();
        while (stringTokenizer.hasMoreElements()) {
            String element = stringTokenizer.nextElement().toString();
            if (isSQLKeyword(element))
                break;
            Stringbuilder.append(element);
            Stringbuilder.append(" ");
        }

        List<String> tableNames = new ArrayList<>();
        stringTokenizer = new StringTokenizer(Stringbuilder.toString().trim(), ",");
        while (stringTokenizer.hasMoreElements()) {
            String tableName = stringTokenizer.nextToken().trim();
            tableName = tableName.trim();
            if (tableName.contains(" "))
                throw new SQLException("Invalid sql query, space in table name: " + tableName);
            tableNames.add(tableName);
        }

        return Optional.absent();
    }

    private static boolean isSQLKeyword(String element) {
        element = element.trim().toUpperCase();

        if (element.equals(Constants.SQL_KEYWORD_FROM))
            return true;

        if (element.equals(Constants.SQL_KEYWORD_SELECT))
            return true;

        if (element.equals(Constants.SQL_KEYWORD_WHERE))
            return true;

        if (element.equals(Constants.SQL_KEYWORD_AS))
            return true;

        return false;
    }
}
