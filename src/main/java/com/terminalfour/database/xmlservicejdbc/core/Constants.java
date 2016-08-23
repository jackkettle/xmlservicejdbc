/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle
 * Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

import java.util.Arrays;
import java.util.List;

import com.terminalfour.database.xmlservicejdbc.core.objects.Keyword;

public class Constants {
    
    public static final String TABLE_NAME = "TABLE_NAME";
    
    public static final String COLUMN_NAME = "COLUMN_NAME";
    
    public static final String SQL_DATE_FORMAT = "YYYY-MM-DD HH:MM:SS";

	public static final String ATTRIBUTE_DELIMITER = "$";

	public static final String NAMESPACE_DELIMITER = ":";
	
	public static final List<Keyword> CONDITIONAL_KEYWORDS = Arrays.asList(Keyword.WHERE, Keyword.OR, Keyword.AND);
	
	public static final List<String> CONDITIONAL_OPERATORS = Arrays.asList("=", "!=", "<>", "<=", ">=", "<", ">", "!<", "!>");

}
