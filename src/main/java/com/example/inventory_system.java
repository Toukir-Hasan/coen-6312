package com.example;
import com.example.User;
import java.util.Scanner;


public class inventory_system{
   public String inventory_name = "walmart";
   public String location = "canada";
   

public void enter_into_system(String inventory_name, String location){

    if (inventory_name.equals(this.inventory_name) && location.equals(this.location)){

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

            User new_user=new User(username, password);
            new_user.registerUser();

            }
        
        else if (choice.equals("Login")){
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();
    
            User new_user=new User(username, password);
            if (new_user.Login(username, password)){
                system_admin admin = new system_admin();
                String system_admin_name = admin.getUser_name();
                String system_admin_pass = admin.getUser_password();
    
                if (username.equals(system_admin_name) && password.equals(system_admin_pass)){
                    System.out.println("Welcome System Admin "+system_admin_name);
                    System.out.print("Enter what would you like to do:\n 1 view all employee\n 2.create role \n 3. delete role \n");
                    String work_want_todo = scanner.next();
                    
                    if (work_want_todo.equals("1")){
                        System.out.println(admin.all_employee_list());
                    }
                    else if (work_want_todo.equals("2")){
                        System.out.print("Enter User Name");
                        String search_name = scanner.next();
                        System.out.print("Enter User Role");
                        String role = scanner.next();
                        admin.assignRoleToUser(search_name, role);

                    }


                    else if (work_want_todo.equals("3")){
                        System.out.print("Enter User Name");
                        String delete_name = scanner.next();
                 
                        admin.deleteUser(delete_name);

                    }
    
                
                }
    
                else{
                    System.out.println("Welcome");
                }

            }
          
        }
  


    }

    else{
        System.out.println("Your Location or System name is invalid!!"); 
    }

}

}
