
public class Controller {
    private GUI gui;
    private AuctionSystemModel aManager;

    public Controller(GUI gui){
        this.gui = gui;
        this.aManager = new AuctionSystemModel();
    }

    public void createAuctionData(String title, String description, String category, String tags, String condition, String shippingCost, String buyNow, String duration){
        aManager.addAuction(createItem(title, description, category, condition, shippingCost), Double.parseDouble(buyNow));
        gui.lbl.setText(aManager.toString());
    }

    public Item createItem(String title, String description, String category, String condition, String shippingCost){
        return new Item(title, description, category, condition, Double.parseDouble(shippingCost));
    }

    public void getAuctions(){

    }

    public void setCommission(String commission){
        aManager.setSellerCommission(Double.parseDouble(commission));

        gui.lbl.setText("Commission set to " + aManager.getSellerCommission() + "%");
    }

    public void setPremium(String premium){
        aManager.setBuyerPremium(Double.parseDouble(premium));

        gui.lbl.setText("Premium set to " + aManager.getBuyerPremium() + "%");
    }

    //Thread to constantly update GUI Auction info?
}
