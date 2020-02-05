package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class EditorParser {
    public void parseEditors(InputStream stream){
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

            Map<String, Instant> revisions = new LinkedHashMap<String, Instant>(); // <username, timestamp>
            Map<String, Integer> editors = new LinkedHashMap<String,Integer>(); // <username, revisions>
            for(int i = 0; i < revisionsArray.size(); i++){
                JsonObject revision = revisionsArray.get(i).getAsJsonObject();
                String username = revision.get("user").getAsString();
                Instant timestamp = Instant.parse(revision.get("timestamp").getAsString());

                if(!revisions.containsKey(username)){
                    revisions.put(username,timestamp);
                }

                if(editors.containsKey(username)){
                    Integer number = editors.get(username) + 1;
                    editors.put(username,number);
                }else{
                    editors.put(username,1);
                }
                //revisions.put(username,timestamp);
            }

            final Map<String, Integer> editorsOrdered = editors.entrySet()
                    .stream()
                    .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            Iterator hmIterator = editorsOrdered.entrySet().iterator();
            ArrayList<Editor> editorList = new ArrayList<Editor>();

            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                String username = mapElement.getKey().toString();
                int edits = (Integer) mapElement.getValue();
                Instant timestamp = revisions.get(mapElement.getKey());

                //System.out.println("username: " + mapElement.getKey() + " - Edits: " + mapElement.getValue() + "Timestamp: " + revisions.get(mapElement.getKey()));
                editorList.add(new Editor(username, edits, timestamp));
            }

            System.out.println(editorList);

            Collections.sort(editorList, new sortByEditsThenTimestamp());

            for (int i=0; i<editorList.size(); i++) {
                String username = editorList.get(i).username;
                int edits = editorList.get(i).edits;
                Instant timestamp = editorList.get(i).timestamp;
                System.out.println("Username: "+username+" | Revisions: "+edits+" | Timestamp: "+timestamp);
            }
            //System.out.println("Editors: \n"+editorsOrdered);

            //Collections.sort(ar);
            //ListSorter sorter = new ListSorter();
            //System.out.println(revisions);
            //sorter.sortList(revisions,editors);

            //System.out.println(revisions);
        }else{
            System.out.println("Page doesn't exist");
        }
    }
}
