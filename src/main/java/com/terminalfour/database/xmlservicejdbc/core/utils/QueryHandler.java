package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminalfour.database.xmlservicejdbc.core.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.KeywordStatementPair;

public class QueryHandler {

	public static List<KeywordStatementPair> getActionList (String sql) {

		List<KeywordStatementPair> actionList = new ArrayList<> ();

		KeywordStatementPair keywordStatementPair = new KeywordStatementPair ();
		StringBuilder stringBuilder = new StringBuilder ();
		Keyword keyword = null;
		Keyword previousKeyword = null;
		String[] tokens = sql.split (" ");

		for (int i = 0; i < tokens.length; i++) {

			String token = tokens[i];

			boolean isKeyword = Keyword.isEnum (token);
			boolean isLast = (i == tokens.length - 1);

			if (isKeyword) {

				previousKeyword = keyword;
				keyword = Keyword.getKeyword (token).get ();

				keywordStatementPair.setStatement (stringBuilder.toString ().trim ());
				stringBuilder = new StringBuilder ();

				boolean isFirst = (previousKeyword == null);
				if (isFirst) {
					keywordStatementPair.setKeyword (keyword);
				}
				else if (isLast) {
					keywordStatementPair.setKeyword (previousKeyword);
					actionList.add (keywordStatementPair);
					keywordStatementPair = new KeywordStatementPair ();
					keywordStatementPair.setKeyword (keyword);
					actionList.add (keywordStatementPair);
				}
				else {
					keywordStatementPair.setKeyword (previousKeyword);
					actionList.add (keywordStatementPair);
					keywordStatementPair = new KeywordStatementPair ();
				}
				continue;
			}

			stringBuilder.append (token);
			stringBuilder.append (" ");

			if (isLast) {
				keywordStatementPair.setKeyword (keyword);
				keywordStatementPair.setStatement (stringBuilder.toString ().trim ());
				actionList.add (keywordStatementPair);
			}

		}

		return actionList;

	}

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger (QueryHandler.class);

}
