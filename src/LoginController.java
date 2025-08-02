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

public class LoginController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField userNameText;
    @FXML
    TextField passwordText;
    @FXML
    TextField printText;
   
    Guest guest;
    Login log;
    

    public void logIn(ActionEvent event) throws IOException, SQLException  {
            log = new Login(userNameText.getText(),passwordText.getText());
            GuestManager g1 = new GuestManager();

            if(g1.login(log)){ 
                guest = g1.getInfo(log);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));	
		        root = loader.load();	
                
                MenuController menuController = loader.getController();
                menuController.setGuest(guest);
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else { 
                printText.setText("Wrong information");
            }
        }
    }
