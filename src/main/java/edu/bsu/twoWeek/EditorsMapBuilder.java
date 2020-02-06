package edu.bsu.twoWeek;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class EditorsMapBuilder {
    public Map<String,Integer> buildEditorsMap(JsonArray revisionsArray){
        Map<String, Integer> editors = new LinkedHashMap<>(); // <username, revisions>
        for(int i = 0; i < revisionsArray.size(); i++){
            JsonObject revision = revisionsArray.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();

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
