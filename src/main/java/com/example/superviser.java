package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class superviser extends User{
    
    public superviser(String user_name,String password ){
        super(user_name,password);
    }

public String getUser_name(){
    return user_name;
}

public void addItem(String barcode, String itemName, int price) {
    String FILE_PATH = "D:\\java\\New folder\\demo\\src\\main\\resources\\iteams.json";
        // Create a JSON object for the new item
        JSONObject newItem = new JSONObject();
        newItem.put("barcode", barcode);
        newItem.put("item_name", itemName);
        newItem.put("price", price);

        // Read existing JSON content from the file
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONArray existingItems = (JSONArray) org.json.simple.JSONValue.parse(jsonString);

            // If the file is empty or doesn't exist, create a new JSONArray
            if (existingItems == null) {
                existingItems = new JSONArray();
            }

            // Add the new item to the existing JSON array
            existingItems.add(newItem);

            // Write the updated JSON data back to the file
            try (FileWriter file = new FileWriter(FILE_PATH)) {
                file.write(existingItems.toJSONString());
            }

            System.out.println("Item added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
