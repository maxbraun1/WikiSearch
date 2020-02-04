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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static sun.tools.java.Type.tString;

public class RevisionParser {

    public void parse(InputStream stream){
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
            JsonArray revisions = pageIDNumberObject.getAsJsonArray("revisions");

            System.out.println(revisions.size() + " revisions returned.");

            Map<String, Integer> user = new HashMap<String, Map<String,Integer>>(); // <username, (timestamp,count)>

            for(int i = 0; i < revisions.size(); i++){
                JsonObject revision = revisions.get(i).getAsJsonObject();

                String username = revision.get("user").getAsString();
                String timestamp = revision.get("timestamp").getAsString();

                if(user.containsKey(revision.get("user").getAsString())){
                    Map<String,Integer> info = user.get(username);
                    System.out.println(info.get(""));
                    user.put(revision.get("user").getAsString(),count);
                }else{
                    user.put(revision.get("user").getAsString(),1);
                    userTimestamp.put(revision.get("user").getAsString(),revision.get("timestamp").getAsString())
                }

                String username = revision.get("user").getAsString();
                String timestamp = revision.get("timestamp").getAsString();
                //System.out.println("Revision #"+(i+1)+": "+username+" - "+timestamp);
            }
            HashMap<String, Integer> sorted = user.entrySet()
                    .stream()
                    .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            System.out.println(sorted);
        }else{
            System.out.println("It doesn't exists");
        }
    }

}
