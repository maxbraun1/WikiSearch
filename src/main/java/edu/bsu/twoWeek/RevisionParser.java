package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RevisionParser {

    public String parseRevisions(JsonArray revisionsArray){
        String output = "-- "+revisionsArray.size()+" revisions returned --\n";
        for(int i = 0; i < revisionsArray.size(); i++) {
            JsonObject revision = revisionsArray.get(i).getAsJsonObject();
            String username = revision.get("user").getAsString();
            Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());
            output += String.format("%-30s - %s\n", username, timestamp);
        }

        return output;

        /*

            System.out.println(revisionsArray.size() + " revisionsArray returned.");
            for(int i = 0; i < revisionsArray.size(); i++){
                JsonObject revision = revisionsArray.get(i).getAsJsonObject();
                String username = revision.get("user").getAsString();
                Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());
                revisions.add(new Revision(username,timestamp));
            }
            return revisions;
        }else{
            System.out.println("It doesn't exist");
        }

         */
    }
}
