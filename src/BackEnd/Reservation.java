package BackEnd;

public class Reservation {
    private Guest guest;
    private Hotel room;
    private String checkIn;
    private String checkOut;
    private Boolean occupancy;

    public Reservation(Guest guest, String checkIn, String checkOut) {
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    public Reservation (Guest guest, Hotel room, String checkIn, String checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    public Guest getGuest() {
        return guest;
    }
    public Hotel getRoom() {
        return room;
    }
    public void setCheckIn(String s) {
        this.checkIn = s;
    }
    public void setCheckOut(String s) {
        this.checkOut = s;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
    public Boolean getOccupancy() {
        return this.occupancy;
    }
    public void setOccupancy(Boolean b) {
        this.occupancy = b;
    }
}