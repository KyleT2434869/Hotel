public class Guest {
    private String name;
    private String email;
    private int id;
    private int partySize;

    Guest(String name, String email,int id, int partySize) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.partySize = partySize;
    }
    public final String getName() {
        return name;
    }
    public final String getEmail() {
        return email;
    }
    public final int getId() {
        return id;
    }
    public final int getPartySize() {
        return partySize;
    }
}