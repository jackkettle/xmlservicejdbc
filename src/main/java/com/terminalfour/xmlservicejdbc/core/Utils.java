package com.terminalfour.xmlservicejdbc.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class Utils {

    @SuppressWarnings("resource")
    public static Optional<String> getStringFromFile(String urlString) {
        BufferedReader bufferedReader = null;
        try {
            URI uri = new URI(urlString);
            File file = new File(uri);
            bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            return Optional.of(stringBuilder.toString());
        } catch (URISyntaxException | IOException e) {
            logger.error("Failed to get data from file uri: {}", urlString, e);
            return Optional.absent();
        }

    }

    public static Optional<String> getStringFromUrl(String urlString) {
        String body = "";
        try {
            URL url = new URL(urlString);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (IOException e) {
            logger.error("Failed to get string from url: {}", urlString, e);
            return Optional.absent();
        }
        return Optional.of(body);
    }

    public static Optional<String> getStringFromUrl(URL url) {
        String body = "";
        try {
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (IOException e) {
            logger.error("Failed to get string from url: {}", url.toString(), e);
            return Optional.absent();
        }
        return Optional.of(body);
    }

    public static int getProtocolType(String urlString) {
        urlString = urlString.trim();
        if (urlString.toLowerCase().startsWith(HTTP_PROTOCAL_SNIPPET.toLowerCase()))
            return Constants.HTTP_PROTOCAL;
        if (urlString.toLowerCase().startsWith(HTTPS_PROTOCAL_SNIPPET.toLowerCase()))
            return Constants.HTTPS_PROTOCAL;
        if (urlString.toLowerCase().startsWith(FILE_PROTOCAL_SNIPPET.toLowerCase()))
            return Constants.FILE_PROTOCAL;

        return Constants.INVALID_PROTOCAL;
    }

    public static boolean hasValidProtocol(String urlString) {
        int type = getProtocolType(urlString);
        return type == 1 || type == 2 || type == 3;

    }

    private static final String HTTP_PROTOCAL_SNIPPET = "http://";

    private static final String HTTPS_PROTOCAL_SNIPPET = "https://";

    private static final String FILE_PROTOCAL_SNIPPET = "file:/";

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Utils.class);

}
