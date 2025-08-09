package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReservationManager {

    public Reservation getReservation(Guest guest, String checkIn, String checkOut)  { 
        
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {                    
            Hotel room;

            try(PreparedStatement prst = con.prepareStatement("SELECT * FROM Reservation WHERE email = ? AND checkIn = ? AND checkOut = ?")) {
                prst.setString(1,guest.getEmail());
                prst.setString(2,checkIn);
                prst.setString(3,checkOut);
            
                try(ResultSet rs = prst.executeQuery()) {
                    if(rs.next()) {

                        if(rs.getString("Room_Type").equals("Standard")) {
                            room = new Standard();
                            guest.setPartySize(rs.getInt("PartySize"));
                            return new Reservation(guest, room, checkIn,checkOut);
                        }
                        if(rs.getString("Room_Type").equals("Deluxe"))  {
                            room = new Deluxe();
                            guest.setPartySize(rs.getInt("PartySize"));
                            return new Reservation(guest, room, checkIn,checkOut);
                        }
                        else {
                            room = new Suite();
                            guest.setPartySize(rs.getInt("PartySize"));
                            return new Reservation(guest, room, checkIn,checkOut);
                        }
                    }
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateReservation(Reservation newReservation, Reservation oldReservation)  {

        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {
            
            if(newReservation.getRoom().getRoomType().equals(oldReservation.getRoom().getRoomType())) {
                String roomType = newReservation.getRoom().getRoomType();
                newReservation.setOccupancy(true);
                
                try(PreparedStatement prst = con.prepareStatement("UPDATE " + roomType + " SET occupied = ?, guest_email = ? " + "WHERE room_num = (SELECT room_num FROM " + roomType + " WHERE room_num BETWEEN 1 AND 10 AND occupied IS NULL AND guest_email IS NULL " + "LIMIT 1)");) {
                    prst.setBoolean(1, newReservation.getOccupancy());
                    prst.setString(2, newReservation.getGuest().getEmail());
                    prst.executeUpdate();
                }
                try(PreparedStatement prst2 = con.prepareStatement("Update Reservation SET email = ?, name = ?, partySize = ?, checkIn = ?, checkOut = ?, Room_Type = ? WHERE email = ?")) {
                    prst2.setString(1, newReservation.getGuest().getEmail());
                    prst2.setString(2, newReservation.getGuest().getName());
                    prst2.setInt(3,newReservation.getGuest().getPartySize());
                    prst2.setString(4, newReservation.getCheckIn());
                    prst2.setString(5, newReservation.getCheckOut());
                    prst2.setString(6,newReservation.getRoom().getRoomType());
                    prst2.setString(7,oldReservation.getGuest().getEmail());
                    prst2.executeUpdate();
                }
            }
            else {
                String newRoomType = newReservation.getRoom().getRoomType();
                String oldRoomType = oldReservation.getRoom().getRoomType();
                newReservation.setOccupancy(true);

                try(PreparedStatement prst3 = con.prepareStatement("UPDATE " + oldRoomType + " SET occupied = ?, guest_email = ? WHERE guest_email = ?");) {
                    prst3.setBoolean(1, false);
                    prst3.setString(2,null);
                    prst3.setString(3,oldReservation.getGuest().getEmail());
                    prst3.executeUpdate();
                }
                try(PreparedStatement prst = con.prepareStatement("UPDATE " + newRoomType + " SET occupied = ?, guest_email = ? " + "WHERE room_num = (SELECT room_num FROM " + newRoomType + " WHERE room_num BETWEEN 1 AND 10 AND occupied = 0 AND guest_email IS NULL " + "LIMIT 1)");) {
                    prst.setBoolean(1, newReservation.getOccupancy());
                    prst.setString(2, newReservation.getGuest().getEmail());
                    prst.executeUpdate();
                }
                try(PreparedStatement prst2 = con.prepareStatement("Update Reservation SET email = ?, name = ?, partySize = ?, checkIn = ?, checkOut = ?, Room_Type = ? WHERE email = ?")) {
                    prst2.setString(1, newReservation.getGuest().getEmail());
                    prst2.setString(2, newReservation.getGuest().getName());
                    prst2.setInt(3,newReservation.getGuest().getPartySize());
                    prst2.setString(4, newReservation.getCheckIn());
                    prst2.setString(5, newReservation.getCheckOut());
                    prst2.setString(6,newReservation.getRoom().getRoomType());
                    prst2.setString(7,oldReservation.getGuest().getEmail());
                    prst2.executeUpdate();
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addReservation(Reservation reservations) {
    
            try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {
                
                // inserting into db
                try(PreparedStatement prst = con.prepareStatement("INSERT INTO Reservation(email, name, partySize, checkIn, checkOut, Room_Type) VALUES(?, ?, ?, ?, ?, ?);");) {
                    prst.setString(1, reservations.getGuest().getEmail());
                    prst.setString(2, reservations.getGuest().getName());
                    prst.setInt(3, reservations.getGuest().getPartySize());
                    prst.setString(4, reservations.getCheckIn());
                    prst.setString(5, reservations.getCheckOut());
                    prst.setString(6,reservations.getRoom().getRoomType());
                    prst.executeUpdate();
                }

                // Updating room db
                try(PreparedStatement prst2 = con.prepareStatement("UPDATE " + reservations.getRoom().getRoomType() + " SET occupied = ?, guest_email = ? " + "WHERE room_num = (SELECT room_num FROM " + reservations.getRoom().getRoomType() + " WHERE room_num BETWEEN 1 AND 10 AND occupied IS NULL AND guest_email IS NULL " + "LIMIT 1)");){
                    prst2.setBoolean(1, true);
                    prst2.setString(2, reservations.getGuest().getEmail());
                    prst2.executeUpdate();
                    System.out.println("Reservation added.");
                }
            }
            
        catch (SQLException e) {
            System.out.println("Unable to make reservation. " + e.getMessage());
            e.printStackTrace();
        }
    }
 
}