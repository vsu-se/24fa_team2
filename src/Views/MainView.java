package Views;
import Controllers.UserController;
import javafx.scene.control.*; 
import javafx.scene.layout.*;

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
        viewAuctions.setOnAction(e -> switchAuctionListView());

        MenuItem dashboard = new MenuItem("user view?");
        dashboard.setOnAction(e -> showUser());

        MenuItem createAuction = new MenuItem("Create Auction");
        createAuction.setOnAction(e -> switchCreateAuctionView());

        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> handleLogout());

        auctionsMenu.getItems().add(viewAuctions);
        userMenu.getItems().addAll(dashboard, createAuction, logout);

        //for admin only controls
        if (userController.isAdmin()) {
            Menu adminMenu = new Menu("Admin");

            MenuItem adminDashboard = new MenuItem("Admin");
            adminDashboard.setOnAction(e -> showAdmin());

            MenuItem manageCategories = new MenuItem("Manage Categories");
            manageCategories.setOnAction(e -> switchCategoryManagement());

            MenuItem manageUsers = new MenuItem("Manage Users");
            manageUsers.setOnAction(e -> switchUserManagement());

            adminMenu.getItems().addAll(adminDashboard, manageCategories, manageUsers);

            menuBar.getMenus().add(adminMenu);
        }

        menuBar.getMenus().addAll(auctionsMenu, userMenu);
        setTop(menuBar);
    }

    private void switchAuctionListView() {
        //to add
        getScene().setRoot(new AuctionListView());
    }

    private void switchCreateAuctionView(){
        getScene().setRoot(new CreateAuctionView());
    }

    private void showUser() {
        //to add
    }

    private void showAdmin() {
        //to add
    }

    private void switchCategoryManagement(){
        getScene().setRoot(new CategoryView());
    }

    private void switchUserManagement(){
        getScene().setRoot(new UserManagementView());
    }

    private void handleLogout() {
        userController.logout();
        getScene().setRoot(new LoginView());
    }
}

