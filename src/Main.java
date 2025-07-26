import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public void roomReservation() {
        String name;
        String email;
        String roomType;
        int id;
        int partySize;
        Hotel room;

        Guest guest = new Guest(name, email, id, partySize);

        if(roomType.equalsIgnoreCase("Standard")) {
            room = new Standard();
        } else if (roomType.equalsIgnoreCase("Deluxe")) {
            room = new Deluxe();
        } else if (roomType.equalsIgnoreCase("Suite")) {
            room = new Suite();
        } else { System.out.println("Invalid room type"); }
    }
}
