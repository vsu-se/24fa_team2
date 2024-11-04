import java.util.ArrayList;

public class AuctionSystemModel{
    private double sellerCommission;
    private double buyerPremium;
    private ArrayList<Auction> activeAuctions = new ArrayList<Auction>();
    private ArrayList<Auction> completedAuctions = new ArrayList<Auction>();

    public AuctionSystemModel(){

    }

    public void addAuction(Item item, double buyNow){
        activeAuctions.add(new Auction(item, buyNow));
    }

    public void setSellerCommission(double sellerCommission){
        this.sellerCommission = sellerCommission;
    }

    public double getSellerCommission(){
        return sellerCommission;
    }

    public void setBuyerPremium(double buyerPremium){
        this.buyerPremium = buyerPremium;
    }

    @Override
    public String toString(){
        String msg;
        msg = "Seller Commission: " + sellerCommission + "\n" +
                "Buyer Premium: " + buyerPremium + "\n" +
                "Number of Active Auctions: " + activeAuctions.size() + "\n" +
                "Auctions: ";

        return msg;
    }


    //Thread to monitor validitiy?
}