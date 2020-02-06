package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class DataParser {
    public JsonArray parseData(InputStream stream){
        Reader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        JsonObject object = element.getAsJsonObject();
        if(!object.has("batchcomplete")) {
            RedirectChecker checker = new RedirectChecker();
            JsonObject query = object.getAsJsonObject("query");
            checker.checkForRedirects(query);
            JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
            JsonObject pageIDNumberObject = pages.entrySet().iterator().next().getValue().getAsJsonObject();
            JsonArray revisionsArray = pageIDNumberObject.getAsJsonArray("revisions");
            return revisionsArray;
        }else{
            return null;
        }
    }
}
