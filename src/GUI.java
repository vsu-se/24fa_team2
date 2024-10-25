import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GUI extends Application {

    private IntegerProperty screenWidth = new SimpleIntegerProperty();
    private IntegerProperty screenHeight = new SimpleIntegerProperty();
    Label lbl = new Label("Start");

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Combine the auction creation pane and commission setting pane into a main layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().add(createNewAuctionPane());
        mainLayout.getChildren().add(createCommissionPane());

        Scene scene = new Scene(mainLayout, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        screenWidth.bind(scene.widthProperty());
        screenHeight.bind(scene.heightProperty());
        primaryStage.setTitle("Auction System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Region createNewAuctionPane() {
        VBox newAuction = new VBox();
        // Item
        // title
        TextField title = new TextField("Title");
        newAuction.getChildren().add(title);
        // description
        TextField description = new TextField("Description");
        newAuction.getChildren().add(description);
        // category
        TextField category = new TextField("Category");
        newAuction.getChildren().add(category);
        // tags
        TextField tags = new TextField("Tags");
        newAuction.getChildren().add(tags);
        // condition
        TextField condition = new TextField("Condition");
        newAuction.getChildren().add(condition);
        // shipping cost
        TextField shippingCost = new TextField("Shipping Cost");
        newAuction.getChildren().add(shippingCost);

        // Auction
        // buy now
        TextField buyNow = new TextField("Buy Now");
        newAuction.getChildren().add(buyNow);
        // duration
        TextField duration = new TextField("Duration");
        newAuction.getChildren().add(duration);

        Button addAuction = new Button("Create Auction");

        addAuction.setOnAction(e -> {
            // Placeholder for auction creation logic
            lbl.setText("Auction Created with Title: " + title.getText());
        });

        newAuction.getChildren().add(addAuction);
        newAuction.getChildren().add(lbl);

        return newAuction;
    }

    public Region createCommissionPane() {
        VBox commissionPane = new VBox();
        commissionPane.setPadding(new Insets(10));
        commissionPane.setSpacing(10);
        commissionPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px;");

        // Add a label
        Label commissionLabel = new Label("Set Seller's Commission");
        commissionPane.getChildren().add(commissionLabel);

        // Username field
        TextField username = new TextField();
        username.setPromptText("Seller Username");
        commissionPane.getChildren().add(username);

        // Commission rate field
        TextField commissionRate = new TextField();
        commissionRate.setPromptText("Commission Rate (%)");
        commissionPane.getChildren().add(commissionRate);

        // Button to set commission
        Button setCommission = new Button("Set Commission");

        setCommission.setOnAction(e -> {
            // Placeholder action to display the entered values
            lbl.setText("Commission set for " + username.getText() + ": " + commissionRate.getText() + "%");
        });

        commissionPane.getChildren().add(setCommission);

        return commissionPane;
    }
}
