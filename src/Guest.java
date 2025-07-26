public class Guest {
    private String name;
    private String email;
    private int partySize;

    Guest(String name, String email, int partySize) {
        this.name = name;
        this.email = email;
        this.partySize = partySize;
    }
    public final String getName() {
        return name;
    }
    public final String getEmail() {
        return email;
    }
    public final int getPartySize() {
        return partySize;
    }
}