package com.inventory;

import com.example.User;
import com.example.superviser;
import com.example.system_admin;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class inventory_system {
    private String inventory_name;
    private String location;

    public inventory_system(String inventory_name, String location) {
        this.inventory_name = inventory_name;
        this.location = location;
    }

    public void enter_into_system() {

        String filePath = "D:\\java\\New folder\\demo\\src\\main\\resources\\location.json";

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONParser jsonParser = new JSONParser();
            JSONArray employee_information = (JSONArray) jsonParser.parse(fileReader);
            

            for (Object obj : employee_information) {
                JSONObject userObj = (JSONObject) obj;
                String stored_inventory_name = (String) userObj.get("inventory_name");
                String stored_location = (String) userObj.get("location");
                System.out.println("stored name"+stored_inventory_name);
                if (stored_inventory_name.equals(this.inventory_name) && stored_location.equals(this.location)) {

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Login");
                    System.out.println("Register");
                    System.out.print("Enter your choice ( Register or Login): ");

                    String choice = scanner.next();

                    if (choice.equals("Register")) {
                        // User chose login
                        System.out.print("Enter username: ");
                        String username = scanner.next();
                        System.out.print("Enter password: ");
                        String password = scanner.next();

                        User new_user = new User(username, password);
                        new_user.registerUser();

                    }

                    else if (choice.equals("Login")) {
                        System.out.print("Enter username: ");
                        String username = scanner.next();
                        System.out.print("Enter password: ");
                        String password = scanner.next();

                        User new_user = new User(username, password);
                        
                        if (new_user.Login(username, password)) {
                            system_admin admin = new system_admin();
                            String system_admin_name = admin.getUser_name();
                            String system_admin_pass = admin.getUser_password();
                            String title=new_user.role(username, password);
                            System.out.println(title);
                            if (username.equals(system_admin_name) && password.equals(system_admin_pass)) {
                                System.out.println("Welcome System Admin " + system_admin_name);
                                System.out.print(
                                        "Enter what would you like to do:\n 1 view all employee\n 2.create role \n 3. delete role \n");
                                String work_want_todo = scanner.next();

                                if (work_want_todo.equals("1")) {
                                    System.out.println(admin.all_employee_list());
                                } else if (work_want_todo.equals("2")) {
                                    System.out.print("Enter User Name");
                                    String search_name = scanner.next();
                                    System.out.print("Enter User Role");
                                    String role = scanner.next();
                                    admin.assignRoleToUser(search_name, role);

                                }

                                else if (work_want_todo.equals("3")) {
                                    System.out.print("Enter User Name");
                                    String delete_name = scanner.next();

                                    admin.deleteUser(delete_name);

                                }

                            }
                            else if (title.equals("supervisor")){
                                superviser new_suverviser = new superviser(username,password);
                                System.out.println("Welcome Superviser " + new_suverviser.getUser_name() );
                                System.out.print(
                                        "Enter what would you like to do:\n 1 add iteams\n 2.update \n 3. delele \n");
                                String work_want_todo = scanner.next();
                                if (work_want_todo.equals("1")){
                                    System.out.print("barcode of iteams ");
                                    String barcode = scanner.next();
                                    System.out.print("Enter iteam_name ");
                                    String iteam_name = scanner.next();
                                    System.out.print("Enter price ");
                                    int price = scanner.nextInt();
                                    new_suverviser.addItem(barcode,iteam_name,price);
                                    
                                    
                                }

                            }

                            else {
                                System.out.println("Welcome");
                            }

                        }

                    }

                }
                else{
                    System.out.println("something went wrong");
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }

    }

}
