import java.util.Date;

public class Reservations {
    private Guest guest;
    private Room room;
    private Date checkIn;
    private Date checkOut;

    public Reservations(Guest guest, Room room, Date checkIn, Date checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public Date getCheckIn() {
        return checkIn;
    }
    public Date getCheckOut() {
        return checkOut;
    }
}
