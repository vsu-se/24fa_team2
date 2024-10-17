//an auction will store bids
//should we have Users store bids to?
public class Bid {
    private double amount;
    private User user;

    public Bid(double amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}