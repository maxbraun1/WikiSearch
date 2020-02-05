package edu.bsu.twoWeek;


import org.junit.jupiter.api.Test;

import java.io.InputStream;
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

            WikiConnectionCreator urlBuilder = new WikiConnectionCreator();
            InputStream in = urlBuilder.buildURL("soup");
            RevisionParser revisionParser = new RevisionParser();
            revisionParser.parseRevisions(in);
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
        }
    }
}
