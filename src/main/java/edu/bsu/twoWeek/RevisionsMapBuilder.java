package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class RevisionsMapBuilder {
    public Map<String,Instant> buildRevisionsMap(JsonArray revisionsArray){
        Map<String, Instant> revisions = new LinkedHashMap<>(); // <username, timestamp>
        for(int i = 0; i < revisionsArray.size(); i++){
            JsonObject revision = revisionsArray.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();
            Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());

            if(!revisions.containsKey(username)){
                revisions.put(username,timestamp);
            }
        }
        return revisions;
    }
}
