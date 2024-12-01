import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    // Test setting a valid seller commission
    @Test
    void shouldSetValidSellerCommission() {
        // Arrange
        AuctionSystemModel model = new AuctionSystemModel();
        Controller controller = new Controller(model);
        String validCommission = "10.5";

        // Act
        controller.setCommission(validCommission);

        // Assert
        assertEquals(10.5, model.getSellerCommission(), "Seller commission should be set correctly.");
    }

    // Test handling an invalid seller commission
    @Test
    void shouldThrowExceptionForInvalidSellerCommission() {
        // Arrange
        AuctionSystemModel model = new AuctionSystemModel();
        Controller controller = new Controller(model);
        String invalidCommission = "invalid";

        // Act & Assert
        assertThrows(NumberFormatException.class,
                () -> controller.setCommission(invalidCommission),
                "Invalid commission should throw a NumberFormatException.");
    }

    // Test setting a valid buyer premium
    @Test
    void shouldSetValidBuyerPremium() {
        // Arrange
        AuctionSystemModel model = new AuctionSystemModel();
        Controller controller = new Controller(model);
        String validPremium = "15.0";

        // Act
        controller.setPremium(validPremium);

        // Assert
        assertEquals(15.0, model.getBuyerPremium(), "Buyer premium should be set correctly.");
    }

    // Test handling an invalid buyer premium
    @Test
    void shouldThrowExceptionForInvalidBuyerPremium() {
        // Arrange
        AuctionSystemModel model = new AuctionSystemModel();
        Controller controller = new Controller(model);
        String invalidPremium = "invalid";

        // Act & Assert
        assertThrows(NumberFormatException.class,
                () -> controller.setPremium(invalidPremium),
                "Invalid premium should throw a NumberFormatException.");
    }
}
