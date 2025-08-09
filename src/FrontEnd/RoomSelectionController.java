package FrontEnd;
import BackEnd.*;
import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoomSelectionController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    Hotel room;
    Guest guest;
    Reservation r;
    ReservationManager rm;

    @FXML
    TextField checkInText;
    @FXML
    TextField checkOutText;

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setReservation_Standard(ActionEvent event) throws IOException {
        room = new Standard();
        r = new Reservation(guest,room, checkInText.getText(),checkOutText.getText());
        rm = new ReservationManager();
        rm.addReservation(r);

        root = FXMLLoader.load(getClass().getResource("ReservationConfirmation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setReservation_Deluxe(ActionEvent event) throws IOException  {
        room = new Deluxe();
        r = new Reservation(guest,room,checkInText.getText(),checkOutText.getText());
        rm = new ReservationManager();
        rm.addReservation(r);

        Parent root = FXMLLoader.load(getClass().getResource("ReservationConfirmation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setReservation_Suite(ActionEvent event) throws IOException {
        room = new Suite();
        r = new Reservation(guest,room,checkInText.getText(),checkOutText.getText());
        rm = new ReservationManager();
        rm.addReservation(r);

        Parent root = FXMLLoader.load(getClass().getResource("ReservationConfirmation.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
