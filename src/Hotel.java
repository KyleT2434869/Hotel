public class Hotel {
    Guest guest;
    String roomType;
    double price;

    Hotel(Guest guest) {
        this.guest = guest;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getPrice() {
        return price;
    }
}
