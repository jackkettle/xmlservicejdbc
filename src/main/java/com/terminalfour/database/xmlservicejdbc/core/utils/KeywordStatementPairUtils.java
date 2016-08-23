/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 20 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.Constants;
import com.terminalfour.database.xmlservicejdbc.core.objects.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.objects.KeywordStatementPair;
import com.terminalfour.database.xmlservicejdbc.core.objects.Operator;

public class KeywordStatementPairUtils {

	public static Optional<KeywordStatementPair> getFirst (List<KeywordStatementPair> actionList, Keyword keyword) {

		for (KeywordStatementPair keywordStatementPair : actionList) {
			if (keywordStatementPair.getKeyword ().equals (keyword))
				return Optional.of (keywordStatementPair);
		}
		return Optional.absent ();
	}

	public static List<KeywordStatementPair> getAll (List<KeywordStatementPair> actionList, Keyword keyword) {
		List<KeywordStatementPair> filteredActionList = new ArrayList<> ();
		for (KeywordStatementPair keywordStatementPair : actionList) {
			if (keywordStatementPair.getKeyword ().equals (keyword))
				filteredActionList.add (keywordStatementPair);
		}
		return filteredActionList;
	}

	public static List<KeywordStatementPair> filter (List<KeywordStatementPair> actionList, List<Keyword> keywords) {

		List<KeywordStatementPair> filteredActionList = new ArrayList<> ();

		for (KeywordStatementPair keywordStatementPair : actionList) {
			if (keywords.contains (keywordStatementPair.getKeyword ()))
				filteredActionList.add (keywordStatementPair);
		}
		return filteredActionList;
	}

	public static Optional<String> getColumnName (String statemenet) {

		for (Operator operator : Operator.values ()) {
			if (statemenet.contains (operator.toString ())) {
				return Optional.of (statemenet.split (operator.toString ())[0].trim ());
			}
		}
		return Optional.absent ();

	}

	public static Optional<Operator> getOperator (String statemenet) {

		for (Operator operator : Operator.values ()) {
			if (statemenet.contains (operator.toString ())) {
				return Optional.of (operator);
			}
		}
		return Optional.absent ();

	}

	public static Optional<String> getValue (String statemenet) {

		String[] array = new String[] {};
		for (Operator operator : Operator.values ()) {
			if (statemenet.contains (operator.toString ())) {
				array = statemenet.split (operator.toString ());
			}

			if (array.length > 1) {
				return Optional.of (array[1]);
			}
		}
		return Optional.absent ();

	}

	public static String getNamespace (String input) {
		if (input.contains (Constants.NAMESPACE_DELIMITER))
			return input.substring (0, input.indexOf (Constants.NAMESPACE_DELIMITER));

		return "";
	}

	public static String removeNamespace (String input) {

		if (input.contains (Constants.NAMESPACE_DELIMITER)) {
			int index = input.indexOf (Constants.NAMESPACE_DELIMITER);

			if (index == input.length ())
				return input.substring (index, input.length ());

			return input.substring (index + 1, input.length ());
		}
		return input;
	}

	public static String getElement (String input) {
		input = removeAttribute (input);
		return removeNamespace (input);
	}

	public static String getAttribute (String input) {

		if (input.contains (Constants.ATTRIBUTE_DELIMITER)) {
			int index = input.indexOf (Constants.ATTRIBUTE_DELIMITER);

			if (index == input.length ())
				return "";

			return input.substring (index + 1);
		}
		return "";
	}

	public static String removeAttribute (String input) {

		if (input.contains (Constants.ATTRIBUTE_DELIMITER))
			return input.substring (0, input.indexOf (Constants.ATTRIBUTE_DELIMITER));

		return input;
	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (KeywordStatementPairUtils.class);

}
