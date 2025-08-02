import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
        String checkIn;
        String checkOut;

        Guest guest;
        Room room = null;
        Reservations reservation;
        ReservationController reserve = new ReservationController();

        Scanner keyboard = new Scanner(System.in);

        connect(url);
        createNewTable(url);

        do {
            Menu();
            selection = keyboard.nextInt();
            keyboard.nextLine();

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

            System.out.print("Please enter your check in date (dd-MM-yyyy): ");
            checkIn = keyboard.nextLine();
            System.out.print("Please enter your check out date (dd-MM-yyyy): ");
            checkOut = keyboard.nextLine();

            switch (selection) {
                case 1:
                    reservation = new Reservations(guest, room, checkIn, checkOut);
                    reserve.addReservation(reservation);

                    break;
                case 2:
                    reservation = new Reservations(guest, room, checkIn, checkOut);
                    reserve.cancelReservation(reservation);
                    break;
                case 3:
                    reservation = new Reservations(guest, room, checkIn, checkOut);
                    reserve.updateReservation(reservation);
                    break;
                case 4:
                    reservation = new Reservations(guest, room, checkIn, checkOut);
                    reserve.printReservationInfo(reservation);
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
        System.out.println("2. Cancel Reservation");
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
}
