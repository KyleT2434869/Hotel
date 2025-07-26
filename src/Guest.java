/*
UML Diagram

Guest

- name : String
- email : String
- id : int
- partySize : int

+ Guest(name : String, email : String, partySize : int)
+ getName() : String : final
+ getEmail() : String : final
+ getPartSize() : int : final
+ printInfo() : void
 */

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
}