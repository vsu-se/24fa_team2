import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.beans.property.*;
import java.util.List;
import java.util.ArrayList;

public class GUI extends Application {

    private IntegerProperty screenWidth = new SimpleIntegerProperty();
    private IntegerProperty screenHeight = new SimpleIntegerProperty();
    Label lbl = new Label("Start");
    private VBox mainLayout = new VBox();
    private List<Auction> activeAuctions = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // User role buttons
        HBox roleSelection = createRoleButtons();

        mainLayout.getChildren().add(roleSelection);

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

    private HBox createRoleButtons() {
        HBox roleSelection = new HBox();
        roleSelection.setPadding(new Insets(10));
        roleSelection.setSpacing(10);

        Button btnSA = new Button("System Admin");
        Button btnBuyer = new Button("Buyer");
        Button btnSeller = new Button("Seller");

        btnSA.setOnAction(e -> showSAView());
        btnBuyer.setOnAction(e -> showBuyerView());
        btnSeller.setOnAction(e -> showSellerView());

        roleSelection.getChildren().addAll(btnSA, btnBuyer, btnSeller);
        return roleSelection;
    }

    private void showSAView() {
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(createRoleButtons());
        mainLayout.getChildren().add(createCommissionPane());

        // Add placeholders for other System Admin functionalities
        Label buyerPremium = new Label("Set Buyer Premium (Placeholder)");
        mainLayout.getChildren().add(buyerPremium);
        Label showConcludedAuctions = new Label("Show Concluded Auctions (Placeholder)");
        mainLayout.getChildren().add(showConcludedAuctions);
    }

    private void showBuyerView() {
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(createRoleButtons());

        Label showActiveAuctionsLabel = new Label("Active Auctions:");
        mainLayout.getChildren().add(showActiveAuctionsLabel);

        VBox activeAuctionsBox = new VBox();
        activeAuctionsBox.setSpacing(5);

        for (Auction auction : activeAuctions) {
            Label auctionLabel = new Label(auction.toString() + " - Buy Now: $" + auction.buyNow);
            activeAuctionsBox.getChildren().add(auctionLabel);
        }

        mainLayout.getChildren().add(activeAuctionsBox);

        // Other Buyer functionalities
        Label bidOnItem = new Label("Bid on Item (Placeholder)");
        mainLayout.getChildren().add(bidOnItem);
        Label showMyBids = new Label("Show My Bids (Placeholder)");
        mainLayout.getChildren().add(showMyBids);
        Label showBuyerReport = new Label("Show Buyer's Report (Placeholder)");
        mainLayout.getChildren().add(showBuyerReport);
    }

    private void showSellerView() {
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(createRoleButtons());
        mainLayout.getChildren().add(createNewAuctionPane());

        // Add placeholders for Seller functionalities
        Label showMyAuctions = new Label("Show My Auctions (Placeholder)");
        mainLayout.getChildren().add(showMyAuctions);
        Label showSellerReport = new Label("Show Seller's Report (Placeholder)");
        mainLayout.getChildren().add(showSellerReport);
    }

    public Region createNewAuctionPane() {
        VBox newAuction = new VBox();
        // Item fields
        TextField title = new TextField("Title");
        newAuction.getChildren().add(title);
        TextField description = new TextField("Description");
        newAuction.getChildren().add(description);
        TextField category = new TextField("Category");
        newAuction.getChildren().add(category);
        TextField tags = new TextField("Tags");
        newAuction.getChildren().add(tags);
        TextField condition = new TextField("Condition");
        newAuction.getChildren().add(condition);
        TextField shippingCost = new TextField("Shipping Cost");
        newAuction.getChildren().add(shippingCost);

        // Auction fields
        TextField buyNow = new TextField("Buy Now");
        newAuction.getChildren().add(buyNow);
        TextField duration = new TextField("Duration");
        newAuction.getChildren().add(duration);

        Button addAuction = new Button("Create Auction");
        addAuction.setOnAction(e -> {
            try {
                // Create Item instance from input fields
                Item item = new Item(
                        title.getText(),
                        description.getText(),
                        category.getText(),
                        condition.getText(),
                        Double.parseDouble(shippingCost.getText())
                );

                // Parse the buy now price
                double buyNowPrice = Double.parseDouble(buyNow.getText());

                // Create Auction with Item and buyNow price
                Auction auction = new Auction(item, buyNowPrice);

                // Add the Auction to the activeAuctions list
                activeAuctions.add(auction);

                lbl.setText("Auction Created with Title: " + title.getText());

            } catch (NumberFormatException ex) {
                lbl.setText("Error: Please enter valid numbers for Shipping Cost and Buy Now price.");
            }
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

        Label commissionLabel = new Label("Set Seller's Commission");
        commissionPane.getChildren().add(commissionLabel);

        TextField username = new TextField();
        username.setPromptText("Seller Username");
        commissionPane.getChildren().add(username);

        TextField commissionRate = new TextField();
        commissionRate.setPromptText("Commission Rate (%)");
        commissionPane.getChildren().add(commissionRate);

        Button setCommission = new Button("Set Commission");
        setCommission.setOnAction(e -> lbl.setText("Commission set for " + username.getText() + ": " + commissionRate.getText() + "%"));
        commissionPane.getChildren().add(setCommission);

        return commissionPane;
    }
}
