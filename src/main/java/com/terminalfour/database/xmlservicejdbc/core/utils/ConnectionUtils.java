/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Jack Kettle Created: 31 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core.utils;

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

import com.google.common.base.Optional;

public class ConnectionUtils {

	@SuppressWarnings("resource")
	public static Optional<String> getStringFromFile (String urlString) {
		BufferedReader bufferedReader = null;
		try {
			URI uri = new URI (urlString);
			File file = new File (uri);
			bufferedReader = new BufferedReader (new FileReader (file));
			StringBuilder stringBuilder = new StringBuilder ();
			String line;
			while ((line = bufferedReader.readLine ()) != null) {
				stringBuilder.append (line);
				stringBuilder.append (System.lineSeparator ());
			}
			return Optional.of (stringBuilder.toString ());
		}
		catch (URISyntaxException | IOException e) {
			return Optional.absent ();
		}

	}

	public static Optional<String> getStringFromUrl (String urlString) {
		String body = "";
		try {
			URL url = new URL (urlString);
			URLConnection con = url.openConnection ();
			InputStream in = con.getInputStream ();
			String encoding = con.getContentEncoding ();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = IOUtils.toString (in, encoding);
		}
		catch (IOException e) {
			return Optional.absent ();
		}
		return Optional.of (body);
	}

	public static Optional<String> getStringFromUrl (URL url) {
		String body = "";
		try {
			URLConnection con = url.openConnection ();
			InputStream in = con.getInputStream ();
			String encoding = con.getContentEncoding ();
			encoding = encoding == null ? "UTF-8" : encoding;
			body = IOUtils.toString (in, encoding);
		}
		catch (IOException e) {
			return Optional.absent ();
		}
		return Optional.of (body);
	}

}
