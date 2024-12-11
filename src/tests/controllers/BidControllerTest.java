package tests.controllers;

import Models.Auction;
import Models.Bid;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controllers.*;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BidControllerTest {

    private BidController bidController;
    private UserController userController;
    private AuctionController auctionController;
    private Auction auction;
    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize controllers
        bidController = BidController.getInstance();
        userController = UserController.getInstance();
        auctionController = AuctionController.getInstance();

        // Create a user with bidding permission
        user = new User("user1", "password123");
        user.setCanBid(true);
        userController.setCurrentUser(user);

        // Create an auction
        auction = new Auction("AUC1", null, 10.0, 100.0, null, null);
        auctionController.createAuction(auction.getItem(), auction.getStartingPrice(),
                auction.getBuyNowPrice(), auction.getEndTime());  // assuming proper item and time setup
    }

    @Test
    public void testPlaceBid_Success() {
        // Arrange
        double bidAmount = 20.0;

        // Act
        Bid bid = bidController.placeBid("AUC1", bidAmount);

        // Assert
        assertNotNull(bid);
        assertEquals(bidAmount, bid.getAmount());
        assertEquals(user, bid.getBidder());
        assertEquals("AUC1", bid.getAuction().getAuctionId());
        assertTrue(auction.getBids().contains(bid));
    }

    @Test
    public void testPlaceBid_UserNotLoggedIn() {
        // Arrange
        userController.setCurrentUser(null);  // Simulate user not being logged in

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> bidController.placeBid("AUC1", 20.0));
    }

    @Test
    public void testPlaceBid_UserNotAllowedToBid() {
        // Arrange
        user.setCanBid(false);  // Disable bidding for the user

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> bidController.placeBid("AUC1", 20.0));
    }

    @Test
    public void testPlaceBid_AuctionNotExist() {
        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> bidController.placeBid("AUC999", 20.0));
    }

    @Test
    public void testPlaceBid_InvalidAmount() {
        // Arrange
        double bidAmount = 5.0;  // Lower than the starting price

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> bidController.placeBid("AUC1", bidAmount));
    }

    @Test
    public void testGetBid_Success() {
        // Arrange
        double bidAmount = 20.0;
        Bid placedBid = bidController.placeBid("AUC1", bidAmount);

        // Act
        Bid retrievedBid = bidController.getBid(placedBid.getBidId());

        // Assert
        assertEquals(placedBid, retrievedBid);
    }

    @Test
    public void testGetBid_BidNotExist() {
        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> bidController.getBid("INVALID_BID_ID"));
    }

    @Test
    public void testGetBidsForAuction() {
        // Arrange
        double firstBidAmount = 20.0;
        double secondBidAmount = 30.0;
        bidController.placeBid("AUC1", firstBidAmount);
        bidController.placeBid("AUC1", secondBidAmount);

        // Act
        List<Bid> bidsForAuction = bidController.getBidsForAuction("AUC1");

        // Assert
        assertEquals(2, bidsForAuction.size());
        assertEquals(firstBidAmount, bidsForAuction.get(0).getAmount());
        assertEquals(secondBidAmount, bidsForAuction.get(1).getAmount());
    }

    @Test
    public void testGetBidsForAuction_AuctionNotExist() {
        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> bidController.getBidsForAuction("AUC999"));
    }
}
