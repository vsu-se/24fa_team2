import java.util.Date;

//comments to be added
public class Auction {
    public Item item;
    public Bid[] bids;
    public Bid currentBid;
    public Date startDate;
    public Date endDate;
    public Date sellDate;
    public User seller;
    public double buyNow;

    public Auction(Item item, double price) {
        this.item = item;
        this.buyNow = price;
    }

    public Transaction endAuction() {
        return null;
    }

    @Override
    public String toString() {
        // Formatting item details
        StringBuilder sb = new StringBuilder();
        sb.append("Auction for: ").append(item.title).append("\n")
                .append("Description: ").append(item.description).append("\n")
                .append("Category: ").append(item.category).append("\n")
                .append("Condition: ").append(item.condition).append("\n")
                .append("Shipping Cost: $").append(String.format("%.2f", item.shippingCost)).append("\n")
                .append("Buy Now Price: $").append(String.format("%.2f", buyNow)).append("\n")
                .append("Start Date: ").append(startDate != null ? startDate : "TBD").append("\n")
                .append("End Date: ").append(endDate != null ? endDate : "TBD").append("\n");

        return sb.toString();
    }
}