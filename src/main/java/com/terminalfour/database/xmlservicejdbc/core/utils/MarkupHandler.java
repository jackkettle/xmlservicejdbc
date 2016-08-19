package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.common.base.Optional;

public class MarkupHandler {

	public static String decodeHtml (String html) {
		return StringEscapeUtils.unescapeHtml4 (html);
	}

	public static void handleDecodeElements (List<Map<String, Object>> data, String statement)
			throws SQLException {

		Optional<List<String>> elements = QueryHandler.getCsvValues (statement);
		if (!elements.isPresent () || elements.get ().size () < 1) {
			throw new SQLException ("Failed to get date elements form statement: " + statement);
		}

		for (Map<String, Object> rowColumns : data) {
			for (Entry<String, Object> entry : rowColumns.entrySet ()) {

				if (!elements.get ().contains (entry.getKey ()))
					continue;

				entry.setValue (decodeHtml (entry.getValue ().toString ()));

			}
		}

	}

}
