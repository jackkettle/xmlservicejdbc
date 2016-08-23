/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 22 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.handlers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.terminalfour.database.xmlservicejdbc.core.Constants;

public class DateHandler {

	public static void handleDateFormatElements (List<Map<String, Object>> data, String dateElementsStatement, String dateFormatStatement)
			throws SQLException {

		Optional<List<String>> dateElements = QueryHandler.getCsvValues (dateElementsStatement);
		if (!dateElements.isPresent () || dateElements.get ().isEmpty ()) {
			throw new SQLException ("Failed to get date elements from sql: " + dateElementsStatement);
		}

		if (Strings.isNullOrEmpty (dateFormatStatement)) {
			throw new SQLException ("Failed to get date format from sql: " + dateFormatStatement);
		}

		SimpleDateFormat foundFormat = new SimpleDateFormat (dateFormatStatement);
		SimpleDateFormat newFormat = new SimpleDateFormat (Constants.SQL_DATE_FORMAT);

		for (Map<String, Object> rowColumns : data) {
			for (Entry<String, Object> entry : rowColumns.entrySet ()) {

				if (!dateElements.get ().contains (entry.getKey ()))
					continue;

				Date date;
				try {
					date = foundFormat.parse (entry.getValue ().toString ());
				}
				catch (ParseException e) {
					throw new SQLException ("Failed to parse value of: " + entry.getValue ().toString ());
				}
				entry.setValue (newFormat.format (date));
			}
		}

	}

}
