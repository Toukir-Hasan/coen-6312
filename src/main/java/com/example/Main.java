package com.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login");
        System.out.println("Register");
        System.out.print("Enter your choice ( Register or Login): ");

        String choice = scanner.next();

        if (choice.equals("register")) {
            // User chose login
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            User new_user=new User(username, password);
            new_user.registerUser();

    }
}
}