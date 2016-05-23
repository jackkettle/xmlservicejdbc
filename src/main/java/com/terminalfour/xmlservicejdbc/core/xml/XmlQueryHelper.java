package com.terminalfour.xmlservicejdbc.core.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.terminalfour.xmlservicejdbc.core.Constants;

public class XmlQueryHelper {

    public static List<Map<String, Object>> getTablesFromXml(XmlObject xmlObject) {

        List<Map<String, Object>> data = new ArrayList<>();

        for (String name : xmlObject.getUniqueElementNames()) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.ELEMENT_NAME_COLUMN_NAME, name);
            data.add(map);
        }

        return data;

    }

}
