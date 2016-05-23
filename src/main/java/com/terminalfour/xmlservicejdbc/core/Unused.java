package com.terminalfour.xmlservicejdbc.core;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

abstract class Unused {
    
    private SQLException unused() {
        return new SQLException("not implemented by XmlService JDBC driver");
    }

    /***********************************
     ************ ResultSet ************
     ***********************************/

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        unused();
        return false;
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        unused();
        return null;
    }

    public boolean absolute(int row) throws SQLException {
        unused();
        return false;
    }

    public void afterLast() throws SQLException {
        unused();

    }

    public void beforeFirst() throws SQLException {
        unused();

    }

    public void cancelRowUpdates() throws SQLException {
        unused();

    }

    public void clearWarnings() throws SQLException {
        unused();

    }

    public void close() throws SQLException {
        unused();

    }

    public void deleteRow() throws SQLException {
        unused();

    }

    public int findColumn(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public boolean first() throws SQLException {
        unused();
        return false;
    }

    public Array getArray(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Array getArray(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        unused();
        return null;
    }

    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        unused();
        return null;
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Blob getBlob(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        unused();
        return false;
    }

    public boolean getBoolean(String columnLabel) throws SQLException {
        unused();
        return false;
    }

    public byte getByte(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public byte getByte(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public byte[] getBytes(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Reader getCharacterStream(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Clob getClob(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Clob getClob(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public int getConcurrency() throws SQLException {
        unused();
        return 0;
    }

    public String getCursorName() throws SQLException {
        unused();
        return null;
    }

    public Date getDate(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Date getDate(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public double getDouble(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public double getDouble(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public int getFetchDirection() throws SQLException {
        unused();
        return 0;
    }

    public int getFetchSize() throws SQLException {
        unused();
        return 0;
    }

    public float getFloat(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public float getFloat(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public int getHoldability() throws SQLException {
        unused();
        return 0;
    }

    public int getInt(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public int getInt(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public long getLong(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public long getLong(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        unused();
        return null;
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public String getNString(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public String getNString(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Object getObject(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Object getObject(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        unused();
        return null;
    }

    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        unused();
        return null;
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        unused();
        return null;
    }

    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        unused();
        return null;
    }

    public Ref getRef(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Ref getRef(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public int getRow() throws SQLException {
        unused();
        return 0;
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public short getShort(int columnIndex) throws SQLException {
        unused();
        return 0;
    }

    public short getShort(String columnLabel) throws SQLException {
        unused();
        return 0;
    }

    public Statement getStatement() throws SQLException {
        unused();
        return null;
    }

    public String getString(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public String getString(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Time getTime(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Time getTime(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        unused();
        return null;
    }

    public int getType() throws SQLException {
        unused();
        return 0;
    }

    public URL getURL(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public URL getURL(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        unused();
        return null;
    }

    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        unused();
        return null;
    }

    public SQLWarning getWarnings() throws SQLException {
        unused();
        return null;
    }

    public void insertRow() throws SQLException {
        unused();

    }

    public boolean isAfterLast() throws SQLException {
        unused();
        return false;
    }

    public boolean isBeforeFirst() throws SQLException {
        unused();
        return false;
    }

    public boolean isClosed() throws SQLException {
        unused();
        return false;
    }

    public boolean isFirst() throws SQLException {
        unused();
        return false;
    }

    public boolean isLast() throws SQLException {
        unused();
        return false;
    }

    public boolean last() throws SQLException {
        unused();
        return false;
    }

    public void moveToCurrentRow() throws SQLException {
        unused();

    }

    public void moveToInsertRow() throws SQLException {
        unused();

    }

    public boolean next() throws SQLException {
        unused();
        return false;
    }

    public boolean previous() throws SQLException {
        unused();
        return false;
    }

    public void refreshRow() throws SQLException {
        unused();

    }

    public boolean relative(int rows) throws SQLException {
        unused();
        return false;
    }

    public boolean rowDeleted() throws SQLException {
        unused();
        return false;
    }

    public boolean rowInserted() throws SQLException {
        unused();
        return false;
    }

    public boolean rowUpdated() throws SQLException {
        unused();
        return false;
    }

    public void setFetchDirection(int direction) throws SQLException {
        unused();

    }

    public void setFetchSize(int rows) throws SQLException {
        unused();

    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        unused();

    }

    public void updateArray(String columnLabel, Array x) throws SQLException {
        unused();

    }

    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        unused();

    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        unused();

    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        unused();

    }

    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        unused();

    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        unused();

    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        unused();

    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        unused();

    }

    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        unused();

    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        unused();

    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        unused();

    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        unused();

    }

    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        unused();

    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        unused();

    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        unused();

    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        unused();

    }

    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        unused();

    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        unused();

    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        unused();

    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        unused();

    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        unused();

    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        unused();

    }

    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        unused();

    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        unused();

    }

    public void updateByte(String columnLabel, byte x) throws SQLException {
        unused();

    }

    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        unused();

    }

    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        unused();

    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        unused();

    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        unused();

    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        unused();

    }

    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        unused();

    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        unused();

    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        unused();

    }

    public void updateClob(String columnLabel, Clob x) throws SQLException {
        unused();

    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        unused();

    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        unused();

    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        unused();

    }

    public void updateDate(String columnLabel, Date x) throws SQLException {
        unused();

    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        unused();

    }

    public void updateDouble(String columnLabel, double x) throws SQLException {
        unused();

    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        unused();

    }

    public void updateFloat(String columnLabel, float x) throws SQLException {
        unused();

    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        unused();

    }

    public void updateInt(String columnLabel, int x) throws SQLException {
        unused();

    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        unused();

    }

    public void updateLong(String columnLabel, long x) throws SQLException {
        unused();

    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        unused();

    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        unused();

    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        unused();

    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        unused();

    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        unused();

    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        unused();

    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        unused();

    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        unused();

    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        unused();

    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        unused();

    }

    public void updateNull(int columnIndex) throws SQLException {
        unused();

    }

    public void updateNull(String columnLabel) throws SQLException {
        unused();

    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        unused();

    }

    public void updateObject(String columnLabel, Object x) throws SQLException {
        unused();

    }

    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        unused();

    }

    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        unused();

    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        unused();

    }

    public void updateRef(String columnLabel, Ref x) throws SQLException {
        unused();

    }

    public void updateRow() throws SQLException {
        unused();

    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        unused();

    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        unused();

    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        unused();

    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        unused();

    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        unused();

    }

    public void updateShort(String columnLabel, short x) throws SQLException {
        unused();

    }

    public void updateString(int columnIndex, String x) throws SQLException {
        unused();

    }

    public void updateString(String columnLabel, String x) throws SQLException {
        unused();

    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        unused();

    }

    public void updateTime(String columnLabel, Time x) throws SQLException {
        unused();

    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        unused();

    }

    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        unused();

    }

    public boolean wasNull() throws SQLException {
        unused();
        return false;
    }
    
    /*
     * */

    
    public boolean allProceduresAreCallable() throws SQLException {
        unused();
        return false;
    }

    
    public boolean allTablesAreSelectable() throws SQLException {
        unused();
        return false;
    }

    
    public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
        unused();
        return false;
    }

    
    public boolean dataDefinitionCausesTransactionCommit() throws SQLException {
        unused();
        return false;
    }

    
    public boolean dataDefinitionIgnoredInTransactions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean deletesAreDetected(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean doesMaxRowSizeIncludeBlobs() throws SQLException {
        unused();
        return false;
    }

    
    public boolean generatedKeyAlwaysReturned() throws SQLException {
        unused();
        return false;
    }

    
    public ResultSet getAttributes(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getBestRowIdentifier(String arg0, String arg1, String arg2, int arg3, boolean arg4) throws SQLException {
        unused();
        return null;
    }

    
    public String getCatalogSeparator() throws SQLException {
        unused();
        return null;
    }

    
    public String getCatalogTerm() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getCatalogs() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getClientInfoProperties() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getColumnPrivileges(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public Connection getConnection() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getCrossReference(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) throws SQLException {
        unused();
        return null;
    }

    
    public int getDatabaseMajorVersion() throws SQLException {
        unused();
        return 0;
    }

    
    public int getDatabaseMinorVersion() throws SQLException {
        unused();
        return 0;
    }

    
    public String getDatabaseProductName() throws SQLException {
        unused();
        return null;
    }

    
    public String getDatabaseProductVersion() throws SQLException {
        unused();
        return null;
    }

    
    public int getDefaultTransactionIsolation() throws SQLException {
        unused();
        return 0;
    }

    
    public int getDriverMajorVersion() {
        unused();
        return 0;
    }

    
    public int getDriverMinorVersion() {
        unused();
        return 0;
    }

    
    public String getDriverName() throws SQLException {
        unused();
        return null;
    }

    
    public String getDriverVersion() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getExportedKeys(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public String getExtraNameCharacters() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getFunctionColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getFunctions(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public String getIdentifierQuoteString() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getImportedKeys(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getIndexInfo(String arg0, String arg1, String arg2, boolean arg3, boolean arg4) throws SQLException {
        unused();
        return null;
    }

    
    public int getJDBCMajorVersion() throws SQLException {
        unused();
        return 0;
    }

    
    public int getJDBCMinorVersion() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxBinaryLiteralLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxCatalogNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxCharLiteralLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnsInGroupBy() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnsInIndex() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnsInOrderBy() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnsInSelect() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxColumnsInTable() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxConnections() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxCursorNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxIndexLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxProcedureNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxRowSize() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxSchemaNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxStatementLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxStatements() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxTableNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxTablesInSelect() throws SQLException {
        unused();
        return 0;
    }

    
    public int getMaxUserNameLength() throws SQLException {
        unused();
        return 0;
    }

    
    public String getNumericFunctions() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getPrimaryKeys(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getProcedureColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public String getProcedureTerm() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getProcedures(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getPseudoColumns(String arg0, String arg1, String arg2, String arg3) throws SQLException {
        unused();
        return null;
    }

    
    public int getResultSetHoldability() throws SQLException {
        unused();
        return 0;
    }

    
    public RowIdLifetime getRowIdLifetime() throws SQLException {
        unused();
        return null;
    }

    
    public String getSQLKeywords() throws SQLException {
        unused();
        return null;
    }

    
    public int getSQLStateType() throws SQLException {
        unused();
        return 0;
    }

    
    public String getSchemaTerm() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getSchemas() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getSchemas(String arg0, String arg1) throws SQLException {
        unused();
        return null;
    }

    
    public String getSearchStringEscape() throws SQLException {
        unused();
        return null;
    }

    
    public String getStringFunctions() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getSuperTables(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getSuperTypes(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public String getSystemFunctions() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getTablePrivileges(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getTableTypes() throws SQLException {
        unused();
        return null;
    }

    
    public String getTimeDateFunctions() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getTypeInfo() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getUDTs(String arg0, String arg1, String arg2, int[] arg3) throws SQLException {
        unused();
        return null;
    }

    
    public String getURL() throws SQLException {
        unused();
        return null;
    }

    
    public String getUserName() throws SQLException {
        unused();
        return null;
    }

    
    public ResultSet getVersionColumns(String arg0, String arg1, String arg2) throws SQLException {
        unused();
        return null;
    }

    
    public boolean insertsAreDetected(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean isCatalogAtStart() throws SQLException {
        unused();
        return false;
    }

    
    public boolean isReadOnly() throws SQLException {
        unused();
        return false;
    }

    
    public boolean locatorsUpdateCopy() throws SQLException {
        unused();
        return false;
    }

    
    public boolean nullPlusNonNullIsNull() throws SQLException {
        unused();
        return false;
    }

    
    public boolean nullsAreSortedAtEnd() throws SQLException {
        unused();
        return false;
    }

    
    public boolean nullsAreSortedAtStart() throws SQLException {
        unused();
        return false;
    }

    
    public boolean nullsAreSortedHigh() throws SQLException {
        unused();
        return false;
    }

    
    public boolean nullsAreSortedLow() throws SQLException {
        unused();
        return false;
    }

    
    public boolean othersDeletesAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean othersInsertsAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean othersUpdatesAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean ownDeletesAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean ownInsertsAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean ownUpdatesAreVisible(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesLowerCaseIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesLowerCaseQuotedIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesMixedCaseIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesMixedCaseQuotedIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesUpperCaseIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean storesUpperCaseQuotedIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsANSI92EntryLevelSQL() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsANSI92FullSQL() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsANSI92IntermediateSQL() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsAlterTableWithAddColumn() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsAlterTableWithDropColumn() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsBatchUpdates() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCatalogsInDataManipulation() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCatalogsInIndexDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCatalogsInPrivilegeDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCatalogsInProcedureCalls() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCatalogsInTableDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsColumnAliasing() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsConvert() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsConvert(int arg0, int arg1) throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCoreSQLGrammar() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsCorrelatedSubqueries() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsDataManipulationTransactionsOnly() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsDifferentTableCorrelationNames() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsExpressionsInOrderBy() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsExtendedSQLGrammar() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsFullOuterJoins() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsGetGeneratedKeys() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsGroupBy() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsGroupByBeyondSelect() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsGroupByUnrelated() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsIntegrityEnhancementFacility() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsLikeEscapeClause() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsLimitedOuterJoins() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMinimumSQLGrammar() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMixedCaseIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMixedCaseQuotedIdentifiers() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMultipleOpenResults() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMultipleResultSets() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsMultipleTransactions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsNamedParameters() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsNonNullableColumns() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOpenCursorsAcrossCommit() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOpenCursorsAcrossRollback() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOpenStatementsAcrossCommit() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOpenStatementsAcrossRollback() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOrderByUnrelated() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsOuterJoins() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsPositionedDelete() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsPositionedUpdate() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsResultSetConcurrency(int arg0, int arg1) throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsResultSetHoldability(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsResultSetType(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSavepoints() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSchemasInDataManipulation() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSchemasInIndexDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSchemasInPrivilegeDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSchemasInProcedureCalls() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSchemasInTableDefinitions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSelectForUpdate() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsStatementPooling() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsStoredProcedures() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSubqueriesInComparisons() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSubqueriesInExists() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSubqueriesInIns() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsSubqueriesInQuantifieds() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsTableCorrelationNames() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsTransactionIsolationLevel(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsTransactions() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsUnion() throws SQLException {
        unused();
        return false;
    }

    
    public boolean supportsUnionAll() throws SQLException {
        unused();
        return false;
    }

    
    public boolean updatesAreDetected(int arg0) throws SQLException {
        unused();
        return false;
    }

    
    public boolean usesLocalFilePerTable() throws SQLException {
        unused();
        return false;
    }

    
    public boolean usesLocalFiles() throws SQLException {
        unused();
        return false;
    }
    

}
