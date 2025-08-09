package FrontEnd;
import BackEnd.*;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class SearchController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Guest guest;
    private Reservation oldReservation;
    private ReservationManager reservationManager = new ReservationManager();

    @FXML
    private TextField emailText;
    @FXML
    private TextField checkinText;
    @FXML
    private TextField checkoutText;

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void searchReservation(ActionEvent event) throws IOException {
       this.oldReservation = reservationManager.getReservation(guest, checkinText.getText(), checkoutText.getText());
       FXMLLoader loader = new FXMLLoader(getClass().getResource("display.fxml"));
       root = loader.load();
        
       DisplayController displayController = loader.getController();
       displayController.setGuest(this.guest);
       displayController.setOldReservation(this.oldReservation);
       displayController.display();

       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show(); 

    }
}
