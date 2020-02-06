package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;


public class EditorSorterTest {
    @Test
    public void testSortEditors()throws Exception{
        EditorSorter sorter = new EditorSorter();
        DataParser parser = new DataParser();

        InputStream streamFromResourceFolder = Thread.currentThread().getContextClassLoader().getResourceAsStream("soupOneRevision.json");
        assert streamFromResourceFolder != null;
        Reader reader = new InputStreamReader(streamFromResourceFolder);
        JsonElement element = JsonParser.parseReader(reader);
        JsonArray array = element.getAsJsonArray();
        String expected = sorter.sortEditors(array);

        String search = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=soup&rvprop=timestamp|user&rvlimit=1&redirects";
        URL url = new URL(search);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Fa20; msbraun@bsu.edu)");
        InputStream streamFromWiki = connection.getInputStream();
        JsonArray array2 = parser.parseData(streamFromWiki);
        String actual = sorter.sortEditors(array2);

        Assertions.assertEquals(expected, actual);
    }
}
