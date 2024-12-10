package tests.models;

import Models.Auction;
import Models.Bid;
import Models.Item;
import Models.Category;
import Models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AuctionTest {
    private Auction auction;
    private Item item;
    private User seller;
    private User bidder1;
    private User bidder2;
    private Category category;

    @BeforeEach
    public void setUp() {
        // Create a seller, bidders, and category
        seller = new User("seller123", "password123");
        bidder1 = new User("bidder1", "password1");
        bidder2 = new User("bidder2", "password2");
        category = new Category("cat1", "Electronics");

        // Create an item
        item = new Item("item123", "Laptop", "A high-quality laptop", 15.0, category, seller);

        // Create an auction
        auction = new Auction("auction123", item, 100.0, 500.0, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    @Test
    public void testAddBid_SuccessfulBid() {
        Bid bid1 = new Bid("bid1", auction, bidder1, 150.0);
        assertTrue(auction.addBid(bid1), "Bid should be successfully added.");
        assertEquals(150.0, auction.getCurrentPrice(), "Current price should match the highest bid.");
    }

    @Test
    public void testAddBid_UnsuccessfulBidLowerThanCurrentPrice() {
        Bid bid1 = new Bid("bid1", auction, bidder1, 150.0);
        auction.addBid(bid1);

        Bid bid2 = new Bid("bid2", auction, bidder2, 120.0);
        assertFalse(auction.addBid(bid2), "Bid lower than the current price should not be added.");
        assertEquals(150.0, auction.getCurrentPrice(), "Current price should remain the same.");
    }

    @Test
    public void testAddBid_UnsuccessfulBidLowerThanStartingPrice() {
        Bid bid = new Bid("bid1", auction, bidder1, 50.0);
        assertFalse(auction.addBid(bid), "Bid lower than starting price should not be added.");
        assertEquals(100.0, auction.getCurrentPrice(), "Current price should remain the starting price.");
    }

    @Test
    public void testEndAuction_WithBids() {
        Bid bid1 = new Bid("bid1", auction, bidder1, 150.0);
        auction.addBid(bid1);

        Bid bid2 = new Bid("bid2", auction, bidder2, 200.0);
        auction.addBid(bid2);

        auction.endAuction();
        assertFalse(auction.getBids().isEmpty(), "Auction should have bids.");
        assertEquals(bid2, auction.getWinningBid(), "Winning bid should be the highest bid.");
    }

    @Test
    public void testEndAuction_NoBids() {
        auction.endAuction();
        assertTrue(auction.getBids().isEmpty(), "Auction with no bids should remain empty.");
        assertNull(auction.getWinningBid(), "Winning bid should be null if no bids are placed.");
    }

    @Test
    public void testGetCurrentPrice_WithoutBids() {
        assertEquals(100.0, auction.getCurrentPrice(), "Current price should be the starting price with no bids.");
    }

    @Test
    public void testSetBuyNowPrice() {
        auction.setBuyNowPrice(300.0);
        assertEquals(300.0, auction.getBuyNowPrice(), "Buy Now Price should be updated.");
    }

    @Test
    public void testSetEndTime() {
        LocalDateTime newEndTime = LocalDateTime.now().plusDays(2);
        auction.setEndTime(newEndTime);
        assertEquals(newEndTime, auction.getEndTime(), "End time should be updated.");
    }
}
