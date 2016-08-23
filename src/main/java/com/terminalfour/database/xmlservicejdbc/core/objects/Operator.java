/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 23 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.objects;

import java.util.StringTokenizer;

import com.google.common.base.Optional;

public enum Operator {

	NOT_EQUAL("!="),
	GREATER_THAN_OR_EQUAL(">="),
	LESS_THAN_OR_EQUAL("<="),
	EQUAL("="),
	GREATER_THAN(">"),
	LESS_THAN("<");

	private String initial;

	Operator () {
		this.initial = "";
	}

	Operator (final String initial) {
		this.initial = initial;
	}

	public static Optional<Operator> getKeyword (final String token) {
		if (token == null)
			return Optional.absent ();

		String tokenUpdated = token.trim ().toUpperCase ();
		for (Operator next : values ()) {
			if (tokenUpdated.equals (next.initial))
				return Optional.of (next);
		}

		return Optional.absent ();
	}

	public static boolean containsKeyword (final String urlString) {
		return containsKeyword (urlString, null);
	}

	public static boolean containsKeyword (final String urlString, Operator keyword) {
		if (urlString == null)
			return false;

		StringTokenizer tokens = new StringTokenizer (urlString);

		while (tokens.hasMoreTokens ()) {
			String token = tokens.nextToken ();

			Optional<Operator> keywordWrapper = getKeyword (token);
			if (!keywordWrapper.isPresent ())
				continue;

			if (keyword == null || keyword == keywordWrapper.get ())
				return true;

		}

		return false;
	}

	public static Optional<Operator> getFirstKeyword (final String urlString) {
		if (urlString == null)
			return Optional.absent ();

		StringTokenizer tokens = new StringTokenizer (urlString);

		while (tokens.hasMoreTokens ()) {
			String token = tokens.nextToken ();

			Optional<Operator> keywordWrapper = getKeyword (token);
			if (!keywordWrapper.isPresent ())
				continue;

			return keywordWrapper;
		}

		return Optional.absent ();
	}

	public static boolean isEnum (final String token) {
		return getKeyword (token).isPresent ();
	}

	public String toString () {
		return this.initial;
	}
}
