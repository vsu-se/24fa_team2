import java.util.Date;

public class Transaction {
    private Item item;
    private double price;
    private Date time;
    private User buyer;

    public Transaction(Item item, double price, Date time, User buyer) {
        this.item = item;
        this.price = price;
        this.time = time;
        this.buyer = buyer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public <T> T method(T type) {
        // Implementation of the method(type) function
        // This is a generic method as the diagram doesn't specify the exact type
        return type;
    }
}
