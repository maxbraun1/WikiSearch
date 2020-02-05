package edu.bsu.twoWeek;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class WikiConnectionCreatorTest {
    @Test
    public void TestCreateConnection() throws Exception {
        String term = "soup";
        WikiConnectionCreator creator = new WikiConnectionCreator();
        InputStream actual = creator.createConnection(term);
    }
}
