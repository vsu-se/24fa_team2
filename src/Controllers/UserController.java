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
        // Default admin
        Admin admin = new Admin("admin", "admin123");
        users.put("admin", admin);
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

    // File operations (placeholders for persistence implementation)
    public void saveUsers() {
        // Implement saving users to a file or database in the future
        System.out.println("Saving users... (to be implemented)");
    }

    public void loadUsers() {
        // Implement loading users from a file or database in the future
        System.out.println("Loading users... (to be implemented)");
    }
}
