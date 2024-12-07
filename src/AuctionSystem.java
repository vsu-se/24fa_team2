
import Views.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuctionSystem extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Set up the initial scene with LoginView
        Scene scene = new Scene(new LoginView(), 800, 600);
        primaryStage.setTitle("Auction System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}