package edu.bsu.twoWeek;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternetTesterTest {
    @Test
    public void testTestInternetConnection(){
        InternetTester internetTester = new InternetTester();
        boolean actual = internetTester.testInternetConnection();
        Assertions.assertEquals(true, actual);
    }

}
