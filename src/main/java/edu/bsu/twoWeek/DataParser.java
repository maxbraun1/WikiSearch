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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(!object.has("batchcomplete")) {
            JsonObject query = object.getAsJsonObject("query");
            if (query.has("redirects")) {
                JsonArray array = query.getAsJsonArray("redirects");
                String to = array.get(array.size() - 1).getAsJsonObject().get("to").getAsString();
                String from = array.get(0).getAsJsonObject().get("from").getAsString();
                alert.setTitle("Alert");
                alert.setHeaderText("You have been redirected");
                alert.setContentText("You were redirected from: " + from + " to " + to);
                alert.setResizable(false);
                alert.showAndWait();
            }
            JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
            JsonObject pageIDNumberObject = pages.entrySet().iterator().next().getValue().getAsJsonObject();
            JsonArray revisionsArray = pageIDNumberObject.getAsJsonArray("revisions");
            return revisionsArray;
        }else{
            return null;
        }
    }
}
