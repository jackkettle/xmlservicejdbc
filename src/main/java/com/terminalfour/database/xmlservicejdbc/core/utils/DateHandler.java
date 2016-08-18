package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.terminalfour.database.xmlservicejdbc.core.Constants;

public class DateHandler {

	public static void handleDateFormatElements (List<Map<String, Object>> data, String dateElementsStatement, String dateFormatStatement)
			throws SQLException {

		Optional<List<String>> dateElements = getValues (dateElementsStatement);
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

	public static Optional<List<String>> getValues (String statement) {

		if (Strings.isNullOrEmpty (statement))
			return Optional.absent ();

		List<String> tableNameList = new ArrayList<> ();
		StringTokenizer tokens = new StringTokenizer (statement, ",");

		while (tokens.hasMoreElements ()) {
			String token = tokens.nextToken ().trim ();
			tableNameList.add (token);
		}

		return Optional.of (tableNameList);

	}

}
