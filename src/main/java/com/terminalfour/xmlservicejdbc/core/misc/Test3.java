package com.terminalfour.xmlservicejdbc.core.misc;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Optional;
import com.terminalfour.xmlservicejdbc.core.Constants;

public class Test3 {

    public static void main(String [] args){
        
        String sqlQuery = "SELECT name, id, blah FROM tableNames";
        
        if (!sqlQuery.startsWith(Constants.SQL_KEYWORD_SELECT)){
            return;
        }

        if (!sqlQuery.contains(Constants.SQL_KEYWORD_FROM)){
            return;
        }

        String stringToExplode = sqlQuery.substring(Constants.SQL_KEYWORD_SELECT.length(), sqlQuery.indexOf(Constants.SQL_KEYWORD_FROM));

        System.out.println(stringToExplode.trim());
        
        List<String> columnNames = new ArrayList<>();
    }
    
}
