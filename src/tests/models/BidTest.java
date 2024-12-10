package tests.models;

import Models.Auction;
import Models.Bid;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BidTest {
    private Bid bid;
    private User bidder;
    private Auction auction;

    @BeforeEach
    public void setUp() {
        // Initialize necessary objects before each test
        bidder = new User("testUser", "password123");
        auction = new Auction("1", null, 100.0, 200.0, null, null); // Use null for item, as it is not relevant here
        bid = new Bid("1", auction, bidder, 150.0); // Create a Bid object with sample data
    }

    @Test
    public void testGetBidId() {
        assertEquals("1", bid.getBidId(), "Bid ID should match the initialized value.");
    }

    @Test
    public void testGetAuction() {
        assertEquals(auction, bid.getAuction(), "Auction object should match the initialized value.");
    }

    @Test
    public void testGetBidder() {
        assertEquals(bidder, bid.getBidder(), "Bidder should match the initialized value.");
    }

    @Test
    public void testGetAmount() {
        assertEquals(150.0, bid.getAmount(), "Bid amount should match the initialized value.");
    }
}
