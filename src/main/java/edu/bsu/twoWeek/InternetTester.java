package edu.bsu.twoWeek;

import java.net.URL;
import java.net.URLConnection;

public class InternetTester {
    public Boolean testInternetConnection(){
        String connection;
        try {
            URL url = new URL("https://en.wikipedia.org/");
            URLConnection Urlconnection = url.openConnection();
            Urlconnection.connect();
            connection = "Connection Successful";
        } catch (Exception e) {
            connection = "Internet Not Connected";
            System.out.println("Internet Not Connected");
        }
        return (connection.equals("Connection Successful"));

    }
}
