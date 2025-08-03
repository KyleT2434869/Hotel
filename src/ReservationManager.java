import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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

    public void addReservation(Reservation reservations) {
        try {
            if (con == null || con.isClosed())
                con = connect();

            // inserting to db
            PreparedStatement prst = con.prepareStatement("INSERT INTO Reservations(id, name, partySize, checkIn, checkOut) VALUES(?,?,?, ?, ?);");
            prst.setString(1, reservations.getGuest().getEmail());
            prst.setString(2, reservations.getGuest().getName());
            prst.setInt(3, reservations.getGuest().getPartySize());
            prst.setString(4, reservations.getCheckIn());
            prst.setString(5, reservations.getCheckOut());
            prst.executeUpdate();

            // Updating room db
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
}