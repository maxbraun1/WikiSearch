package edu.bsu.twoWeek;


import com.google.gson.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class RevisonParserTest {

    @Test
    public void testParse() throws IOException {
        try {
            URL url = new URL("https://en.wikipedia.org/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Connection Successful");

            WikiUrlBuilder urlBuilder = new WikiUrlBuilder();
            InputStream in = urlBuilder.buildURL("dog");
            RevisionParser revisionParser = new RevisionParser();
            revisionParser.parse(in);
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
        }
    }
}
