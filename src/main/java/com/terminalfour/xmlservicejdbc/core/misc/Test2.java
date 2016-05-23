package com.terminalfour.xmlservicejdbc.core.misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import com.terminalfour.xmlservicejdbc.core.XmlObject;

public class Test2 {

    public static void main(String[] args) {
        String urlString = "https://democe.campusit.net/app/democe/solar.qll_web.curriculum?id=00124303&auth=demokey";
        URL url;
        String body = "";
        try {
            url = new URL(urlString);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (IOException e) {
            logger.info("Unable to get response from url: {}", urlString, e);
            return;
        }

        XmlObject xmlObject = null;
        try {
            xmlObject = new XmlObject(body);
        } catch (DocumentException e) {
            logger.error("Unable to convert response data to xml object", e);
            return;
        }
 
        for(String name: xmlObject.getUniqueElementNames()){
            logger.info("Name: {}", name);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Test2.class);

}
