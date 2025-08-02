import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestManager {

    public void signUp(Login log, Guest guest) throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {

            try(PreparedStatement prst = con.prepareStatement("SELECT user_id FROM login WHERE user_id = ?")) {
                prst.setString(1,guest.getEmail());

                try(ResultSet rs = prst.executeQuery()) {
                    if(!rs.next()) {

                        try(PreparedStatement prst2 = con.prepareStatement("INSERT INTO login(username,user_id,hash) VALUES(?,?,?)")) {
                            prst2.setString(1, log.getUsername());
                            prst2.setString(2, guest.getEmail());
                            prst2.setInt(3,log.getHashcode());
                            prst2.executeUpdate();
                        }

                        try(PreparedStatement prst3 = con.prepareStatement("Insert INTO Guest(id,name,partySize) VALUES(?,?,?)")) {
                            prst3.setString(1,guest.getEmail());
                            prst3.setString(2,guest.getName());
                            prst3.setInt(3,guest.getPartySize());
                            prst3.executeUpdate();
                        }
                    }
                    else {
                        System.out.println("You are already a member");
                    }
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean login(Login log) throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {

            try(PreparedStatement prst = con.prepareStatement("SELECT * FROM login WHERE username = ?")) {
                prst.setString(1,log.getUsername());

                try(ResultSet rs = prst.executeQuery()) {
                    if(!rs.next()) {
                        return false;
                    }
                    return rs.getInt("hash") == log.getHashcode();
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public Guest getInfo(Login log) throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {

            try(PreparedStatement prst = con.prepareStatement("SELECT * FROM Guest WHERE id = ?")) {
                prst.setString(1,log.getUserId());

                try(ResultSet rs = prst.executeQuery()) {
                    if(rs.next()) {
                        return new Guest(rs.getString("id"),rs.getString("name"),rs.getInt("partySize"));
                    }
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}