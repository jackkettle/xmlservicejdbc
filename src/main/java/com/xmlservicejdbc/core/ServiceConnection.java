package com.xmlservicejdbc.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.dom4j.DocumentException;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.xmlservicejdbc.core.xml.SavedResponseProvider;

public class ServiceConnection extends Unused implements Connection {

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

    public DatabaseMetaData getMetaData() throws SQLException {
        return new ServiceDatabaseMetaData();
    }

    public Statement createStatement() throws SQLException {
        return new ServiceStatement();
    }

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ServiceConnection.class);

}
