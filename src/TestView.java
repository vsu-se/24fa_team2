import javafx.scene.layout.*;
import javafx.scene.control.*;

public class TestView extends VBox{
    
    TestView(TestModel model, Runnable addFive){
        TextField numberTF = new TextField();
        numberTF.textProperty().bindBidirectional(model.numberProperty());
        HBox dataBox = new HBox(4, new Label("Enter a number: "), numberTF);
        Button btn = new Button("Add 5");
        btn.setOnAction(e -> addFive.run());
        getChildren().addAll(dataBox, btn);
    }
}
