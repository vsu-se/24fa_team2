import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;


public class TestGUI extends Application {
    
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(new TestController().getView(), 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
