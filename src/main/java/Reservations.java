import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservations {
    private Guest guest;
    private Room room;
    private String checkIn;
    private String checkOut;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public Reservations(Guest guest, Room room, String checkIn, String checkOut) {
        this.guest = guest;
        this.room = room;
        this.checkIn = formatter.format(checkIn);
        this.checkOut = formatter.format(checkOut);
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public String getCheckIn() {
        return checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
}
