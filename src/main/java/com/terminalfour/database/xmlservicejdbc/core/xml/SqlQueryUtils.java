package com.terminalfour.database.xmlservicejdbc.core.xml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.Keyword;

public class SqlQueryUtils {

	public static Optional<List<String>> getColumnNamesFromQuery (String sqlQuery) throws SQLException {

		Keyword keyword = null;

		if (sqlQuery.toUpperCase ().startsWith (Keyword.SELECT.toString ()))
			keyword = Keyword.SELECT;

		if (sqlQuery.toUpperCase ().startsWith (Keyword.ADAVANCED_SELECT.toString ()))			
			keyword = Keyword.ADAVANCED_SELECT;

		if (keyword == null)
			return Optional.absent ();

		int beginningIndex = keyword.toString ().length ();
		
		String sqlQuerySubString = sqlQuery.substring (beginningIndex);
		
		Optional<Keyword> keywordWrapper = Keyword.getFirstKeyword (sqlQuerySubString);
		
		if(!keywordWrapper.isPresent () || keywordWrapper.get () != Keyword.FROM)
			throw new SQLException("Missing from keyword");
		
		int endIndex = sqlQuery.toUpperCase ().indexOf (Keyword.FROM.toString ());
		
		logger.info ("{} - {}", beginningIndex, endIndex);

		String stringToExplode = sqlQuery.substring (beginningIndex, endIndex);
		stringToExplode = stringToExplode.trim ();

		List<String> data = new ArrayList<> ();

		String[] tokens = stringToExplode.split (",");
		for (String token : tokens) {
			data.add (token.trim ());
		}

		if (data.size () > 0)
			return Optional.of (data);

		return Optional.absent ();
	}

	public static Optional<List<String>> getTableNamesFromQuery (String sqlQuery) {

		int beginningIndex = sqlQuery.indexOf (Keyword.FROM.toString ());
		beginningIndex += Keyword.FROM.toString ().length ();
		String snippt = sqlQuery.substring (beginningIndex);

		StringTokenizer tokens = new StringTokenizer (snippt);
		StringBuilder stringBuilder = new StringBuilder ();

		while (tokens.hasMoreElements ()) {
			String token = tokens.nextToken ();
			if (Keyword.getKeyword (token).isPresent ())
				break;

			stringBuilder.append (token);
		}

		if (stringBuilder.length () == 0)
			return Optional.absent ();

		List<String> tableNameList = new ArrayList<> ();
		tokens = new StringTokenizer (stringBuilder.toString (), ",");

		while (tokens.hasMoreElements ()) {
			String token = tokens.nextToken ().trim ();
			if (Keyword.getKeyword (token).isPresent ())
				break;

			tableNameList.add (token);
		}

		return Optional.of (tableNameList);

	}

	private static final Logger logger = LoggerFactory.getLogger (SqlQueryUtils.class);

	
}
