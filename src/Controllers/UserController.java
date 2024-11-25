package Controllers;
import java.util.HashMap;
import java.util.Map;

import Models.Admin;
import Models.User;

public class UserController {
    private Map<String, User> users;
    private User currentUser;
    private static UserController instance;
    
    private UserController() {
        users = new HashMap<>();
        //efault admin
        Admin admin = new Admin("admin", "admin123");
        users.put("admin", admin);
    }
    
    //one UserController
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    //user authentication
    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.verifyPassword(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    //user registers
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false;  //if username exists
        }
        
        User newUser = new User(username, password);
        users.put(username, newUser);
        return true;
    }

    // Current User Management
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAdmin() {
        return currentUser instanceof Admin;
    }

    // Permission Management
    public boolean toggleUserSellPermission(String username) {
        if (!isAdmin()) return false; //not admin
        
        User user = users.get(username);
        if (user != null && !(user instanceof Admin)) { 
            user.setCanSell(!user.getCanSell()); //flip permission
            return true;
        }
        return false;
    }

    public boolean toggleUserBidPermission(String username) {
        if (!isAdmin()) return false; //not admin
        
        User user = users.get(username);
        if (user != null && !(user instanceof Admin)) {
            user.setCanBid(!user.getCanBid()); //flip permission
            return true;
        }
        return false;
    }

    // File Operations
    public void saveUsers() {
        // some utility controller maybe to handle
    }

    public void loadUsers() {
        // will be for loading users from file (persistence)
        
    }
}