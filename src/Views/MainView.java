package Views;
import Controllers.UserController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {
    private MenuBar menuBar;
    private UserController userController;
    
    public MainView() {
        userController = UserController.getInstance();
        setupMenuBar();
         //can show auctions in main view maybe
    }

    //to non-created views
    private void setupMenuBar() {
        menuBar = new MenuBar();
        Menu auctionsMenu = new Menu("Auctions");
        Menu userMenu = new Menu("User");

        MenuItem viewAuctions = new MenuItem("View Auctions");
        viewAuctions.setOnAction(e -> showAuctionList());

        MenuItem dashboard = new MenuItem("user view?");
        dashboard.setOnAction(e -> showUser());

        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> handleLogout());

        auctionsMenu.getItems().add(viewAuctions);
        userMenu.getItems().addAll(dashboard, logout);

        //for admin only controls
        if (userController.isAdmin()) {
            Menu adminMenu = new Menu("Admin");
            MenuItem adminDashboard = new MenuItem("Admin");
            adminDashboard.setOnAction(e -> showAdmin());
            adminMenu.getItems().add(adminDashboard);
            menuBar.getMenus().add(adminMenu);
        }

        menuBar.getMenus().addAll(auctionsMenu, userMenu);
        setTop(menuBar);
    }

    private void showAuctionList() {
        //to add
    }

    private void showUser() {
        //to add
    }

    private void showAdmin() {
        //to add
    }

    private void handleLogout() {
        userController.logout();
        getScene().setRoot(new LoginView());
    }
}

