//comments to be added
public class Item {
    public String title;
    public String description;
    public String category;
    public String[] tags;
    public String condition;
    public double shippingCost;

    public Item(String title, String description, String category, String condition, Double shippingCost){
        this.title = title;
        this.description = description;
        this.category = category;
        this.condition = condition;
        this.shippingCost = shippingCost;
    }
}
