package Controllers;
import AuctionSystemModel;
import GUI;
import Item;

public class Controller {
    private GUI gui;

    public AuctionSystemModel getaManager() {
        return aManager;
    }

    private AuctionSystemModel aManager;

    public Controller(GUI gui){
        this.gui = gui;
        this.aManager = new AuctionSystemModel();
    }

    // used for testing
    public Controller(AuctionSystemModel aManager) {
        this.gui = null; // GUI is unused in this scenario
        this.aManager = aManager;
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

    public void setCommission(String commission) {
        try {
            double comm = Double.parseDouble(commission);
            aManager.setSellerCommission(comm);

            // GUI update only if the GUI exists
            if (gui != null && gui.lbl != null) {
                gui.lbl.setText("Seller commission set to " + comm);
            }
        } catch (NumberFormatException e) {
            if (gui != null && gui.lbl != null) {
                gui.lbl.setText("Invalid commission value");
            }
            throw e; // Re-throw for testing purposes
        }
    }

    public void setPremium(String premium) {
        try {
            double prem = Double.parseDouble(premium);
            aManager.setBuyerPremium(prem);

            // GUI update only if the GUI exists
            if (gui != null && gui.lbl != null) {
                gui.lbl.setText("Buyer premium set to " + prem);
            }
        } catch (NumberFormatException e) {
            if (gui != null && gui.lbl != null) {
                gui.lbl.setText("Invalid premium value");
            }
            throw e; // Re-throw for testing purposes
        }
    }

    //Thread to constantly update GUI Auction info?
}
