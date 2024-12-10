package tests.models;

import Models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    private Category category;

    @BeforeEach
    public void setUp() {
        // Initialize a Category object before each test
        category = new Category("1", "Electronics");
    }

    @Test
    public void testGetCategoryId() {
        assertEquals("1", category.getCategoryId(), "Category ID should match the initialized value.");
    }

    @Test
    public void testGetName() {
        assertEquals("Electronics", category.getName(), "Category name should match the initialized value.");
    }

    @Test
    public void testSetName() {
        category.setName("Home Appliances");
        assertEquals("Home Appliances", category.getName(), "Category name should update correctly.");
    }
}
