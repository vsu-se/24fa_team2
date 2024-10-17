import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private List<Bid> activeBids;
    private List<Transaction> purchaseHistory;
    private boolean sellRights;
    private boolean buyRights;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.activeBids = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
        this.sellRights = false;  // Default to false
        this.buyRights = false;  
    }

    // Getters and setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Bid> getActiveBids() {
        return activeBids;
    }

    public List<Transaction> getPurchaseHistory() {
        return purchaseHistory;
    }

    public boolean hasSellRights() {
        return sellRights;
    }

    public void setSellRights(boolean sellRights) {
        this.sellRights = sellRights;
    }

    public boolean hasBuyRights() {
        return buyRights;
    }

    public void setBuyRights(boolean buyRights) {
        this.buyRights = buyRights;
    }

    // The generic method as shown in the diagram
    public <T> T method(T type) {
        // Implementation depends on the specific use case
        return type;
    }

    // Additional helpful methods
    public void addActiveBid(Bid bid) {
        activeBids.add(bid);
    }

    public void removeActiveBid(Bid bid) {
        activeBids.remove(bid);
    }

    public void addPurchase(Transaction transaction) {
        purchaseHistory.add(transaction);
    }
}
