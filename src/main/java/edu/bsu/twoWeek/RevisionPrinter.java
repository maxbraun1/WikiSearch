package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.time.Instant;


public class RevisionPrinter {

    public String printRevisions(JsonArray revisionsArray){
        StringBuilder output = new StringBuilder("-- " + revisionsArray.size() + " revisions returned --\n");
        for(int i = 0; i < revisionsArray.size(); i++) {
            JsonObject revision = revisionsArray.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();
            Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());
            output.append(String.format("%-30s - %s\n", username, timestamp));
        }
        return output.toString();
    }
}
