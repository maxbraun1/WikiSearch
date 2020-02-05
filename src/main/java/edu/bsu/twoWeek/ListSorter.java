package edu.bsu.twoWeek;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class ListSorter {
    public Map<String,String> sortList(Map<String, Instant> revisions, Map<String,Integer> editors){
        // revisions = <username,timestamp>
        // count = <username, revisions>
        System.out.println(editors);
        final Map<String, Integer> editorsOrdered = editors.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Iterator hmIterator = editorsOrdered.entrySet().iterator();
        System.out.println(editorsOrdered);
        ArrayList<Editor> editorList = new ArrayList<Editor>();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            //System.out.println("username: " + mapElement.getKey() + " - Edits: " + mapElement.getValue() + "Timestamp: " + revisions.get(mapElement.getKey()));
            //System.out.println(mapElement.getValue());
            //int marks = ((int)mapElement.getValue() + 10);
            //System.out.println(mapElement.getKey() + " : " + marks);
        }

        //System.out.println(sorted);
        return null;
    }
}
