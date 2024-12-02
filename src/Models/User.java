package Models;

public class User {
    private String username;
    private String password;
    private boolean canSell;
    private boolean canBid;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.canSell = false; // Default value
        this.canBid = false;  // Default value
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCanSell() {
        return canSell;
    }

    public void setCanSell(boolean canSell) {
        this.canSell = canSell;
    }

    public boolean isCanBid() {
        return canBid;
    }

    public void setCanBid(boolean canBid) {
        this.canBid = canBid;
    }
    // verifyPassword method
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

}
