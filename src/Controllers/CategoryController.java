package Controllers;

import Models.Category;
import java.util.*;

public class CategoryController {
    // Private attributes
    private Map<String, Category> categories;
    private static CategoryController instance;

    // Private constructor
    private CategoryController() {
        categories = new HashMap<>();
    }

    // Public method to get the Singleton instance (thread-safe)
    public static synchronized CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }

    // Method to create a new category
    public Boolean createCategory(String name) {
        // Check if a category with the same name already exists
        for (Category category : categories.values()) {
            if (category.getName().equalsIgnoreCase(name)) {
                return false;
            }
        }

        // Generate unique ID and create a new category
        String categoryId = generateCategoryId();
        Category newCategory = new Category(name, name);
        categories.put(name, newCategory);
        return true;
    }

    // Method to retrieve all categories
    public List<String> getAllCategories() {
        ArrayList<String> cats = new ArrayList<>();
        for(Map.Entry<String, Category> entry : categories.entrySet()){
            cats.add(entry.getKey());
        }
        return cats;
    }

    // Method to retrieve a specific category by ID
    public Category getCategory(String id) {
        Category category = categories.get(id);
        if (category == null) {
            throw new NoSuchElementException("Category with ID '" + id + "' not found.");
        }
        return category;
    }

    // Private method to generate a unique category ID
    private String generateCategoryId() {
        return UUID.randomUUID().toString();
    }

    // Optional: Method to delete a category by ID
    public boolean deleteCategory(String id) {
        if (categories.containsKey(id)) {
            categories.remove(id);
            return true;
        }
        return false;
    }
}
