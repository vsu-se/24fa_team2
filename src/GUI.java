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
    private Controller control;
    private SA systemAdmin = new SA();

    @Override
    public void start(Stage primaryStage) throws Exception {
        control = new Controller(this);
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
        mainLayout.getChildren().add(createPremiumPane());
        mainLayout.getChildren().add(createCategoryManagementPane());
        mainLayout.getChildren().add(lbl);
        Label showConcludedAuctions = new Label("Show Concluded Auctions (Placeholder)");
        mainLayout.getChildren().add(showConcludedAuctions);
    }

    //category management pane
    private Region createCategoryManagementPane() {
        VBox categoryPane = new VBox();
        categoryPane.setPadding(new Insets(10));
        categoryPane.setSpacing(10);
        categoryPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px;");

        Label categoryLabel = new Label("Category Management");
        categoryLabel.setStyle("-fx-font-weight: bold");

        // Add Category section
        TextField categoryNameField = new TextField();
        categoryNameField.setPromptText("Enter category name");

        Button addCategoryBtn = new Button("Add Category");
        addCategoryBtn.setOnAction(e -> {
            String category = categoryNameField.getText().trim();
            if (!category.isEmpty()) {
                systemAdmin.addCategory(category);
                categoryNameField.clear();
            }
        });

        // Remove Category section
        TextField removeCategoryField = new TextField();
        removeCategoryField.setPromptText("Enter category to remove");

        Button removeCategoryBtn = new Button("Remove Category");
        removeCategoryBtn.setOnAction(e -> {
            String category = removeCategoryField.getText().trim();
            if (!category.isEmpty()) {
                systemAdmin.removeCategory(category);
                removeCategoryField.clear();
            }
        });

        // Add all components to the pane
        categoryPane.getChildren().addAll(
            categoryLabel,
            categoryNameField,
            addCategoryBtn,
            removeCategoryField,
            removeCategoryBtn
        );

        return categoryPane;
    }

    private void showBuyerView() {
        mainLayout.getChildren().clear();
        mainLayout.getChildren().add(createRoleButtons());

        // Bolded label for Active Auctions
        Label showActiveAuctionsLabel = new Label("Active Auctions:");
        showActiveAuctionsLabel.setStyle("-fx-font-weight: bold;"); // Make the text bold
        mainLayout.getChildren().add(showActiveAuctionsLabel);

        VBox activeAuctionsBox = new VBox();
        activeAuctionsBox.setSpacing(10); // Increase spacing between auction boxes

        for (Auction auction : activeAuctions) {
            VBox auctionBox = new VBox();
            auctionBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10;"); // Add border and padding

            Label auctionLabel = new Label(auction.toString() + " - Buy Now: $" + auction.buyNow);
            auctionBox.getChildren().add(auctionLabel);

            activeAuctionsBox.getChildren().add(auctionBox);
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

                //This needs to be handled by Controller
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

        // TextField username = new TextField();
        // username.setPromptText("Seller Username");
        // commissionPane.getChildren().add(username);

        TextField commissionRate = new TextField();
        commissionRate.setPromptText("Commission Rate (%)");
        commissionPane.getChildren().add(commissionRate);

        Button setCommission = new Button("Set Commission");
        setCommission.setOnAction(e -> control.setCommission(commissionRate.getText()));
        commissionPane.getChildren().add(setCommission);

        return commissionPane;
    }

    public Region createPremiumPane(){
        VBox premiumPane = new VBox();
        premiumPane.setPadding(new Insets(10));
        premiumPane.setSpacing(10);
        premiumPane.setStyle("-fx-border-color: gray; -fx-border-width: 1px;");

        Label premiumLabel = new Label("Set Buyer's Premium");
        premiumPane.getChildren().add(premiumLabel);

        // TextField username = new TextField();
        // username.setPromptText("Seller Username");
        // commissionPane.getChildren().add(username);

        TextField premiumRate = new TextField();
        premiumRate.setPromptText("Premium Rate (%)");
        premiumPane.getChildren().add(premiumRate);

        Button setPremium = new Button("Set Premium");
        setPremium.setOnAction(e -> control.setPremium(premiumRate.getText()));
        premiumPane.getChildren().add(setPremium);

        return premiumPane;
    }
}
