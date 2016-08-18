package com.terminalfour.database.xmlservicejdbc.core.utils;

import java.util.List;

import com.google.common.base.Optional;
import com.terminalfour.database.xmlservicejdbc.core.Keyword;
import com.terminalfour.database.xmlservicejdbc.core.KeywordStatementPair;

public class KeywordStatementPairUtils {

	public static Optional<KeywordStatementPair> get (List<KeywordStatementPair> actionList, Keyword keyword) {

		for (KeywordStatementPair keywordStatementPair : actionList) {
			if (keywordStatementPair.getKeyword ().equals (keyword))
				return Optional.of (keywordStatementPair);
		}
		return Optional.absent ();
	}

}
