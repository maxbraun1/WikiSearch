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
import java.util.stream.Stream;

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
            JsonArray revisionsArray = pageIDNumberObject.getAsJsonArray("revisions");

            System.out.println(revisionsArray.size() + " revisionsArray returned.");

            Map<String, String> revisions = new HashMap<String, String>(); // <username, timestamp>
            Map<String, Integer> count = new HashMap<String,Integer>(); // <username, revisions>

            for(int i = 0; i < revisionsArray.size(); i++){
                JsonObject revision = revisionsArray.get(i).getAsJsonObject();

                String username = revision.get("user").getAsString();
                String timestamp = revision.get("timestamp").getAsString();

                if(count.containsKey(username)){
                    Integer number = count.get(username) + 1;
                    count.put(username,number);
                }else{
                    count.put(username,1);
                }

                revisions.put(username,timestamp);
                //System.out.println("Revision #"+(i+1)+": "+username+" - "+timestamp);
            }

            ListSorter sorter = new ListSorter();
            sorter.sortList(revisions,count);

            //System.out.println(revisions);
        }else{
            System.out.println("It doesn't exists");
        }
    }
}
