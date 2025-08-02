import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Reservations {
    private Guest guest;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservations(Guest guest, Room room, LocalDate checkIn, LocalDate checkOut) {
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
    public String getCheckIn() {
        return checkIn;
    }
    public String getCheckOut() {
        return checkOut;
    }
}
