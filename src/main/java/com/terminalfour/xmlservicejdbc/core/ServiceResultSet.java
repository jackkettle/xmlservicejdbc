package com.terminalfour.xmlservicejdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceResultSet extends Unused implements ResultSet {

    private final ServiceStatment statement;

    boolean open = false;
    String[] cols = null;
    String[] colsMeta = null;
    boolean[][] meta = null;

    private int row = 1;

    ServiceResultSet(ServiceStatment statement) {
        this.statement = statement;
    }

    public boolean next() throws SQLException {
        row++;
        return true;
    }

    boolean isOpen() {
        return open;
    }

    public void close() throws SQLException {
        cols = null;
        open = false;
        limitRows = 0;
        row = 1;
        lastCol = -1;

        if (statement == null)
            return;

    }

}
