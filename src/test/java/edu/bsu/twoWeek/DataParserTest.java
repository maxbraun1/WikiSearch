package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DataParserTest {
    @Test
    public void testParseData()throws Exception{
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("soupRevisions.json");
        assert stream != null;
        Reader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        JsonArray expected = element.getAsJsonArray();
        WikiConnectionCreator creator = new WikiConnectionCreator();
        DataParser parser = new DataParser();
        InputStream stream2 = creator.createConnection("soup");
        JsonArray actual = parser.parseData(stream2);
        Assertions.assertEquals(expected, actual);
    }
}
