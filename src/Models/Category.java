package Models;

public class Category {
    String categoryId;
    String name;

    public Category(String categoryId, String name ) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}