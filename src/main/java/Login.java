import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private String username;
    private String password;
    private int hash;

    public Login(String s1, String s2) {
        this.username = s1;
        this.password = s2;
    }

    public void setUsername(String u) {
        this.username = u;
    }

    public void setPassword(String p) {
        this.password = p;
    }

    public int getHashcode() {
        return this.password.hashCode();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUserId() {
        try(Connection con = DriverManager.getConnection("jdbc:sqlite:hotel.db")) {

            try(PreparedStatement prst = con.prepareStatement("SELECT * FROM login WHERE username = ?")) {
                prst.setString(1, this.getUsername());

                try(ResultSet rs = prst.executeQuery()) {
                    if(rs.next()) {
                        return rs.getString(2);
                    }
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}