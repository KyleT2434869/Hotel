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

public class SignupController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField nameText;
    @FXML
    TextField emailText;
    @FXML
    TextField usernameText;
    @FXML
    TextField passwordText;

    Guest guest;
    Login log;

    public void Signup(ActionEvent event) throws IOException, SQLException {
            
            log = new Login(usernameText.getText(),passwordText.getText());
            guest = new Guest(nameText.getText(), emailText.getText());

            GuestManager g1 = new GuestManager();
            g1.signUp(log,guest);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            root = loader.load();

            MenuController menuController = new MenuController();
            menuController.setGuest(guest);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
