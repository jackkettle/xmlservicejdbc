package com.terminalfour.xmlservicejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.terminalfour.xmlservicejdbc.core.JDBC;

public class ConnectionTest {

    @BeforeClass
    public static void forName() throws Exception {
        Class.forName(JDBC.class.getCanonicalName());
    }
    
    @Test
    public void openConnectionEmpty() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:xmlservice:");
        conn.close();
    }

    @Test
    public void openConnectionValidURL() throws SQLException, ClassNotFoundException {
        Connection conn = DriverManager.getConnection("jdbc:xmlservice:https://democe.campusit.net/app/democe/solar.qll_web.curriculum?id=00124303&auth=demokey");
        conn.close();
    }
    
    @Test
    public void openConnectionValidFile() throws SQLException, ClassNotFoundException {
        
        String fileURI = getClass().getResource("/testResponse.xml").toString();
        Connection conn = DriverManager.getConnection("jdbc:xmlservice:" + fileURI);
        conn.close();
    } 
    
}
