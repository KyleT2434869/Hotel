/*
UML Diagram

Hotel

- guest : Guest
- roomType : String
- price : double

+ Hotel(guest : Guest, roomType : String, price : double)
+ getRoomType() : String
+ getPrice() : double
 */

public class Hotel {
    Guest guest;
    String roomType;
    double price;

    Hotel() {}
    Hotel(Guest guest, String roomType, double price) {
        this.guest = guest;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getPrice() {
        return price;
    }
}
