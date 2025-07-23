import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Hotel Check-In System");
        Scene scene = new Scene(label, 400, 200);
        primaryStage.setTitle("Hotel Check-In System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
