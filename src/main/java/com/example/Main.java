package com.example;
import java.util.Scanner;

import com.inventory.inventory_system;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Inventory_name: ");
        String inventory_name = scanner.next();
        System.out.print("Enter location: ");
        String location = scanner.next();

        inventory_system system_login = new inventory_system(inventory_name,location);
        system_login.enter_into_system();
     

 
    }
}