public class Guest {
    private String name;
    private String email;
    private int partySize;

    Guest(String name, String email, int partySize) {
        setName(name);
        setEmail(email);
        setPartySize(partySize);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
            this.email = email;
    }
    public void setPartySize(int partySize) {
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
