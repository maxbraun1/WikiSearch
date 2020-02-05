package edu.bsu.twoWeek;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WikiConnectionCreatorTest {
    @Test
    public void TestBuildUrl() throws IOException {
        String term = "soup";
        // Create class based on results requested (Revisions List/Editors List)
        WikiConnectionCreator urlBuilder = new WikiConnectionCreator();
        urlBuilder.buildURL(term);
    }
}
