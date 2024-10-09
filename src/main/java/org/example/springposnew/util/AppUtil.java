package org.example.springposnew.util;


import java.util.UUID;

public class AppUtil {
    public static String generateItemId(){
        return "NOTE-"+UUID.randomUUID();
    }
    public static String generateCustomerId(){
        return "USER-"+UUID.randomUUID();
    }

}
