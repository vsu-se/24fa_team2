import java.util.ArrayList;
import java.util.List;

public class Item {
    private String title;
    private String description;
    private String category;
    private List<String> tags;
    private String condition;
    private double shippingCost;

    public Item(String title, String description, String category, String condition, double shippingCost) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tags = new ArrayList<>();
        this.condition = condition;
        this.shippingCost = shippingCost;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return "Item: " + title + " (" + category + ")";
    }
}
