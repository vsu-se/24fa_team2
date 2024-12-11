package Views;
import Controllers.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class AuctionView extends ScrollPane{
    private UserController userController;
    private AuctionController auctionController;

    //to show auctions in mainView
    public AuctionView(){
        userController = UserController.getInstance();
        auctionController = AuctionController.getInstance();

        setFitToWidth(true);
        setContent(genAuctionGrid());
        
    }

    public TilePane genAuctionGrid(){
        TilePane auctionGrid = new TilePane();

        for(int i = 0; i < 15; i++){
            auctionGrid.getChildren().add(template(i));
        }

        auctionGrid.setHgap(15);
        auctionGrid.setVgap(20);

        return auctionGrid;
    }

    public Pane template(int i){
        VBox temp = new VBox();
        temp.getChildren().add(new Label(String.valueOf(i)));
        temp.setPrefSize(250, 400);
        temp.setAlignment(Pos.CENTER);
        temp.setStyle("-fx-border-width: 2;"+
                      "-fx-border-style: solid inside;");
        return temp;
    }
}
