package Views;
import java.time.LocalDateTime;

import Controllers.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.*;

public class CreateAuctionView extends VBox{
    private UserController userController;
    private AuctionController auctionController;
    private CategoryController catController;

    public CreateAuctionView(){
        userController = UserController.getInstance();
        auctionController = AuctionController.getInstance();
        catController = CategoryController.getInstance();

        /*
        HBox hbItemID = new HBox();
        Label lblItemID = new Label("Item ID: ");
        TextField txfItemID = new TextField();
        hbItemID.getChildren().addAll(lblItemID, txfItemID);
        */

        HBox hbItemName = new HBox();
        Label lblItemName = new Label("Item Name: ");
        TextField txfItemName = new TextField();
        hbItemName.getChildren().addAll(lblItemName, txfItemName);
        
        HBox hbItemDesc = new HBox();
        Label lblItemDesc = new Label("Item Description: ");
        TextField txfItemDesc = new TextField();
        hbItemDesc.getChildren().addAll(lblItemDesc, txfItemDesc);
        
        HBox hbItemShippingCost = new HBox();
        Label lblItemShippingCost = new Label("Item Shipping Cost: ");
        TextField txfItemShippingCost = new TextField();
        hbItemShippingCost.getChildren().addAll(lblItemShippingCost, txfItemShippingCost);
        
        HBox hbItemCat = new HBox();
        Label lblItemCat = new Label("Item Category: ");
        ComboBox cbItemCat = new ComboBox(FXCollections.observableArrayList(catController.getAllCategories()));
        hbItemCat.getChildren().addAll(lblItemCat, cbItemCat);
        
        HBox hbStartingPrice = new HBox();
        Label lblStartingPrice = new Label("Starting Price: ");
        TextField txfStartingPrice = new TextField();
        hbStartingPrice.getChildren().addAll(lblStartingPrice, txfStartingPrice);
        
        HBox hbBuyNow = new HBox();
        Label lblBuyNow = new Label("Buy Now: ");
        TextField txfBuyNow = new TextField();
        hbBuyNow.getChildren().addAll(lblBuyNow, txfBuyNow);
        
        HBox hbEndTime = new HBox();
        Label lblEndTime = new Label("End Time: ");
        TextField txfEndTime = new TextField();
        hbEndTime.getChildren().addAll(lblEndTime, txfEndTime);

        Button btnCreateAuction = new Button("Create Auction");
        btnCreateAuction.setOnAction(e -> createAuction(txfItemName.getText(),
                                                        txfItemDesc.getText(),
                                                        txfItemShippingCost.getText(),
                                                        cbItemCat.getValue().toString(),
                                                        txfStartingPrice.getText(),
                                                        txfBuyNow.getText(),
                                                        txfEndTime.getText()));

        getChildren().addAll(hbItemName, hbItemDesc, hbItemShippingCost, 
                             hbItemCat, hbStartingPrice, hbBuyNow, hbEndTime, btnCreateAuction);
    }

    public void createAuction(String itemName, String description, String shippingCost, 
                                String category, String startingPrice, String buyNowPrice, String endTime){
        //auctionController.createNewAuction(itemName, description, shippingCost, 
                                        //endTime, category);
    }
}
