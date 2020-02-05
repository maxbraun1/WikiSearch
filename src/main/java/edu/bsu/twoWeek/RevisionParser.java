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

import static sun.tools.java.Type.tString;

public class RevisionParser {

    public void parseRevisions(InputStream stream){
        Reader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        JsonObject object = element.getAsJsonObject();
        if(!object.has("batchcomplete")){
            JsonObject query = object.getAsJsonObject("query");
            if(query.has("redirects")){
                JsonArray array = query.getAsJsonArray("redirects");
                String to = array.get(array.size()-1).getAsJsonObject().get("to").getAsString();
                String from = array.get(0).getAsJsonObject().get("from").getAsString();
                System.out.println("You were redirected from: "+from+" to "+to);
            }
            JsonObject pages = object.getAsJsonObject("query").getAsJsonObject("pages");
            JsonObject pageIDNumberObject = pages.entrySet().iterator().next().getValue().getAsJsonObject();
            JsonArray revisionsArray = pageIDNumberObject.getAsJsonArray("revisions");

            System.out.println(revisionsArray.size() + " revisionsArray returned.");
            for(int i = 0; i < revisionsArray.size(); i++){
                JsonObject revision = revisionsArray.get(i).getAsJsonObject();
                String username = revision.get("user").getAsString();
                Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());
                System.out.println("Revision #"+(i+1)+": "+username+" - "+timestamp);
            }
        }else{
            System.out.println("It doesn't exist");
        }
    }
}
