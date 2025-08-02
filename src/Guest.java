public class Guest {
    private String name;
    private int partySize;
    private String email;
    private String roomType;
    private Payment payment;

    public Guest(String s1, String u1) {
        this.name = s1;
        this.email = u1;
    }

    public Guest(String s1, String s2, int num) {
        this.email = s1;
        this.partySize = num;
        this.name = s2;
    }

    public String getName() {
        return this.name;
    }

    public int getPartySize() {
        return this.partySize;
    }

    public String getEmail() {
        return this.email;
    }

    public void setRoomType(String s) {
        this.roomType = s;
    }

    public String getRoomType() {
        return this.roomType;
    }
}
