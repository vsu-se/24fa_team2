package tests.controllers;

import Controllers.AuctionController;
import Controllers.UserController;
import Models.Auction;
import Models.Item;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuctionControllerTest {
    private AuctionController auctionController;
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Reset controllers
        auctionController = AuctionController.getInstance();
        userController = UserController.getInstance();

        // Clear auctions and log out user
        auctionController.getActiveAuctions().clear();
        userController.logout();
    }

    @Test
    void testCreateAuction() {
        User seller = new User("sellerUser", "password123");
        seller.setCanSell(true);
        userController.setCurrentUser(seller);

        Item item = new Item("ITEM001", "Test Item", "Test Description", 5.0, null, seller);
        double startingPrice = 50.0;
        double buyNowPrice = 150.0;
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);

        Auction auction = auctionController.createAuction(item, startingPrice, buyNowPrice, endTime);

        assertNotNull(auction);
        assertEquals(item, auction.getItem());
        assertEquals(startingPrice, auction.getStartingPrice());
        assertEquals(buyNowPrice, auction.getBuyNowPrice());
    }

    @Test
    void testCreateAuctionWithoutSellPermission() {
        User user = new User("regularUser", "password123");
        user.setCanSell(false); // No permission to sell
        userController.setCurrentUser(user);

        Item item = new Item("ITEM002", "Test Item 2", "Another Description", 5.0, null, user);
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);

        Auction auction = auctionController.createAuction(item, 50.0, 100.0, endTime);

        assertNull(auction);
    }

    @Test
    void testGetUserAuctions() {
        User seller = new User("sellerUser", "password123");
        seller.setCanSell(true);
        userController.setCurrentUser(seller);

        Item item1 = new Item("ITEM001", "Test Item 1", "Description 1", 5.0, null, seller);
        Item item2 = new Item("ITEM002", "Test Item 2", "Description 2", 5.0, null, seller);

        auctionController.createAuction(item1, 50.0, 100.0, LocalDateTime.now().plusDays(1));
        auctionController.createAuction(item2, 30.0, 90.0, LocalDateTime.now().plusDays(2));

        List<Auction> userAuctions = auctionController.getUserAuctions(seller);

        assertEquals(2, userAuctions.size());
        assertEquals(item1, userAuctions.get(0).getItem());
        assertEquals(item2, userAuctions.get(1).getItem());
    }

    @Test
    void testGetActiveAuctions() {
        User seller = new User("sellerUser", "password123");
        seller.setCanSell(true);
        userController.setCurrentUser(seller);

        Item item1 = new Item("ITEM001", "Test Item 1", "Description 1", 5.0, null, seller);
        Item item2 = new Item("ITEM002", "Test Item 2", "Description 2", 5.0, null, seller);

        auctionController.createAuction(item1, 50.0, 100.0, LocalDateTime.now().plusDays(1));
        auctionController.createAuction(item2, 30.0, 90.0, LocalDateTime.now().plusDays(2));

        List<Auction> activeAuctions = auctionController.getActiveAuctions();

        assertEquals(2, activeAuctions.size());
        assertEquals(item1, activeAuctions.get(0).getItem());
        assertEquals(item2, activeAuctions.get(1).getItem());
    }
}
