package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class User {

    protected String user_name;
    protected String password;

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    private String getUser_name() {
        return user_name;
    }

    private void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_name", this.user_name);
        jsonObject.put("password", this.password);
        jsonArray.add(jsonObject);
        return jsonArray;
    }

public void registerUser() {
        // Convert user information to JSON
    JSONArray userJson = this.toJsonArray();

    // Provide the hardcoded file path
    String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\information.json";

    try {
        // Read existing JSON content from the file
        JSONParser jsonParser = new JSONParser();
        try (FileReader fileReader = new FileReader(filePath)) {
            // Check if the file is empty by reading the content into a StringBuilder
            StringBuilder fileContent = new StringBuilder();
            int character;
            while ((character = fileReader.read()) != -1) {
                fileContent.append((char) character);
            }

            // If the file is empty, write the new JSON array directly
            if (fileContent.length() == 0) {
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(userJson.toJSONString());
                }
            } else {
                // If the file is not empty, append the new user information to the existing JSON array
                Object existingJsonObj = jsonParser.parse(fileContent.toString());
                JSONArray existingJsonArray = (JSONArray) existingJsonObj;
                existingJsonArray.addAll(userJson);

                // Write the updated JSON data back to the file
                try (FileWriter file = new FileWriter(filePath)) {
                    file.write(existingJsonArray.toJSONString());
                }
            }
        }

        System.out.println("Registration successful. User information has been appended to information.json");
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}
public boolean Login(String enteredUserName, String enteredPassword) {
    // Provide the hardcoded file path
    String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\information.json";

    try (FileReader fileReader = new FileReader(filePath)) {
        JSONParser jsonParser = new JSONParser();
        JSONArray employee_information = (JSONArray) jsonParser.parse(fileReader);

        for (Object obj : employee_information) {
            JSONObject userObj = (JSONObject) obj;
            String storedUserName = (String) userObj.get("user_name");
            String storedPassword = (String) userObj.get("password");

            if (enteredUserName.equals(storedUserName) && enteredPassword.equals(storedPassword)) {
                // System.out.println("Login successful. Welcome, " + enteredUserName + "!");
                return true;
            }
        }

        System.out.println("Login failed. Invalid username or password.");
        return false;
    } catch (IOException | ParseException e) {
        e.printStackTrace();
        return false;
    }
}



}
