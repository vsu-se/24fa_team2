package Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Auction {
    private String auctionId;
    private Item item;
    private double startingPrice;
    private double buyNowPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isActive;
    private List<Bid> bids = new ArrayList<Bid>();
    private Bid winningBid;

    public Auction(String auctionId, Item item, double startingPrice, double buyNowPrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.auctionId = auctionId;
        this.item = item;
        this.startingPrice = startingPrice;
        this.buyNowPrice = buyNowPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isActive = true;
        this.bids = new ArrayList<>();
    }

    public boolean addBid(Bid bid) {
        if (bid.getAmount() > startingPrice && bid.getAmount() > winningBid.getAmount()) {
            bids.add(bid);
            winningBid = bid;
            return true;
        }
        return false;
    }

    public double getCurrentPrice() {
        if (bids.isEmpty()) {
            return startingPrice;
        }
        return bids.get(bids.size() - 1).getAmount();
    }

    public void endAuction() {
        isActive = false;
        if(!bids.isEmpty()){
            winningBid = bids.get(bids.size() - 1);
        }
    }

    public String getAuctionId() {
        return auctionId;
    }

    public Item getItem() {
        return item;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getBuyNowPrice() {
        return buyNowPrice;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setBuyNowPrice(double buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public List<Bid> getBids() {
        return bids;
    }
}
