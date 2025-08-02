import java.time.LocalDate;

public class Reservation {
    private Guest guest;
    private Hotel room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation (Guest guest, Hotel room, LocalDate checkIn, LocalDate checkOut) {
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
    public LocalDate getCheckIn() {
        return checkIn;
    }
    public LocalDate getCheckOut() {
        return checkOut;
    }
}