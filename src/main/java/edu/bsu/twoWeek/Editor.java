package edu.bsu.twoWeek;

import java.time.Instant;
import java.util.Comparator;

class Editor {
    String username;
    int edits;
    Instant timestamp;

    public Editor(String username, int edits, Instant timestamp){
        this.username = username;
        this.edits = edits;
        this.timestamp = timestamp;
    }
}

class sortByEditsThenTimestamp implements Comparator<Editor> {
    public int compare(Editor a, Editor b) {
        int sComp = a.edits - b.edits;

        if(sComp == 0){
            return sComp;
        }else{
            return a.timestamp.compareTo(b.timestamp);
        }
    }
}
