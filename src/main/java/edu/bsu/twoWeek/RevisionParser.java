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
        JsonArray revisions = null;

        for (Map.Entry<String, JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisions = entryObject.getAsJsonArray("revisions");

        }

        System.out.println(revisions.size() + " revisions returned.");

        for(int i = 0; i < revisions.size(); i++){
            JsonObject revision = revisions.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();
            String timestamp = revision.get("timestamp").getAsString();
            System.out.println("Revision #"+(i+1)+": "+username+" - "+timestamp);
            //System.out.println("Name:" + revisions.get(i).getAsJsonObject().get("user").getAsString());
            //System.out.println("Timestamp:" + revisions.get(i).getAsJsonObject().get("timestamp").getAsString());
        }
        return null;
    }
}
