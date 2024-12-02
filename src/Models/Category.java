package Models;

public class Category {
    private String categoryId;
    private String name;

    public Category(Stirng categoryId, Stirng name ) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public setName(String name) {
        this.name = name;
    }

}
