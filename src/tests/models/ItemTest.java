package tests.models;

import Models.Category;
import Models.Item;
import Models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;
    private User seller;
    private Category category;

    @BeforeEach
    public void setUp() {
        // Create a seller and category
        seller = new User("seller123", "password123");
        category = new Category("cat1", "Electronics");

        // Create an item
        item = new Item("item123", "Laptop", "A high-quality laptop", 15.0, category, seller);
    }

    @Test
    public void testGetItemId() {
        assertEquals("item123", item.getItemId(), "Item ID should match the initialized value.");
    }

    @Test
    public void testGetName() {
        assertEquals("Laptop", item.getName(), "Item name should match the initialized value.");
    }

    @Test
    public void testGetDescription() {
        assertEquals("A high-quality laptop", item.getDescription(), "Description should match the initialized value.");
    }

    @Test
    public void testGetShippingCost() {
        assertEquals(15.0, item.getShippingCost(), 0.01, "Shipping cost should match the initialized value.");
    }

    @Test
    public void testGetCategory() {
        assertEquals(category, item.getCategory(), "Category should match the initialized value.");
    }

    @Test
    public void testGetSeller() {
        assertEquals(seller, item.getSeller(), "Seller should match the initialized value.");
    }
}
