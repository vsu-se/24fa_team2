import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction {
    private final Item item;
    private List<Bid> bids;
    private Bid currentBid;
    private Date startDate;
    private Date endDate;
    private Date sellDate;
    private User seller;
    private double buyNow;

    public Auction(Item item, double buyNow) {
        this.item = item;
        this.buyNow = buyNow;
        this.bids = new ArrayList<>();
        this.startDate = new Date(); // Set to current date when auction is created
    }

    public Transaction endAuction() {
        if (currentBid != null) {
            this.sellDate = new Date();
            return new Transaction(item, currentBid.getAmount(), sellDate, currentBid.getUser());
        }
        return null; // No bids were placed
    }

    public Item getItem() {
        return item;
    }

    // Getters and setters for all fields
    // ...

    // Additional methods that might be useful:

    public void placeBid(Bid bid) {
        if (bid.getAmount() > (currentBid != null ? currentBid.getAmount() : 0)) {
            currentBid = bid;
            bids.add(bid);
        }
    }

    public boolean isActive() {
        Date now = new Date();
        return now.after(startDate) && now.before(endDate);
    }

    public boolean attemptBuyNow(User buyer) {
        if (isActive()) {
            this.sellDate = new Date();
            return true;
        }
        return false;
    }

    public void setAuctionDuration(int durationInDays) {
        this.startDate = new Date();
        this.endDate = new Date(startDate.getTime() + (durationInDays * 24 * 60 * 60 * 1000L));
    }

    public Bid getHighestBid() {
        return currentBid;
    }

    public int getBidCount() {
        return bids.size();
    }

    public boolean hasBids() {
        return !bids.isEmpty();
    }

    @Override
    public String toString(){
        String msg;


        return msg;
    }
}