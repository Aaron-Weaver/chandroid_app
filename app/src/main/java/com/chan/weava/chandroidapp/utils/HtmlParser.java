package com.chan.weava.chandroidapp.utils;

import org.jsoup.Jsoup;

/**
 * HTML Parser
 *
 * Utility class made to convert HTML to plain text in strings.
 *
 * @author Aaron Weaver         (waaronl@okstate.edu)
 * @version ForeChanApp v0.1A
 * @since 11/16/14
 */
public class HtmlParser
{
    public static String parseHtml(String html)
    {
        if(html != null)
        {
            return Jsoup.parse(html).text();
        }
        else
        {
            return "";
        }
    }
}
