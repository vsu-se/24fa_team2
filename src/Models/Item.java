package Models;

public class Item {
    private String itemId;
    private String name;
    private String description;
    private double shippingCost;
    private Category category;
    private User seller;

    public Item(String itemId, String name, String description, double shippingCost, Category category, User seller) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.shippingCost = shippingCost;
        this.category = category;
        this.seller = seller;
    }

    //getters
    public String getItemId(){
        return itemId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public double getShippingCost(){
        return shippingCost;
    }

    public Category getCategory(){
        return category;
    }

    public User getSeller(){
        return seller;
    }

    
}
