package Controllers;

import Models.Admin;
import Models.User;
import Utils.FileManager;
import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, User> users;
    private User currentUser;
    private static UserController instance;
    private FileManager fileManager;

    private UserController() {
        fileManager = FileManager.getInstance();
        users = new HashMap<>();
        loadUsers();  // Load users when controller is initialized
    }

    // Singleton pattern to ensure one UserController instance
    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    // User authentication
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

    public Map<String, User> getUsers(){
        return users;
    }

    // Register a new user
    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Username already exists
        }

        User newUser = new User(username, password);
        users.put(username, newUser);
   

        return true;
    }

    // Current user management
    public User getCurrentUser() {
        return currentUser;
    }

    public int getNumUsers(){
        return users.size();
    }

    public boolean isAdmin() {
        return currentUser instanceof Admin;
    }

    // Permission management
    public boolean toggleUserSellPermission(String username) {
        if (!isAdmin()) return false; // Only admins can toggle permissions

        User user = users.get(username);
        if (user != null && !(user instanceof Admin)) {
            user.setCanSell(!user.isCanSell()); // Flip permission
            return true;
        }
        return false;
    }

    public boolean toggleUserBidPermission(String username) {
        if (!isAdmin()) return false; // Only admins can toggle permissions

        User user = users.get(username);
        if (user != null && !(user instanceof Admin)) {
            user.setCanBid(!user.isCanSell()); // Flip permission
            return true;
        }
        return false;
    }

    public void saveUsers() {
        fileManager.saveUsers(users);
    }

    private void loadUsers() {
        users = fileManager.loadUsers();
        if (users.isEmpty()) {
            // Create default admin if no users exist
            Admin admin = new Admin("admin", "admin123");
            users.put("admin", admin);
            saveUsers();
        }
    }

    public User getUser(String username){
        return users.get(username);
    }
}
