package tests.models;

import Models.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    @Test
    void testAdminCreation() {
        Admin admin = new Admin("adminUser", "password123");

        assertNotNull(admin, "Admin object should not be null");
        assertEquals("adminUser", admin.getUsername(), "Username should match the one provided");
        assertEquals("password123", admin.getPassword(), "Password should match the one provided");
    }

    @Test
    void testAdminInheritance() {
        Admin admin = new Admin("adminUser", "password123");

        // Verify that Admin is a subclass of User
        assertTrue(admin instanceof Models.User, "Admin should be a subclass of User");
    }

    @Test
    void testSetSystemCommissionMethod() {
        Admin admin = new Admin("adminUser", "password123");

        // Verify that calling the method does not throw exceptions
        assertDoesNotThrow(() -> admin.setSystemCommission(10.5), "setSystemCommission should not throw an exception");
    }

    @Test
    void testSetBuyerPremiumMethod() {
        Admin admin = new Admin("adminUser", "password123");

        // Verify that calling the method does not throw exceptions
        assertDoesNotThrow(() -> admin.setBuyerPremium(15.0), "setBuyerPremium should not throw an exception");
    }

    @Test
    void testCreateCategoryMethod() {
        Admin admin = new Admin("adminUser", "password123");

        // Verify that calling the method does not throw exceptions
        assertDoesNotThrow(() -> admin.createCategory("Electronics"), "createCategory should not throw an exception");
    }
}
