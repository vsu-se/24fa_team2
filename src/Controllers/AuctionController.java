package Controllers;

import Models.*;
import java.time.LocalDateTime;
import java.util.*;

public class AuctionController {
    private Map<String, Auction> auctions;
    private static AuctionController instance;
    private UserController userController;
    private CategoryController catController;

    private AuctionController() {
        auctions = new HashMap<>();
        userController = UserController.getInstance();
        catController = CategoryController.getInstance();
    }

    public static AuctionController getInstance() {
        if (instance == null) {
            instance = new AuctionController();
        }
        return instance;
    }

    public Auction createAuction(Item item, double startingPrice, double buyNowPrice, 
                               LocalDateTime endTime) {
        
        User currentUser = userController.getCurrentUser();
        if (!currentUser.isCanSell()) return null;

        String auctionId = generateAuctionId();
        Auction auction = new Auction(auctionId, item, startingPrice, buyNowPrice,
                LocalDateTime.now(), endTime);

        auctions.put(auctionId, auction);
        return auction;
    }

    public int getNumAuctions(){
        return auctions.size();
    }

    //add createAuction
    //add CreateAuctionResult
    //add generateItemId
    //add getActiveAuctions
    //add getUserAuctions
    //add checkAndEndAuctions


    // Create an auction with detailed user input, returning a result
    public CreateAuctionResult createNewAuction(String itemName, String itemDescription,
                                                String shippingCostStr, String endTimeStr,
                                                String categoryStr) {
        
        User currentUser = userController.getCurrentUser();
        if (!currentUser.isCanSell()) {
            return new CreateAuctionResult(false, "You do not have permission to create an auction.");
        }

        try {
            // Parse shipping cost
            double shippingCost = Double.parseDouble(shippingCostStr);

            //Parse category
            Category category = catController.getCategory(categoryStr);
            // Parse end time
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr);

            // Generate a unique item ID
            String itemId = generateItemId();

            // Create an Item object with the current user as the seller
            Item item = new Item(itemId, itemName, itemDescription, shippingCost, category, currentUser);

            // Set default pricing for the auction
            double startingPrice = 10.0;  // Example default starting price
            double buyNowPrice = 100.0;  // Example default buy-now price

            // Create the Auction object
            Auction auction = createAuction(item, startingPrice, buyNowPrice, endTime);

            if (auction != null) {
                return new CreateAuctionResult(true, "Auction created successfully! ID: " + auction.getAuctionId());
            } else {
                return new CreateAuctionResult(false, "Failed to create the auction.");
            }
        } catch (Exception e) {
            return new CreateAuctionResult(false, "Error parsing auction details: " + e.getMessage());
        }
    }


    // Generate a unique auction ID
    private String generateAuctionId() {
        return "AUC" + (auctions.size() + 1);
    }

    // Get a list of all active auctions
    public List<Auction> getActiveAuctions() {
        List<Auction> activeAuctions = new ArrayList<>();
        for (Auction auction : auctions.values()) {
            if (auction.isActive()) {
                activeAuctions.add(auction);
            }
        }
        return activeAuctions;
    }

    // Get a list of auctions for a specific user
    public List<Auction> getUserAuctions(User user) {
        List<Auction> userAuctions = new ArrayList<>();
        for (Auction auction : auctions.values()) {
            if (auction.getItem().getSeller().equals(user)) {
                userAuctions.add(auction);
            }
        }
        return userAuctions;
    }

    // Check all auctions and end those that have passed their end time
    public void checkAndEndAuctions() {
        LocalDateTime now = LocalDateTime.now();
        for (Auction auction : auctions.values()) {
            if (auction.getEndTime().isBefore(now) && auction.isActive()) {
                auction.endAuction();
            }
        }
    }

    // Generate an item ID for future use (if necessary)
    public String generateItemId() {
        return "ITEM" + UUID.randomUUID();
    }

    // Inner class to return the result of creating an auction
    public static class CreateAuctionResult {
        private boolean success;
        private String message;

        public CreateAuctionResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}


class CreateAuctionResult {
    private boolean success;
    private String message;

    public CreateAuctionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}

