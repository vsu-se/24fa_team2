package tests.controllers;
import Controllers.CategoryController;
import Models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryControllerTest {

    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        // Reset the singleton instance for isolation
        resetSingletonInstance();
        categoryController = CategoryController.getInstance();
    }

    private void resetSingletonInstance() {
        try {
            java.lang.reflect.Field instanceField = CategoryController.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            instanceField.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to reset CategoryController singleton instance.", e);
        }
    }

    @Test
    public void testCreateCategory_Success() {
        boolean isCreated = categoryController.createCategory("Electronics");
        assertTrue(isCreated, "Category should be created successfully.");
        List<String> allCategories = categoryController.getAllCategories();
        assertTrue(allCategories.contains("Electronics"), "Created category should be in the list.");
    }

    @Test
    public void testCreateCategory_Duplicate() {
        categoryController.createCategory("Books");
        boolean isCreated = categoryController.createCategory("Books");
        assertFalse(isCreated, "Duplicate category creation should return false.");
    }

    @Test
    public void testGetAllCategories() {
        categoryController.createCategory("Toys");
        categoryController.createCategory("Clothing");
        List<String> allCategories = categoryController.getAllCategories();
        assertEquals(2, allCategories.size(), "There should be 2 categories.");
        assertTrue(allCategories.contains("Toys"), "Categories list should contain 'Toys'.");
        assertTrue(allCategories.contains("Clothing"), "Categories list should contain 'Clothing'.");
    }

    @Test
    public void testGetCategory_Success() {
        categoryController.createCategory("Furniture");
        Category category = categoryController.getCategory("Furniture");
        assertNotNull(category, "Category should be retrieved successfully.");
        assertEquals("Furniture", category.getName(), "Category name should match.");
    }

    @Test
    public void testGetCategory_NotFound() {
        assertThrows(NoSuchElementException.class,
                () -> categoryController.getCategory("Nonexistent"),
                "Requesting a nonexistent category should throw an exception.");
    }

    @Test
    public void testDeleteCategory_Success() {
        categoryController.createCategory("Sports");
        boolean isDeleted = categoryController.deleteCategory("Sports");
        assertTrue(isDeleted, "Category should be deleted successfully.");
        List<String> allCategories = categoryController.getAllCategories();
        assertFalse(allCategories.contains("Sports"), "Deleted category should not be in the list.");
    }

    @Test
    public void testDeleteCategory_NotFound() {
        boolean isDeleted = categoryController.deleteCategory("Nonexistent");
        assertFalse(isDeleted, "Deleting a nonexistent category should return false.");
    }

    @Test
    public void testSaveAndLoadCategories() {
        categoryController.createCategory("Outdoors");
        categoryController.saveCats(); // Save the categories

        resetSingletonInstance(); // Reset singleton to simulate fresh start

        categoryController = CategoryController.getInstance(); // Reinitialize singleton
        List<String> allCategories = categoryController.getAllCategories();
        assertTrue(allCategories.contains("Outdoors"), "Loaded categories should contain 'Outdoors'.");
    }
}