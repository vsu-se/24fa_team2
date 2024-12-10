package Controllers;

import Models.Auction;
import Models.Item;
import Models.User;
import Models.Category;
import Models.UserController;
import java.time.LocalDateTime;
import java.util.*;

public class AuctionController {
    private Map<String, Auction> auctions;
    private static AuctionController instance;
    private UserController userController;

    private AuctionController() {
        auctions = new HashMap<>();
        userController = UserController.getInstance();
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
        if (!currentUser.getCanSell()) return null;

        String auctionId = generateAuctionId();
        Auction auction = new Auction(auctionId, item, startingPrice, buyNowPrice, 
                                    LocalDateTime.now(), endTime);
        auctions.put(auctionId, auction);
        return auction;
    }
    //add createAuction
    //add CreateAuctionResult
    //add generateItemId
    //add getActiveAuctions
    //add getUserAuctions
    //add checkAndEndAuctions

    private String generateAuctionId() {
        return "AUC" + (auctions.size() + 1);
    }
    //add CreateAuctionResult for positive/negative text output
}
