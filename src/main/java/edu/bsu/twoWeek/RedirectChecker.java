package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.scene.control.Alert;

public class RedirectChecker {
    public void checkForRedirects(JsonObject query){
        if (query.has("redirects")) {
            JsonArray array = query.getAsJsonArray("redirects");
            String to = array.get(array.size() - 1).getAsJsonObject().get("to").getAsString();
            String from = array.get(0).getAsJsonObject().get("from").getAsString();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("You have been redirected");
            alert.setContentText("You were redirected from: " + from + " to " + to);
            alert.setResizable(false);
            alert.showAndWait();
        }
    }
}
