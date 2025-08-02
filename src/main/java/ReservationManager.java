import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ReservationManager {

    Connection con;

    public Connection connect() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:hotel.db");
            PreparedStatement pragma = con.prepareStatement("PRAGMA foreign_keys = ON;");
            pragma.execute();
            System.out.println("Connected to database");
            return con;
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return null;
    }

    public void addReservation(Reservations reservations) {
        try {
            if (con == null || con.isClosed())
                con = connect();

            PreparedStatement prst = con.prepareStatement("INSERT INTO Reservations(id, name, partySize, checkIn, checkOut) VALUES(?,?,?, ?, ?);");
            prst.setString(1, reservations.getGuest().getEmail());
            prst.setString(2, reservations.getGuest().getName());
            prst.setInt(3, reservations.getGuest().getPartySize());

            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
            prst.setString(4, formatter.format(reservations.getCheckIn())); // in progress
            prst.setString(5, formatter.format(reservations.getCheckOut())); // in progress
            prst.executeUpdate();

            prst = con.prepareStatement("UPDATE " + reservations.getRoom().getRoomType() + " SET occupied = ?, guest_id = ? " + "WHERE room_num = (SELECT room_num FROM " + reservations.getRoom().getRoomType() + " WHERE room_num BETWEEN 1 AND 10 AND occupied IS NULL AND guest_id IS NULL " + "LIMIT 1)");
            prst.setBoolean(1, true);
            prst.setString(2, reservations.getGuest().getEmail());
            prst.executeUpdate();

            System.out.println("Reservation added.");
            prst.close();
        } catch (SQLException e) {
            System.out.println("Unable to make reservation. " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void cancelReservation(Reservations reservations) {
        try {
            if (con == null || con.isClosed())
                con = connect();

            // cancel by email, name, or room number
            PreparedStatement prst = con.prepareStatement("SELECT email FROM Reservations WHERE id = ?");
            prst.setString(1, reservations.getGuest().getEmail());
            ResultSet rs = prst.executeQuery();

            if (!rs.next())
                System.out.println("Reservation not found.");

            String email = rs.getString("email");

            prst = con.prepareStatement("DELETE FROM Reservations WHERE id = ?");
            prst.setInt(1, email);
            prst.executeUpdate();

            prst = con.prepareStatement("UPDATE " + reservations.getRoom().getRoomType() + " SET occupied = 0, guest_id = NULL Where id = ?");
            prst.setInt(1, email);
            prst.executeUpdate();
            prst.close();

            System.out.println("Reservation cancelled.");

        } catch (SQLException e) {
            System.out.println("Unable to make reservation. " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void updateReservation(Reservations reservations) {
        try {
            if (con == null || con.isClosed())
                con = connect();

            int selection;

            Scanner keyboard = new Scanner(System.in);

            System.out.println("Update: ");
            System.out.println("1. Email");
            System.out.println("2. Name");
            System.out.println("3. Party Size");
            System.out.println("4. Room");
            System.out.println("5. Check In Date");
            System.out.println("6. Check Out  Date");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            selection = keyboard.nextInt();
            keyboard.nextLine();

            switch (selection) {
                case 1:
                    System.out.println("Enter new email: ");
                    String newEmail = keyboard.next();

                    PreparedStatement prst = con.prepareStatement("UPDATE" + reservations.getGuest().getEmail() + " SET guest_id WHERE id = ?");
                    prst.setString(1, newEmail);
                    prst.executeUpdate();

                    break;
                case 2:
                    System.out.println("Enter new name: ");
                    String newName = keyboard.next();

                    prst = con.prepareStatement("UPDATE" + reservations.getGuest().getName() + " SET guest_id WHERE id = ?");
                    prst.setString(1, newName);
                    prst.executeUpdate();

                    break;
                case 3:
                    System.out.println("Enter new party size: ");
                    String newPartySize = keyboard.next();

                    prst = con.prepareStatement("UPDATE" + reservations.getGuest().getPartySize() + " SET guest_id WHERE id = ?");
                    prst.setString(1, newPartySize);
                    prst.executeUpdate();

                    break;
                case 4:
                    System.out.println("Enter new room: ");
                    String newRoom = keyboard.next();

                    // Find available room of new type
                    prst = con.prepareStatement("SELECT id FROM Room WHERE roomType = ? AND occupied = 0 LIMIT 1");
                    prst.setString(1, newRoom);
                    ResultSet rs = prst.executeQuery();

                    if (!rs.next())
                        System.out.println("No " + newRoom + " rooms available.");

                    // Updates room type for room object
                    prst = con.prepareStatement("UPDATE" + reservations.getRoom().getRoomType() + " SET room_id WHERE id = ?");
                    prst.setString(1, newRoom);
                    prst.executeUpdate();

                    // Update reservation db with new room
                    prst = con.prepareStatement("UPDATE Reservation SET room_id WHERE id = ?");
                    prst.setString(1, newRoom);
                    prst.executeUpdate();

                    // Free old room
                    // Occupy new room

                    break;
                case 5:
                    System.out.println("Enter new check in date (dd-MM-yyyy): ");
                    String newCheckIn = keyboard.next();

                    PreparedStatement prst = con.prepareStatement("UPDATE" + reservations.getCheckIn() + " SET checkIn WHERE id = ?");
                    prst.setString(1, newCheckIn);
                    prst.executeUpdate();

                    break;
                case 6:
                    System.out.println("Enter new check out date (dd-MM-yyyy): ");
                    String newCheckout = keyboard.next();

                    PreparedStatement prst = con.prepareStatement("UPDATE" + reservations.getCheckOut() + " SET checkOut WHERE id = ?");
                    prst.setString(1, newCheckout);
                    prst.executeUpdate();

                    break;
                case 7:
                    System.out.println("Exiting update");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
            System.out.println("Reservation updated.");
        } catch (SQLException e) {
            System.out.println("Unable to update reservation. " + e.getMessage());
            e.printStackTrace();
        }
    }
    // could be used for conformation email/message
    public void printReservationInfo(Reservations reservations) {
        try {
            if (con == null || con.isClosed())
                con = connect();

            PreparedStatement prst = con.prepareStatement("SELECT guest.id, guest.name, guest.partySize, guest.checkIn, guest.checkOut, rm.roomTyoe, rm.price FROM Reservation guest JOIN Room rm ON guest.room_num = rm.id WHERE r.id = ?");
            prst.setString(1, reservations.getGuest().getEmail());
            ResultSet rs = prst.executeQuery();

            if (!rs.next())
                System.out.println("Reservation not found.");

            String email = rs.getString("guest.id");
            String name = rs.getString("guest.name");
            int partySize = rs.getInt("guest.partySize");
            String checkIn = rs.getString("guest.checkIn");
            String checkOut = rs.getString("guest.checkOut");
            String roomType = rs.getString("rm.roomTyoe");
            double price = rs.getInt("rm.price");

            LocalDate checkInDate = LocalDate.parse(checkIn);
            LocalDate checkOutDate = LocalDate.parse(checkOut);
            long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            System.out.println(name);
            System.out.println(email);
            System.out.println(partySize);
            System.out.println(roomType);
            System.out.println(price);
            System.out.println(checkInDate);
            System.out.println(checkOutDate);
            System.out.println(nights);
            System.out.println(price * nights);

            rs.close();
            prst.close();
        }
        catch (SQLException e) {
            System.out.println("Unable to update reservation. " + e.getMessage());
            e.printStackTrace();
        }
    }
}
