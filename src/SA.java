import java.util.ArrayList;
import java.util.List;

public class SA {
    public Object field;
    private List<String> categories;

    public SA() {
        this.categories = new ArrayList<>();
    }


    public void addCategory(String category) {
        String validatedCategory = validateAddCategory(category);
        if (validatedCategory != null) {
            categories.add(validatedCategory);
            System.out.println("Category '" + validatedCategory + "' added successfully.");
        }
    }

    //helper
    private String validateAddCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Invalid category name.");
            return null;
        }
        String trimmedCategory = category.trim().toLowerCase();
        if (categories.contains(trimmedCategory)) { //cannot add if already exists
            System.out.println("Category '" + trimmedCategory + "' already exists.");
            return null;
        }
        return trimmedCategory;
    }

    public void removeCategory(String category) {
        String validatedCategory = validateRemoveCategory(category);
        if (validatedCategory != null) {
            categories.remove(validatedCategory);
            System.out.println("Category '" + validatedCategory + "' removed successfully.");
        }
    }

    //helper
    private String validateRemoveCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            System.out.println("Invalid category name.");
            return null;
        }
        String trimmedCategory = category.trim().toLowerCase();
        if (categories.contains(trimmedCategory)) { //if is an existing category that can be removed
            return trimmedCategory;
        }
        System.out.println("Category '" + trimmedCategory + "' not found.");
        return null;
    }

    public List<String> getCategories() {
        return new ArrayList<>(categories);
    }
}
