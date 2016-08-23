/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.terminalfour.database.xmlservicejdbc.core.Constants;
import com.terminalfour.database.xmlservicejdbc.core.handlers.ConditionHandler;
import com.terminalfour.database.xmlservicejdbc.core.handlers.DateHandler;
import com.terminalfour.database.xmlservicejdbc.core.handlers.MarkupHandler;
import com.terminalfour.database.xmlservicejdbc.core.handlers.QueryHandler;
import com.terminalfour.database.xmlservicejdbc.core.objects.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.objects.KeywordStatementPair;
import com.terminalfour.database.xmlservicejdbc.core.utils.KeywordStatementPairUtils;
import com.terminalfour.database.xmlservicejdbc.core.utils.LoggingUtils;
import com.terminalfour.database.xmlservicejdbc.core.utils.QueryUtils;
import com.terminalfour.database.xmlservicejdbc.core.utils.SqlQueryUtils;

public class XmlQueryHelper {

	public static List<Map<String, Object>> getTablesFromXml (XmlObject xmlObject) {

		List<Map<String, Object>> data = new ArrayList<> ();

		for (String name : xmlObject.getUniqueElementNames ()) {
			Map<String, Object> map = new HashMap<> ();
			map.put (Constants.TABLE_NAME, name);
			data.add (map);
		}

		return data;
	}

	public static List<Map<String, Object>> handleSelectQuery (String sqlQuery)
			throws SQLException {

		logger.debug ("Handling select query: {}", sqlQuery);

		if (!SavedResponseProvider.isSet ())
			throw new SQLException ("The connection is not set");

		Optional<List<String>> tableNameWrapper = SqlQueryUtils.getTableNamesFromQuery (sqlQuery);
		if (!tableNameWrapper.isPresent ())
			throw new SQLException ("Unable to get table name from sqlQuery: " + sqlQuery);

		Optional<List<String>> columnNamesWrapper = SqlQueryUtils.getColumnNamesFromQuery (sqlQuery);
		if (!columnNamesWrapper.isPresent () || columnNamesWrapper.get ().size () == 0)
			throw new SQLException ("Unable to get column names from sqlQuery: " + sqlQuery);

		if (columnNamesWrapper.get ().size () > 1)
			throw new SQLException ("Too many tables specified for simple query");

		boolean getAllColumnNames = false;
		List<String> columnNames = columnNamesWrapper.get ();
		if (columnNames.size () == 1) {
			if (columnNames.get (0).equals ("*"))
				getAllColumnNames = true;
		}

		List<Element> elements = new ArrayList<> ();
		elements = SavedResponseProvider.getXmlObject ().getAllElementsByName (tableNameWrapper.get ().get (0));

		List<KeywordStatementPair> actionList = QueryHandler.getActionList (sqlQuery);
		List<KeywordStatementPair> duplicateActionList = KeywordStatementPairUtils.filter (actionList, Arrays.asList (Keyword.DUPLICATE_IDENTIFIER));
		if (duplicateActionList.size () > 0) {
			sanitizeDataConditionally (duplicateActionList, elements);
		}

		List<Map<String, Object>> data = new ArrayList<> ();
		if (getAllColumnNames)
			data = QueryUtils.getAllColumnValues (elements);
		else
			data = QueryUtils.getColumnValues (elements, columnNamesWrapper.get ());

		logger.debug ("Total rows found: {}", data.size ());
		logger.debug ("Updating rows with missing columns");
		sanitizeData (data);

		handleDateAction (actionList, data);

		handleDecodeAction (actionList, data);

		LoggingUtils.logColumns (data);

		return data;
	}

	private static void sanitizeDataConditionally (List<KeywordStatementPair> conditionActionList, List<Element> elements) throws SQLException {

		
		for (KeywordStatementPair action : conditionActionList) {
			logger.debug ("Removing duplicate elements based on statement: {}", action.getStatement ());
		}
		
		for (Element element : elements) {

			processElementDuplicateActions (element, conditionActionList);
		}

	}

	private static void processElementDuplicateActions (Element element, List<KeywordStatementPair> conditionActionList) throws SQLException {

		for (KeywordStatementPair action : conditionActionList) {
			ConditionHandler.removeDuplicateElementsBasedOnActions(element, action.getStatement ());
		}
	}

	private static void handleDecodeAction (List<KeywordStatementPair> actionList, List<Map<String, Object>> data)
			throws SQLException {
		Optional<KeywordStatementPair> decodeMarkupAction = KeywordStatementPairUtils.getFirst (actionList, Keyword.DECODE);
		if (decodeMarkupAction.isPresent ()) {
			MarkupHandler.handleDecodeElements (data, decodeMarkupAction.get ().getStatement ());
		}
	}

	private static void handleDateAction (List<KeywordStatementPair> actionList, List<Map<String, Object>> data)
			throws SQLException {
		Optional<KeywordStatementPair> dateElement = KeywordStatementPairUtils.getFirst (actionList, Keyword.DATE_ELEMENT);
		Optional<KeywordStatementPair> dateFormat = KeywordStatementPairUtils.getFirst (actionList, Keyword.DATE_FORMAT);
		if (dateElement.isPresent () && dateFormat.isPresent ()) {
			DateHandler.handleDateFormatElements (data, dateElement.get ().getStatement (), dateFormat.get ().getStatement ());
		}
	}

	private static void sanitizeData (List<Map<String, Object>> data) {

		Set<String> allColumnNames = new HashSet<> ();

		for (Map<String, Object> rowColumns : data) {
			allColumnNames.addAll (rowColumns.keySet ());
		}

		logger.debug ("Total columns that should be in each row: {}", allColumnNames.size ());

		Collection<String> missingColumns;
		for (int i = 0; i < data.size (); i++) {
			missingColumns = Collections2.filter (allColumnNames, Predicates.not (Predicates.in (data.get (i).keySet ())));
			if (missingColumns.isEmpty ())
				continue;

			logger.debug ("Total missing columns found in row {}: {}", i + 1, missingColumns.size ());

			for (String missingColumn : missingColumns) {
				data.get (i).put (missingColumn, "");
			}
		}

	}

	public static List<Map<String, Object>> getColumnNames (String table)
			throws SQLException {

		if (!SavedResponseProvider.isSet ())
			throw new SQLException ("The connection is not set");

		List<Element> elements = new ArrayList<> ();
		elements = SavedResponseProvider.getXmlObject ().getAllElementsByName (table);

		return QueryUtils.getColumnNames (elements);
	}

	private static final Logger logger = LoggerFactory.getLogger (XmlQueryHelper.class);

}
