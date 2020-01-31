package edu.bsu.twoWeek;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WikiUrlBuilder {
    public InputStream buildURL(String term) throws IOException {
        String search = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+term+"&rvprop=timestamp|user&rvlimit=30&redirects";
        URL url = new URL(search);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Revision Tracker/0.1 (http://www.cs.bsu.edu/~pvg/courses/cs222Fa20; msbraun@bsu.edu)");
        return connection.getInputStream();
    }
}
