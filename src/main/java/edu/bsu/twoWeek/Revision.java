package edu.bsu.twoWeek;

import java.time.Instant;
import java.util.Comparator;

public class Revision {
    public String username;
    public Instant timestamp;

    public Revision(String username, Instant timestamp){
        this.username = username;
        this.timestamp = timestamp;
    }
}
