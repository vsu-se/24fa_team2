package Controllers;

import Models.Auction;
import Models.Bid;
import Models.User;
import java.util.*;

public class BidController {
    // Map to store bids by their ID
    private Map<String, Bid> bids;
    private static BidController instance;
    private UserController userController;
    private AuctionController auctionController;

    // Private constructor
    private BidController() {
        bids = new HashMap<>();
        userController = UserController.getInstance();
        auctionController = AuctionController.getInstance();
    }

    // Singleton instance method
    public static synchronized BidController getInstance() {
        if (instance == null) {
            instance = new BidController();
        }
        return instance;
    }

    // Method to place a bid
    public Bid placeBid(String auctionId, double amount) {
        // Ensure the current user is logged in
        User currentUser = userController.getCurrentUser();
        if (currentUser == null || !currentUser.getCanBid()) {
            throw new IllegalStateException("User must be logged in and have bidding permissions.");
        }

        // Retrieve the auction
        Auction auction = auctionController.getAuction(auctionId);
        if (auction == null) {
            throw new NoSuchElementException("Auction with ID '" + auctionId + "' does not exist.");
        }

        // Validate the bid amount
        if (amount <= auction.getHighestBid()) {
            throw new IllegalArgumentException("Bid amount must be higher than the current highest bid.");
        }

        // Create a new bid
        String bidId = generateBidId();
        Bid bid = new Bid(bidId, auction, currentUser, amount, LocalDateTime.now());
        bids.put(bidId, bid);

        // Update the auction's highest bid and add the bid
        auction.addBid(bid);

        return bid;
    }

    // Retrieve a specific bid by ID
    public Bid getBid(String bidId) {
        Bid bid = bids.get(bidId);
        if (bid == null) {
            throw new NoSuchElementException("Bid with ID '" + bidId + "' not found.");
        }
        return bid;
    }

    // Retrieve all bids for a specific auction
    public List<Bid> getBidsForAuction(String auctionId) {
        Auction auction = auctionController.getAuction(auctionId);
        if (auction == null) {
            throw new NoSuchElementException("Auction with ID '" + auctionId + "' does not exist.");
        }

        return new ArrayList<>(auction.getBids());
    }

    // Generate a unique bid ID
    private String generateBidId() {
        return UUID.randomUUID().toString();
    }

    // Optional: Method to get all bids placed by a user
    public List<Bid> getBidsByUser(User user) {
        List<Bid> userBids = new ArrayList<>();
        for (Bid bid : bids.values()) {
            if (bid.getUser().equals(user)) {
                userBids.add(bid);
            }
        }
        return userBids;
    }
}
