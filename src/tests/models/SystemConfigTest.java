package tests.models;

import Models.SystemConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SystemConfigTest {

    private SystemConfig config;

    @BeforeEach
    public void setUp() {
        // Reset the singleton instance to ensure a fresh state for each test
        SystemConfig.getInstance(); // Force initialization of the instance

        // Since it's a singleton, we cannot easily reset it unless we modify the class
        // In this case, we'll use reflection to reset it if needed
        try {
            // Access the private static instance field via reflection
            java.lang.reflect.Field field = SystemConfig.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);  // Set instance to null to reset it
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();  // Handle any exceptions related to reflection
        }

        // Now, create a new instance for the test
        config = SystemConfig.getInstance();
    }

    @Test
    public void testSingletonInstance() {
        SystemConfig anotherConfig = SystemConfig.getInstance();
        assertSame(config, anotherConfig, "The SystemConfig instance should be a singleton.");
    }

    @Test
    public void testGetSellerCommission() {
        assertEquals(0.05, config.getSellerCommission(), "The initial seller commission should be 0.05 (5%).");
    }

    @Test
    public void testGetBuyerPremium() {
        assertEquals(0.10, config.getBuyerPremium(), "The initial buyer premium should be 0.10 (10%).");
    }

    @Test
    public void testSetSellerCommission() {
        config.setSellerCommission(0.08); // Set a new commission value
        assertEquals(0.08, config.getSellerCommission(), "The seller commission should be updated to 0.08.");
    }

    @Test
    public void testSetBuyerPremium() {
        config.setBuyerPremium(0.12); // Set a new premium value
        assertEquals(0.12, config.getBuyerPremium(), "The buyer premium should be updated to 0.12.");
    }
}
