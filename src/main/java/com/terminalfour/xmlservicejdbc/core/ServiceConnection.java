package com.terminalfour.xmlservicejdbc.core;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.dom4j.DocumentException;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class ServiceConnection implements Connection {

    public ServiceConnection(String urlString) throws SQLException {

        urlString = urlString.replace(JDBC.PREFIX, "");

        if (Strings.isNullOrEmpty(urlString))
            return;

        if (!Utils.hasValidProtocol(urlString)) {
            throw new SQLException("Invalid protocol (only file, http, https supported) on url: {}", urlString);
        }

        Optional<String> responseDataWrapper = Optional.absent();
        if (Utils.getProtocolType(urlString) != Constants.FILE_PROTOCAL)
            responseDataWrapper = Utils.getStringFromUrl(urlString);
        else
            responseDataWrapper = Utils.getStringFromFile(urlString);

        if (!responseDataWrapper.isPresent())
            throw new SQLException("No data recieved from url: {}", urlString);

        try {
            SavedResponseProvider.setResponseString(responseDataWrapper.get());
        } catch (DocumentException e) {
            throw new SQLException("Unable to convert response from url to xmlObject: {}", urlString);
        }
        
        logger.info("Connection successfully established to {}", urlString);

    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public Statement createStatement() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public String nativeSQL(String sql) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        // TODO Auto-generated method stub

    }

    public boolean getAutoCommit() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public void commit() throws SQLException {
        // TODO Auto-generated method stub

    }

    public void rollback() throws SQLException {
        // TODO Auto-generated method stub

    }

    public void close() throws SQLException {
        // TODO Auto-generated method stub

    }

    public boolean isClosed() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public MetaData getMetaData() throws SQLException {
        return new MetaData();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        // TODO Auto-generated method stub

    }

    public boolean isReadOnly() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setCatalog(String catalog) throws SQLException {
        // TODO Auto-generated method stub

    }

    public String getCatalog() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTransactionIsolation(int level) throws SQLException {
        // TODO Auto-generated method stub

    }

    public int getTransactionIsolation() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    public SQLWarning getWarnings() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void clearWarnings() throws SQLException {
        // TODO Auto-generated method stub

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        // TODO Auto-generated method stub

    }

    public void setHoldability(int holdability) throws SQLException {
        // TODO Auto-generated method stub

    }

    public int getHoldability() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    public Savepoint setSavepoint() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        // TODO Auto-generated method stub

    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        // TODO Auto-generated method stub

    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Clob createClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Blob createBlob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public NClob createNClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public SQLXML createSQLXML() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isValid(int timeout) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        // TODO Auto-generated method stub

    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        // TODO Auto-generated method stub

    }

    public String getClientInfo(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void setSchema(String schema) throws SQLException {
        // TODO Auto-generated method stub

    }

    public String getSchema() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public void abort(Executor executor) throws SQLException {
        // TODO Auto-generated method stub

    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    public int getNetworkTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceConnection.class);

}
