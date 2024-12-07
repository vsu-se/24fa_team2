package Models;
//admin controls
public class SystemConfig {
    private double sellerCommission;
    private double buyerPremium;
    private static SystemConfig instance;

    private SystemConfig() {
        sellerCommission = 0.05; //5%
        buyerPremium = 0.10; //10%
    }
    //maintain only one systemConfig
    public static SystemConfig getInstance() {
        if (instance == null) {
            instance = new SystemConfig();
        }
        return instance;
    }

    public double getSellerCommission() {
        return sellerCommission;
    }
    public double getBuyerPremium() {
        return buyerPremium;
    }
    public void setSellerCommission(double commission) {
        this.sellerCommission = commission;
    }
    public void setBuyerPremium(double premium) {
        this.buyerPremium = premium;
    }
}
