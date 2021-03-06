/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 02 June 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.objects;

import java.util.StringTokenizer;

import com.google.common.base.Optional;

public enum Keyword {

	SELECT("SELECT"),
	ADAVANCED_SELECT("ADVANCED_SELECT"),
	FROM("FROM"),
	WHERE("WHERE"),
	AND("AND"),
	OR("OR"),
	PARENT("PARENT"),
	DATE_ELEMENT("DATE_ELEMENT"),
	DATE_FORMAT("DATE_FORMAT"),
	DECODE("DECODE"),
	DUPLICATE_IDENTIFIER("DUPLICATE_IDENTIFIER");


	private String initial;

	Keyword () {
		this.initial = "";
	}
	
	Keyword (final String initial) {
		this.initial = initial;
	}

	public static Optional<Keyword> getKeyword (final String token) {
		if (token == null)
			return Optional.absent ();

		String tokenUpdated = token.trim ().toUpperCase ();
		for (Keyword next : values ()) {
			if (tokenUpdated.equals (next.initial))
				return Optional.of (next);
		}

		return Optional.absent ();
	}

	public static boolean containsKeyword (final String urlString) {
		return containsKeyword (urlString, null);
	}

	public static boolean containsKeyword (final String urlString, Keyword keyword) {
		if (urlString == null)
			return false;

		StringTokenizer tokens = new StringTokenizer (urlString);

		while (tokens.hasMoreTokens ()) {
			String token = tokens.nextToken ();

			Optional<Keyword> keywordWrapper = getKeyword (token);
			if (!keywordWrapper.isPresent ())
				continue;

			if (keyword == null || keyword == keywordWrapper.get ())
				return true;

		}

		return false;
	}

	public static Optional<Keyword> getFirstKeyword (final String urlString) {
		if (urlString == null)
			return Optional.absent ();
		
		StringTokenizer tokens = new StringTokenizer (urlString);
		
		while (tokens.hasMoreTokens ()) {
			String token = tokens.nextToken ();
			
			Optional<Keyword> keywordWrapper = getKeyword (token);
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
