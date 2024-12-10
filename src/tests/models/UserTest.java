package tests.models;

import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User object before each test
        user = new User("testUser", "securePassword123");
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", user.getUsername(), "Username should match the initialized value.");
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername(), "Username should update correctly.");
    }

    @Test
    public void testGetPassword() {
        assertEquals("securePassword123", user.getPassword(), "Password should match the initialized value.");
    }

    @Test
    public void testSetPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword(), "Password should update correctly.");
    }

    @Test
    public void testIsCanSellDefault() {
        assertFalse(user.isCanSell(), "Default value for canSell should be false.");
    }

    @Test
    public void testSetCanSell() {
        user.setCanSell(true);
        assertTrue(user.isCanSell(), "canSell should update to true.");
    }

    @Test
    public void testIsCanBidDefault() {
        assertFalse(user.isCanBid(), "Default value for canBid should be false.");
    }

    @Test
    public void testSetCanBid() {
        user.setCanBid(true);
        assertTrue(user.isCanBid(), "canBid should update to true.");
    }

    @Test
    public void testVerifyPasswordCorrect() {
        assertTrue(user.verifyPassword("securePassword123"), "Password verification should return true for correct password.");
    }

    @Test
    public void testVerifyPasswordIncorrect() {
        assertFalse(user.verifyPassword("wrongPassword"), "Password verification should return false for incorrect password.");
    }
}
