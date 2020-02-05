package edu.bsu.twoWeek;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WikiUrlBuilderTest {
    @Test
    public void TestBuildUrl() throws IOException {
        String term = "soup";
        // Create class based on results requested (Revisions List/Editors List)
        WikiUrlBuilder urlBuilder = new WikiUrlBuilder();
        urlBuilder.buildURL(term);
    }
}
