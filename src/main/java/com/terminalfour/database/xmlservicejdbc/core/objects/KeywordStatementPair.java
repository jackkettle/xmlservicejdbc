/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 20 August 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.objects;

public class KeywordStatementPair {

	private Keyword keyword;
	
	private String statement;

	public KeywordStatementPair () {
		super ();
		this.keyword = null;
		this.statement = "";
	}

	public KeywordStatementPair (Keyword keyword) {
		super ();
		this.keyword = keyword;
	}

	
	public KeywordStatementPair (Keyword keyword, String statement) {
		super ();
		this.keyword = keyword;
		this.statement = statement;
	}

	public Keyword getKeyword () {
		return keyword;
	}

	
	public void setKeyword (Keyword keyword) {
		this.keyword = keyword;
	}

	
	public String getStatement () {
		return statement;
	}

	
	public void setStatement (String statement) {
		this.statement = statement;
	}
	
	@Override
	public String toString () {
		return "KeywordStatementPair [keyword=" + keyword + ", statement=" + statement + "]";
	}
	
}
