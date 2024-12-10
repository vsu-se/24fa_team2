package Models;
import java.time.LocalDateTime;

public class Bid {
    private String bidId;
    private Auction auction;
    private User bidder;
    private double amount;
    private LocalDateTime timestamp;

    public Bid(String bidId, Auction auction, User bidder, double amount) {
        this.bidId = bidId;
        this.auction = auction;
        this.bidder = bidder;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public String getBidId() {
        return bidId;
    }

    public Auction getAuction() {
        return auction;
    }

    public User getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }
}
