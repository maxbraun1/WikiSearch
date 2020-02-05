package edu.bsu.twoWeek;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class EditorParserTest {
    @Test
    public void testParse() throws IOException {
        try {
            URL url = new URL("https://en.wikipedia.org/");
            URLConnection connection = url.openConnection();
            connection.connect();

            System.out.println("Connection Successful");

            WikiUrlBuilder urlBuilder = new WikiUrlBuilder();
            InputStream in = urlBuilder.buildURL("soup");
            EditorParser editorParser = new EditorParser();
            editorParser.parseEditors(in);
        }
        catch (Exception e) {
            System.out.println("Internet Not Connected");
        }
    }
}
