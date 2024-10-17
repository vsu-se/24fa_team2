
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GUI extends Application{

    private IntegerProperty screenWidth = new SimpleIntegerProperty();
    private IntegerProperty screenHeight = new SimpleIntegerProperty();
    Controller control = new Controller(this);
    Label lbl = new Label("Start");

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Left Portion User summary
        //Right Portion GridPane of Auctions
        Scene scene = new Scene(createNewAuctionPane(), 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        screenWidth.bind(scene.widthProperty());
        screenHeight.bind(scene.heightProperty());
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Region createNewAuctionPane(){
        VBox newAuction = new VBox();
        //Item
            //title
            TextField title = new TextField("Title");
            newAuction.getChildren().add(title);
            //description
            TextField description = new TextField("Description");
            newAuction.getChildren().add(description);
            //category
            TextField category = new TextField("Category");
            newAuction.getChildren().add(category);
            //tags
            TextField tags = new TextField("Tags");
            newAuction.getChildren().add(tags);
            //condition
            TextField condition = new TextField("Condition");
            newAuction.getChildren().add(condition);
            //shippingcost
            TextField shippingCost = new TextField("Shipping Cost");
            newAuction.getChildren().add(shippingCost);

        //Auction
            //buyNow
            TextField buyNow = new TextField("Buy Now");
            newAuction.getChildren().add(buyNow);
            //duration
            TextField duration = new TextField("Duration");
            newAuction.getChildren().add(duration);

            Button addAuction = new Button("Create Auction");

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e){
                    control.createAuctionData(title.getText(), description.getText(), category.getText(), tags.getText(), condition.getText(), shippingCost.getText(), buyNow.getText(), duration.getText());
                }
            };

            addAuction.setOnAction(event);
            newAuction.getChildren().add(addAuction);
            newAuction.getChildren().add(lbl);

        return newAuction;
    }
}