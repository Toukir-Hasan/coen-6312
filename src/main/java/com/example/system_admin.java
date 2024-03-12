package com.example;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class system_admin {
    private String name = "toukir";
    private String password = "123";
    // role, job level,

    public String getUser_name() {
        return name;
    }
    public String getUser_password() {
        return password;
    }

public JSONArray all_employee_list(){
    String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\information.json";

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray(); // Return an empty array in case of an error
        }

}

public void assignRoleToUser(String username, String role) {
    // Provide the hardcoded file path
    String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\information.json";

    try (FileReader fileReader = new FileReader(filePath)) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

        for (Object obj : jsonArray) {
            JSONObject userObj = (JSONObject) obj;
            String storedUserName = (String) userObj.get("user_name");

            if (username.equals(storedUserName)) {
                // Update the user's role in the JSON object
                userObj.put("role", role);

                // Write the updated JSON data back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonArray.toJSONString());
                    System.out.println("Role assigned successfully.");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("User not found with username: " + username);
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}

public void deleteUser(String username) {
    // Provide the hardcoded file path
    String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\information.json";

    try (FileReader fileReader = new FileReader(filePath)) {
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

        // Iterate through the array to find and remove the user
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject userObj = (JSONObject) jsonArray.get(i);
            String storedUserName = (String) userObj.get("user_name");

            if (username.equals(storedUserName)) {
                // Remove the user from the array
                jsonArray.remove(i);

                // Write the updated JSON data back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    fileWriter.write(jsonArray.toJSONString());
                    System.out.println("User deleted successfully.");
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("User not found with username: " + username);
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
}




}












