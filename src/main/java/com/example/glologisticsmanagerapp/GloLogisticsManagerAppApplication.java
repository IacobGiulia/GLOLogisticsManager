package com.example.glologisticsmanagerapp;


import java.sql.*;
import java.util.Scanner;


public class GloLogisticsManagerAppApplication {

    private static Connection connection;
    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/logisticsDB";
            String user = "postgres";
            String password = "1q2w3e";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL database!");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n=== GLO Logistics Management System ===");
                System.out.println("1. Client Menu");
                System.out.println("2. Logistics Manager Menu");
                System.out.println("3. Warehouse Manager Menu");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> clientMenu(scanner);
                    case 2 -> logisticsManagerMenu(scanner);
                    case 3 -> warehouseManagerMenu(scanner);
                    case 4 -> {
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            }
            scanner.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void clientMenu(Scanner scanner) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Create Shipment");
            System.out.println("2. View Shipment by ID");
            System.out.println("3. View All My Shipments");
            System.out.println("4. Track Shipment Progress");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createShipment(scanner);
                case 2 -> viewShipmentById(scanner);
                case 3 -> viewAllShipments();
                case 4 -> trackShipment(scanner);
                case 5 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void logisticsManagerMenu(Scanner scanner) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Logistics Manager Menu ---");
            System.out.println("1. Create Shipment");
            System.out.println("2. View Shipment by Order ID");
            System.out.println("3. Update Shipment Status");
            System.out.println("4. Delete Shipment");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createShipment(scanner);
                case 2 -> viewShipmentById(scanner);
                case 3 -> updateShipmentStatus(scanner);
                case 4 -> deleteShipment(scanner);
                case 5 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void warehouseManagerMenu(Scanner scanner) throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Warehouse Manager Menu ---");
            System.out.println("1. Add Item to Shipment");
            System.out.println("2. Calculate Total Weight of Shipment");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addItemToShipment(scanner);
                case 2 -> calculateTotalWeight(scanner);
                case 3 -> back = true;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createShipment(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        String sql = "INSERT INTO shipments (id, source, destination, weight, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, source);
        stmt.setString(3, destination);
        stmt.setDouble(4, weight);
        stmt.setString(5, "CREATED");

        stmt.executeUpdate();
        System.out.println("Shipment created successfully!");
    }

    private static void viewShipmentById(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();

        String sql = "SELECT * FROM shipments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Shipment: " +
                    rs.getString("id") + " | " +
                    rs.getString("source") + " -> " +
                    rs.getString("destination") + " | " +
                    rs.getDouble("weight") + "kg | " +
                    rs.getString("status"));
        } else {
            System.out.println("Shipment not found.");
        }
    }

    private static void viewAllShipments() throws SQLException {
        String sql = "SELECT * FROM shipments";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println("Shipment: " +
                    rs.getString("id") + " | " +
                    rs.getString("source") + " -> " +
                    rs.getString("destination") + " | " +
                    rs.getDouble("weight") + "kg | " +
                    rs.getString("status"));
        }
    }

    private static void trackShipment(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();

        String sql = "SELECT status FROM shipments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Shipment status: " + rs.getString("status"));
        } else {
            System.out.println("Shipment not found.");
        }
    }

    private static void updateShipmentStatus(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        String sql = "UPDATE shipments SET status = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, status);
        stmt.setString(2, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Shipment status updated!");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    private static void deleteShipment(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();

        String sql = "DELETE FROM shipments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Shipment deleted!");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    private static void addItemToShipment(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter item weight: ");
        double itemWeight = scanner.nextDouble();
        scanner.nextLine();

        String sql = "UPDATE shipments SET weight = weight + ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, itemWeight);
        stmt.setString(2, id);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Item added to shipment.");
        } else {
            System.out.println("Shipment not found.");
        }
    }

    private static void calculateTotalWeight(Scanner scanner) throws SQLException {
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();

        String sql = "SELECT weight FROM shipments WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Total shipment weight: " + rs.getDouble("weight") + " kg");
        } else {
            System.out.println("Shipment not found.");
        }
    }
}



