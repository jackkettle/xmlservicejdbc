package com.terminalfour.xmlservicejdbc.core.misc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;


public class Test {

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

        }
        System.out.println(body);
        
    }

}
