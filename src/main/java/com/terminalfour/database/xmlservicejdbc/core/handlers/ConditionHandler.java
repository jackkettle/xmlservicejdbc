/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 23 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.handlers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.objects.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.objects.KeywordStatementPair;
import com.terminalfour.database.xmlservicejdbc.core.objects.Operator;
import com.terminalfour.database.xmlservicejdbc.core.utils.KeywordStatementPairUtils;
import com.terminalfour.database.xmlservicejdbc.core.utils.QueryUtils;
import com.terminalfour.database.xmlservicejdbc.core.utils.XmlUtils;

public class ConditionHandler {

	public static void testConditionalActionList (List<KeywordStatementPair> conditionActionList)
			throws SQLException {
		Optional<KeywordStatementPair> whereWrapper = KeywordStatementPairUtils.getFirst (conditionActionList, Keyword.WHERE);
		Optional<KeywordStatementPair> andWrapper = KeywordStatementPairUtils.getFirst (conditionActionList, Keyword.AND);
		Optional<KeywordStatementPair> orWrapper = KeywordStatementPairUtils.getFirst (conditionActionList, Keyword.OR);

		if (whereWrapper.isPresent () && andWrapper.isPresent () && orWrapper.isPresent ()) {
			throw new SQLException ("Conditional statements containing both AND and OR keywords are not yet supported");
		}

		if (!whereWrapper.isPresent () && (andWrapper.isPresent () || orWrapper.isPresent ())) {
			throw new SQLException ("There must be a WHERE clause to warrant the AND / OR keywords");
		}
	}

	public static void removeDuplicateElementsBasedOnActions (Element element, String statement)
			throws SQLException {

		Optional<String> fullColumnNameWrapper = KeywordStatementPairUtils.getColumnName (statement);
		Optional<Operator> operatorWrapper = KeywordStatementPairUtils.getOperator (statement);;
		Optional<String> valueWrapper = KeywordStatementPairUtils.getValue (statement);;

		if (!fullColumnNameWrapper.isPresent () || !operatorWrapper.isPresent () || !valueWrapper.isPresent ()) {
			throw new SQLException ("The conditional statement is invalid: " + statement);
		}

		List<Element> elementsToRemove = new ArrayList<> ();
		for (Iterator<?> i = element.elementIterator (); i.hasNext ();) {
			Element childElement = (Element)i.next ();

			if (!XmlUtils.elementMatches (childElement, fullColumnNameWrapper.get ())) {
				continue;
			}

			Optional<String> value = QueryUtils.getValueFromElement (childElement, fullColumnNameWrapper.get ());

			if (!value.isPresent ())
				continue;

			if (!processCondition (operatorWrapper.get (), valueWrapper.get (), value.get ())) {
				elementsToRemove.add (childElement);
			}

		}

		logger.debug ("Total elements to remove: {}", elementsToRemove.size ());

		for (Element elementToRemove : elementsToRemove) {
			element.remove (elementToRemove);
		}

	}

	private static boolean processCondition (Operator operator, String statedValue, String foundValue)
			throws SQLException {

		logger.debug ("Processing condition: {}, {}, {}", operator.name (), statedValue, foundValue);

		if (Operator.EQUAL == operator) {
			return statedValue.trim ().equals (foundValue.trim ());
		}

		// TODO: Handle more conditional operators
		throw new SQLException ("Only the EQAUL operator is supported currently");
	}

	private static final Logger logger = LoggerFactory.getLogger (ConditionHandler.class);

}
