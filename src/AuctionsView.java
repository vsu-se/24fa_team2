import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class AuctionsView {
    private final AnchorPane rootPane;
    private List<Auction> activeAuctions = new ArrayList<>();

    public AuctionsView(){
        this.rootPane = new AnchorPane();

        Label showActiveAuctionsLabel = new Label("Active Auctions:");
        showActiveAuctionsLabel.setStyle("-fx-font-weight: bold;"); // Make the text bold
        rootPane.getChildren().add(showActiveAuctionsLabel);

        VBox activeAuctionsBox = new VBox();
        activeAuctionsBox.setSpacing(10); // Increase spacing between auction boxes

        for (Auction auction : activeAuctions) {
            VBox auctionBox = new VBox();
            auctionBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10;"); // Add border and padding

            Label auctionLabel = new Label(auction.toString() + " - Buy Now: $" + auction.buyNow);
            auctionBox.getChildren().add(auctionLabel);

            activeAuctionsBox.getChildren().add(auctionBox);
        }

        AnchorPane.setRightAnchor(activeAuctionsBox, 10.0);

        rootPane.getChildren().add(activeAuctionsBox);

        HBox buyFunctions = new HBox();
        // Other Buyer functionalities
        Label bidOnItem = new Label("Bid on Item (Placeholder)");
        buyFunctions.getChildren().add(bidOnItem);
        Label showMyBids = new Label("Show My Bids (Placeholder)");
        buyFunctions.getChildren().add(showMyBids);
        Label showBuyerReport = new Label("Show Buyer's Report (Placeholder)");
        buyFunctions.getChildren().add(showBuyerReport);

        AnchorPane.setBottomAnchor(buyFunctions, 10.0);

        rootPane.getChildren().add(buyFunctions);
    }

    public Pane getRootPane(){
        return rootPane;
    }
}
