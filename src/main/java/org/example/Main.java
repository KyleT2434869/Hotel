package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static String url = "jdbc:sqlite:hotel_4.db";
    static Connection con;
    public static void main(String[] args) {

        String name;
        String email;
        String roomType;
        int partySize;
        int selection;

        Guest guest;
        Hotel room = null;

        Scanner keyboard = new Scanner(System.in);

        connect(url);
        createNewTable(url);

        do {
            Menu();
            selection = keyboard.nextInt();
            keyboard.nextLine();

            switch (selection) {
                case 1:
                    System.out.print("Please enter your name: ");
                    name = keyboard.nextLine();

                    System.out.print("Please enter your email: ");
                    email = keyboard.nextLine();

                    System.out.print("Please enter your party size: ");
                    partySize = keyboard.nextInt();
                    keyboard.nextLine();

                    guest = new Guest(name, email, partySize);

                    System.out.print("Please enter your desired room type (Standard, Deluxe, Suite): ");
                    roomType = keyboard.nextLine();
                    if (roomType.equalsIgnoreCase("Standard")) {
                        room = new Standard();
                    } else if (roomType.equalsIgnoreCase("Deluxe")) {
                        room = new Deluxe();
                    } else if (roomType.equalsIgnoreCase("Suite")) {
                        room = new Suite();
                    } else {
                        System.out.println("Invalid room type");
                    }
                    insert(guest, room, url);
                    break;
                case 2:
                    System.out.print("Please enter name or email used for booking: ");
                    String nameEmail = keyboard.nextLine();
                    delete(nameEmail, url);
                    break;
                case 3:
                    // Ask what they would like to change
                    // another menu
                    // loop until done with changes
                    break;
                case 4:
                    // print out all guest information/report
                    // name, email, party size
                    // room type, room number, cost per night, total cost
                    // check-in check-out dates and times
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
            }
        } while (selection != 5);
    }
    public static void Menu() {
        System.out.println("Menu");
        System.out.println("1. Make Reservation");
        System.out.println("2. Delete Reservation");
        System.out.println("3. Update Reservation");
        System.out.println("4. View All Reservations");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }
    public static void connect(String url) {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url);
                System.out.println("Connected to the database");
            } else {
                System.out.println("Already connected to the database");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
    public static void createNewTable(String url) {
        String table = "CREATE TABLE IF NOT EXISTS guests " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " roomType TEXT NOT NULL, " +
                " partySize INTEGER NOT NULL);";
        try {
            java.sql.Statement stmt = con.createStatement();
                stmt.execute(table);
                System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insert(Guest guest, Hotel room, String url) {
        String addGuest = "INSERT INTO guests (name, email, roomType, partySize) VALUES (?, ?, ?, ?);";

        try {
            java.sql.PreparedStatement pstmt = con.prepareStatement(addGuest);
            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getEmail());
            pstmt.setString(3, room.getRoomType());
            pstmt.setInt(4, guest.getPartySize());
            pstmt.executeUpdate();
            System.out.println("Inserted guest");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delete(String nameEmail, String url) {
        String deleteGuest = "DELETE FROM guests WHERE name = ? OR email = ?";

        try {
            java.sql.PreparedStatement pstmt = con.prepareStatement(deleteGuest);
            pstmt.setString(1, nameEmail);
            pstmt.setString(2, nameEmail);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Guest(s) deleted successfully.");
            } else {
                System.out.println("No guest found with that name or email.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting guest: " + e.getMessage());
        }
    }
    public static void update(String url) {

    }
    public static void viewAllReservations(String url) {

    }

}
