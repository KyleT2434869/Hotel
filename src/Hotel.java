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

    Hotel(Guest guest, String roomType, double price) {
        this.guest = guest;
        this.roomType = roomType;
        this.price = price;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getPrice() {
        return price;
    }
}
