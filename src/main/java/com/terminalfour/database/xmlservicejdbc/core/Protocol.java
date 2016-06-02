/*
 * (C) 2016 TERMINALFOUR Solutions Ltd.
 *
 * Author: Paul Kelly
 * Created: 30 May 2016
 */
package com.terminalfour.database.xmlservicejdbc.core;

public enum Protocol {
    HTTP("http://"),
    HTTPS("https://"),
    FILE("file:/"),
    INVALID("");

    private String initial;

    Protocol (final String initial) {
        this.initial = initial;
    }

    public static Protocol getProtocol (final String url) {
        if (url == null)
            return INVALID;

        String lUrl = url.toLowerCase ();
        for (Protocol next : values ()) {
            if (lUrl.startsWith (next.initial))
                return next;
        }

		return INVALID;
	}
}
