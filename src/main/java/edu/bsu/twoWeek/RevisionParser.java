package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class RevisionParser {
    public String parse(InputStream stream){
        Reader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        JsonObject object = element.getAsJsonObject();
        JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray array = null;

        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            array = entryObject.getAsJsonArray("revisions");

        }
        for(int i = 0; i < array.size(); i++){
            System.out.println("Name:" + array.get(i).getAsJsonObject().get("user").getAsString());
            System.out.println("Timestamp:" + array.get(i).getAsJsonObject().get("timestamp").getAsString());
        }
        return null;
    }
}
