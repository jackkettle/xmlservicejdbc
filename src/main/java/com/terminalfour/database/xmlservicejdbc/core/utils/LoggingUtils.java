/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 20 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {

	public static void logColumns (List<Map<String, Object>> data) {
		logger.info ("\n{}", getLogColumns (data));
	}

	public static void logData (List<Map<String, Object>> data) {
		logger.info ("\n{}", getLogData (data));
	}

	public static String getLogColumns (List<Map<String, Object>> data) {
		for (Map<String, Object> dataRow : data) {
			return getDataHeader (dataRow);
		}
		return "";
	}

	public static String getLogData (List<Map<String, Object>> data) {
		StringBuilder str = new StringBuilder ();
		boolean first = true;
		for (Map<String, Object> dataRow : data) {
			if (first) {
				first = false;
				str.append (getDataHeader (dataRow));
			}
			str.append (getRowValues (dataRow));
		}
		return str.toString ();
	}

	private static Object getRowValues (Map<String, Object> dataRow) {
		StringBuilder str = new StringBuilder ();
		Iterator<Entry<String, Object>> entries = dataRow.entrySet ().iterator ();
		while (entries.hasNext ()) {
			Entry<String, Object> thisEntry = entries.next ();
			String value = (String)thisEntry.getValue ();
			str.append (value);
			if (entries.hasNext ())
				str.append (", ");
			else
				str.append ("\n");
		}
		return str.toString ();
	}

	private static String getDataHeader (Map<String, Object> dataRow) {

		StringBuilder str = new StringBuilder ();

		Iterator<Entry<String, Object>> entries = dataRow.entrySet ().iterator ();
		while (entries.hasNext ()) {
			Entry<String, Object> thisEntry = entries.next ();
			String key = (String)thisEntry.getKey ();
			str.append (key);
			if (entries.hasNext ())
				str.append (", ");
			else
				str.append ("\n");
		}

		return str.toString ();
	}

	private static final Logger logger = LoggerFactory.getLogger (LoggingUtils.class);

}
