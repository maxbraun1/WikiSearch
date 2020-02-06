package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class EditorParser {
    public String parseEditors(JsonArray revisionsArray){
        Map<String, Instant> revisions = new LinkedHashMap<String, Instant>(); // <username, timestamp>
        Map<String, Integer> editors = new LinkedHashMap<String,Integer>(); // <username, revisions>

        RevisionsMapBuilder revisionsMapBuilder = new RevisionsMapBuilder();
        EditorsMapBuilder editorsMapBuilder = new EditorsMapBuilder();

        revisions = revisionsMapBuilder.buildRevisionsMap(revisionsArray);
        editors = editorsMapBuilder.buildEditorsMap(revisionsArray);

        final Map<String, Integer> EditorsSorted = editors.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Iterator hmIterator = EditorsSorted.entrySet().iterator();
        ArrayList<Editor> editorList = new ArrayList<Editor>();

        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            String username = mapElement.getKey().toString();
            int edits = (Integer) mapElement.getValue();
            Instant timestamp = revisions.get(mapElement.getKey());
            editorList.add(new Editor(username, edits, timestamp));
        }

        Collections.sort(editorList, new sortByEditsThenTimestamp());

        String output = "-- "+editorList.size()+" editors returned --\n";

        for (int i=0; i<editorList.size(); i++) {
            String username = editorList.get(i).username;
            int edits = editorList.get(i).edits;
            Instant timestamp = editorList.get(i).timestamp;
            //System.out.println("Username: "+username+" | Revisions: "+edits+" | Timestamp: "+timestamp);
            output += String.format("%-20s - %-6s - %s\n", username, edits, timestamp);
        }
        return output;
    }
}
