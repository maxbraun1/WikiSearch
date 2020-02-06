package edu.bsu.twoWeek;

import com.google.gson.JsonArray;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class EditorSorter {
    public String sortEditors(JsonArray revisionsArray){
        RevisionsMapBuilder revisionsMapBuilder = new RevisionsMapBuilder();
        EditorsMapBuilder editorsMapBuilder = new EditorsMapBuilder();
        Map<String, Instant> revisions = revisionsMapBuilder.buildRevisionsMap(revisionsArray);
        Map<String, Integer>editors = editorsMapBuilder.buildEditorsMap(revisionsArray);

        final Map<String, Integer> EditorsSorted = editors.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Iterator<Map.Entry<String, Integer>> hmIterator = EditorsSorted.entrySet().iterator();
        ArrayList<Editor> editorList = new ArrayList<>();

        while (hmIterator.hasNext()) {
            Map.Entry<String, Integer> mapElement = hmIterator.next();
            String username = mapElement.getKey();
            int edits = mapElement.getValue();
            Instant timestamp = revisions.get(mapElement.getKey());
            editorList.add(new Editor(username, edits, timestamp));
        }

        editorList.sort(new sortByEditsThenTimestamp());
        StringBuilder output = new StringBuilder("-- " + editorList.size() + " editors returned --\n");

        for (Editor editor : editorList) {
            String username = editor.username;
            int edits = editor.edits;
            Instant timestamp = editor.timestamp;
            output.append(String.format("%-20s - %-6s - %s\n", username, edits, timestamp));
        }
        return output.toString();
    }
}
