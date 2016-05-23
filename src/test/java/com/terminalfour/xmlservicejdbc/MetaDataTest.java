package com.terminalfour.xmlservicejdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminalfour.xmlservicejdbc.core.Constants;
import com.terminalfour.xmlservicejdbc.core.JDBC;

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

        logger.info("Class: {}", meta.getClass());
    }

    @After
    public void close() throws SQLException {
        meta = null;
        conn.close();
    }

    @Test
    public void getTables() throws Exception {
        logger.info("Test case: getTables()");
        ResultSet rs = meta.getTables(null, null, null, null);
        logger.info("Resultset: {}", rs.toString());

        int indexCheck = 0;
        while (rs.next()) {
            indexCheck++;
            logger.info("Element name: {}", rs.getString(Constants.ELEMENT_NAME_COLUMN_NAME));
            if (indexCheck > 100)
                break;
        }

    }

    private static final Logger logger = LoggerFactory.getLogger(MetaDataTest.class);

}
