package org.example.springposnew.util;


import java.util.UUID;

public class AppUtil {
    public static String generateItemId(){
        return "Item-"+UUID.randomUUID();
    }
    public static String generateCustomerId(){
        return "Customer-"+UUID.randomUUID();
    }

}
