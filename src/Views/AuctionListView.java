package Views;
import Controllers.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import java.util.*;
import Models.*;

public class AuctionListView extends ScrollPane{
    private UserController userController;
    private AuctionController auctionController;

    //to show auctions in mainView
    public AuctionListView(){
        userController = UserController.getInstance();
        auctionController = AuctionController.getInstance();

        setFitToWidth(true);
        setContent(genAuctionGrid());
        
    }

    public TilePane genAuctionGrid(){
        TilePane auctionGrid = new TilePane();

        Map<String, Auction> auctions = auctionController.getAllAuctions();
        for(Map.Entry<String, Auction> entry: auctions.entrySet()){
            auctionGrid.getChildren().add(template(entry.getValue()));
        }

        auctionGrid.setHgap(15);
        auctionGrid.setVgap(20);

        return auctionGrid;
    }

    public Pane template(Auction auction){
        VBox temp = new VBox();

        Label itemName = new Label(auction.getItem().getName());
        Label itemDesc = new Label(auction.getItem().getDescription());
        Label currPrice = new Label(String.valueOf(auction.getStartingPrice()));
        temp.getChildren().addAll(itemName, itemDesc, currPrice);
        temp.setPrefSize(250, 400);
        temp.setAlignment(Pos.CENTER);
        temp.setStyle("-fx-border-width: 2;"+
                      "-fx-border-style: solid inside;");
        return temp;
    }
}
