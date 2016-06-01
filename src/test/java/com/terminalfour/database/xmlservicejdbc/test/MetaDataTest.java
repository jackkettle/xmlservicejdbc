package com.xmlservicejdbc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Strings;
import com.xmlservicejdbc.core.Constants;
import com.xmlservicejdbc.core.JDBC;

public class MetaDataTest {

    private Connection conn;

    private DatabaseMetaData meta;

    @BeforeClass
    public static void forName() throws Exception {
        Class.forName(JDBC.class.getCanonicalName());
    }

    @Before
    public void connect() throws Exception {
        String fileURI = getClass().getResource("/testResponse.xml").toString();
        conn = DriverManager.getConnection("jdbc:xmlservice:" + fileURI);
        meta = conn.getMetaData();
    }

    @After
    public void close() throws SQLException {
        meta = null;
        conn.close();
    }

    @Test
    public void getTables() throws Exception {
        ResultSet rs = meta.getTables(null, null, null, null);
        int indexCheck = 0;
        while (rs.next()) {
            indexCheck++;
            if (Strings.isNullOrEmpty(rs.getString(Constants.TABLE_NAME))) {
                throw new Exception();
            }
        }
        assertTrue(indexCheck == 7);
    }

}
