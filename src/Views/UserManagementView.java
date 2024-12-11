package Views;
import Controllers.*;
import Models.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import java.util.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class UserManagementView extends BorderPane{
    private UserController userController;

    public UserManagementView(){
        userController = UserController.getInstance();

        setCenter(genUserGrid());
    }

    public TilePane genUserGrid(){
        TilePane userGrid = new TilePane();

        Map<String, User> users = userController.getUsers();
        for(Map.Entry<String, User> entry : users.entrySet()){
            userGrid.getChildren().add(template(entry.getValue()));
        }

        userGrid.setHgap(15);
        userGrid.setVgap(20);

        return userGrid;
    }

    public Pane template(User user){
        VBox temp = new VBox();
        temp.setPrefSize(250, 400);
        temp.setAlignment(Pos.CENTER);
        temp.setStyle("-fx-border-width: 2;"+
                      "-fx-border-style: solid inside;");

        temp.getChildren().add(new Label(String.valueOf(user.getUsername())));

        HBox hbBidRights = new HBox();
        Label lblBidRights = new Label("Bid Rights: " + user.isCanBid());
        Button btnBidRights = new Button();
        if(user.isCanBid()){
            btnBidRights.setText("Disable");
        }else{
            btnBidRights.setText("Enable");
        }
        btnBidRights.setOnAction(e -> changeBidRights(user, btnBidRights, lblBidRights));
        hbBidRights.getChildren().addAll(lblBidRights, btnBidRights);

        HBox hbSellRights = new HBox();
        Label lblSellRights = new Label("Sell Rights: " + user.isCanSell());
        Button btnSellRights = new Button();
        if(user.isCanSell()){
            btnSellRights.setText("Disable");
        }else{
            btnSellRights.setText("Enable");
        }
        btnSellRights.setOnAction(e -> changeSellRights(user, btnSellRights, lblSellRights));
        hbSellRights.getChildren().addAll(lblSellRights, btnSellRights);

        temp.getChildren().addAll(hbBidRights, hbSellRights);
        
        return temp;
    }

    public void changeBidRights(User user, Button button, Label label){
        userController.toggleUserBidPermission(user.getUsername());
        boolean newStatus = user.isCanBid();
        button.setText(newStatus ? "Disable" : "Enable");
        label.setText("Bid Rights: " + newStatus);
        button.setFocusTraversable(false);
    }
    
    public void changeSellRights(User user, Button button, Label label){
        userController.toggleUserSellPermission(user.getUsername());
        boolean newStatus = user.isCanSell();
        button.setText(newStatus ? "Disable" : "Enable");
        label.setText("Sell Rights: " + newStatus);
        button.setFocusTraversable(false);  
    }
}
