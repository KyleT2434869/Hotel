package FrontEnd;
import BackEnd.*;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class DisplayController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Reservation oldReservation;
    private Reservation newReservation;
    private Guest guest;

    @FXML
    Label oldRoomLabel;
    @FXML
    Label oldCheckInLabel;
    @FXML
    Label oldCheckOutLabel;
    
    @FXML
    TextField newRoomText;
    @FXML
    TextField newCheckInText;
    @FXML
    TextField newCheckOutText;

    public void setOldReservation(Reservation oldreservation) {
        this.oldReservation = oldreservation;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void display(){
        oldRoomLabel.setText(oldReservation.getRoom().getRoomType());
        oldCheckInLabel.setText(oldReservation.getCheckIn());
        oldCheckOutLabel.setText(oldReservation.getCheckOut());
    }

    public void update(ActionEvent event) throws IOException {
        if(newRoomText.getText().equals("Standard")) {
            Hotel room = new Standard();
            this.newReservation = new Reservation(guest, room,newCheckInText.getText(),newCheckOutText.getText());
            ReservationManager r1 = new ReservationManager();
            r1.updateReservation(newReservation, oldReservation);
        }
        else if(newRoomText.getText().equals("Deluxe")) {
            Hotel room = new Deluxe();
            this.newReservation = new Reservation(guest, room,newCheckInText.getText(),newCheckOutText.getText());
            ReservationManager r1 = new ReservationManager();
            r1.updateReservation(newReservation, oldReservation);
        }
        else {
            Hotel room = new Suite();
            this.newReservation = new Reservation(guest, room,newCheckInText.getText(),newCheckOutText.getText());
            ReservationManager r1 = new ReservationManager();
            r1.updateReservation(newReservation, oldReservation);
        }
        
        root = FXMLLoader.load(getClass().getResource("ReservationConfirmation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    public void switchScene(ActionEvent event) throws IOException {
        
        
    }


}
