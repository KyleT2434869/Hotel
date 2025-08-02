import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {
    
    Connection con;
     public Connection connect() {
        try {
        Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db");
        PreparedStatement pragma = con.prepareStatement("PRAGMA foreign_keys = ON;");
        pragma.execute();
        System.out.println("Connected to database");
        return con;
        }
        
        catch(SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return null;
    }

    public void addReservation(Guest guest) throws SQLException {
        
        try {
            if (con == null || con.isClosed()) {
                con = connect();
            }

            PreparedStatement prst = con.prepareStatement("SELECT name FROM Guest WHERE name = ?");
            prst.setString(1, guest.getName());
            ResultSet rs = prst.executeQuery();
        
            if(!rs.next()) {
                prst = con.prepareStatement("INSERT INTO Guest(id,name,partySize) VALUES(?,?,?);");
                prst.setString(1,guest.getEmail());
                prst.setString(2,guest.getName());
                prst.setInt(3,guest.getPartySize());
                prst.executeUpdate();

                prst = con.prepareStatement("UPDATE " + guest.getRoomType() + " SET occupied = ?, guest_id = ? " + "WHERE room_num = (SELECT room_num FROM " + guest.getRoomType() + " WHERE room_num BETWEEN 1 AND 10 AND occupied IS NULL AND guest_id IS NULL " +"LIMIT 1)");
                prst.setBoolean(1,true);
                prst.setString(2,guest.getEmail());
                prst.executeUpdate();
            }
            
            else {
                prst = con.prepareStatement("UPDATE " + guest.getRoomType() + " SET occupied = ?, guest_id = ? " + "WHERE room_num = (SELECT room_num FROM " + guest.getRoomType() + " WHERE room_num BETWEEN 1 AND 10 AND occupied IS NULL AND guest_id IS NULL " +"LIMIT 1)");
                prst.setBoolean(1,true);
                prst.setString(2,guest.getEmail());
                prst.executeUpdate();

            }      
            System.out.println("A room has been reserved for you");
            rs.close();
            prst.close();
          
        }
        catch(SQLException e) {
            System.out.println("Unable to book a room" + e.getMessage());
            e.printStackTrace();
        }

    }

}
