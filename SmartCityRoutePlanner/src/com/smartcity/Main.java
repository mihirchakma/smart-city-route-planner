package com.smartcity;

import com.smartcity.service.RoutePlanner;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main application class that runs the menu-driven console interface.
 * Responsible for user input, validation, and calling the RoutePlanner service.
 */

public class Main {

    public static void displayMenu() {
        System.out.println("\n===== Smart City Route Planner =====");
        System.out.println("1. Add a new location");
        System.out.println("2. Remove a location");
        System.out.println("3. Add a road between locations");
        System.out.println("4. Remove a road");
        System.out.println("5. Display all connections");
        System.out.println("6. Display all locations (sorted)");
        System.out.println("7. Exit");
        System.out.println("==================================");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        RoutePlanner planner = new RoutePlanner();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Pre-populate with some data for easier testing
        planner.handleAddLocation("Downtown");
        planner.handleAddLocation("Uptown");
        planner.handleAddLocation("Suburb");
        planner.handleAddLocation("Airport");
        planner.handleAddRoad("Downtown", "Uptown");
        planner.handleAddRoad("Downtown", "Suburb");
        System.out.println("\n(Pre-populated with some initial data for demonstration.)");


        while (choice != 7) {
            displayMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                String loc1, loc2;

                switch (choice) {
                    case 1:
                        System.out.print("Enter new location name: ");
                        loc1 = scanner.nextLine();
                        planner.handleAddLocation(loc1);
                        break;
                    case 2:
                        System.out.print("Enter location name to remove: ");
                        loc1 = scanner.nextLine();
                        planner.handleRemoveLocation(loc1);
                        break;
                    case 3:
                        System.out.print("Enter first location: ");
                        loc1 = scanner.nextLine();
                        System.out.print("Enter second location: ");
                        loc2 = scanner.nextLine();
                        planner.handleAddRoad(loc1, loc2);
                        break;
                    case 4:
                        System.out.print("Enter first location of the road to remove: ");
                        loc1 = scanner.nextLine();
                        System.out.print("Enter second location: ");
                        loc2 = scanner.nextLine();
                        planner.handleRemoveRoad(loc1, loc2);
                        break;
                    case 5:
                        planner.displayAllConnections();
                        break;
                    case 6:
                        planner.displayAllLocations();
                        break;
                    case 7:
                        System.out.println("Exiting planner. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner
            }
        }
        scanner.close();
    }
}
