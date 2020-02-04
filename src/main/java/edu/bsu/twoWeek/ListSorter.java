package edu.bsu.twoWeek;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ListSorter {
    public Map<String,String> sortList(Map<String,String> revisions, Map<String,Integer> count){
        // revisions = <username,timestamp>
        // count = <username, revisions>
        final Map<String, Integer> sorted = count.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int value = sorted.entrySet().iterator().next().getValue();
        boolean listSorted = false;
        while(listSorted == false){
            if(value > sorted.entrySet().iterator().next().getValue()){
                listSorted = true;
            }else{

            }
        }
        System.out.println(sorted);
        System.out.println(value);
        return null;
    }
}
