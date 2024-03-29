package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LearningTest {
    @Test
    public void testFindJsonInTestResourcesFolder() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("soup.json");
        Assertions.assertNotNull(stream);
    }

    @Test
    public void testParseJsonRevisionsValue() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("soup.json");
        assert stream != null;
        Reader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        JsonObject object = element.getAsJsonObject();
        JsonObject continueObject = object.getAsJsonObject("query").getAsJsonObject("pages").getAsJsonObject("19651298");
        JsonArray array = continueObject.getAsJsonArray("revisions");
        for(int i = 0; i < array.size(); i++){
            System.out.println("Name:" + array.get(i).getAsJsonObject().get("user").getAsString());
            System.out.println("Timestamp:" + array.get(i).getAsJsonObject().get("timestamp").getAsString());
        }
    }
}
