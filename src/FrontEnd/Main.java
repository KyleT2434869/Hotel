package FrontEnd;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);

        // Leave this here for testing reservation related methods.
        
        /*Guest guest = new Guest("Bruce Wayne","Batman.inc",3);
        Hotel room = new Standard();
        Reservation r = new Reservation(guest,room,"24/03/2000","03/24/2000");
        ReservationManager rm = new ReservationManager();
        rm.addReservation(r); */

        /*Guest guest = new Guest("Bruce Wayne","Batman.inc",3);
        ReservationManager r = new ReservationManager();
        Reservation oldReservation = r.getReservation(guest,"24/03/2000","03/24/2000");
        System.out.println(oldReservation.getRoom().getRoomType() + " " +oldReservation.getGuest().getName() +" " + oldReservation.getCheckIn());
        Hotel room = new Deluxe();
        Reservation newReservation = new Reservation(guest,room,"08/16/1985","16/08/1988");
        r.updateReservation(newReservation, oldReservation); */

    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
