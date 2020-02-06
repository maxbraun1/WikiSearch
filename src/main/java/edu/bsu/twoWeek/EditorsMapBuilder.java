package edu.bsu.twoWeek;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditorsMapBuilder {
    public Map<String,Integer> buildEditorsMap(JsonArray revisionsArray){
        Map<String, Integer> editors = new LinkedHashMap<String,Integer>(); // <username, revisions>
        for(int i = 0; i < revisionsArray.size(); i++){
            JsonObject revision = revisionsArray.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();
            Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());

            if(editors.containsKey(username)){
                Integer number = editors.get(username) + 1;
                editors.put(username,number);
            }else{
                editors.put(username,1);
            }
        }
        return editors;
    }
}
