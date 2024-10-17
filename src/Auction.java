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
    public String toString(){
        String msg;


        return msg;
    }
}