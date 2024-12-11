package tests.controllers;

import Controllers.SystemController;
import Models.SystemConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemControllerTest {
    private SystemController systemController;
    private SystemConfig systemConfig;

    @BeforeEach
    void setUp() {
        // Ensure the singleton instance is reset
        systemConfig = SystemConfig.getInstance();
        systemConfig.setSellerCommission(0.0);
        systemConfig.setBuyerPremium(0.0);

        systemController = SystemController.getInstance();
    }

    @Test
    void testUpdateSellerCommission() {
        double newCommission = 10.5;

        boolean result = systemController.updateSellerCommission(newCommission);

        assertTrue(result);
        assertEquals(newCommission, systemConfig.getSellerCommission());
    }

    @Test
    void testUpdateBuyerPremium() {
        double newPremium = 15.75;

        boolean result = systemController.updateBuyerPremium(newPremium);

        assertTrue(result);
        assertEquals(newPremium, systemConfig.getBuyerPremium());
    }

    @Test
    void testGetSellerCommission() {
        double commission = 12.0;
        systemConfig.setSellerCommission(commission);

        double result = systemController.getSellerCommission();

        assertEquals(commission, result);
    }

    @Test
    void testGetBuyerPremium() {
        double premium = 8.5;
        systemConfig.setBuyerPremium(premium);

        double result = systemController.getBuyerPremium();

        assertEquals(premium, result);
    }
}
