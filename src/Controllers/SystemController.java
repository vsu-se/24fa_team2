package Controllers;

import Models.SystemConfig;

public class SystemController {
    private SystemConfig config;
    private static SystemController instance;

    private SystemController() {
        config = SystemConfig.getInstance();
    }

    public static SystemController getInstance() {
        if (instance == null) {
            instance = new SystemController();
        }
        return instance;
    }

    public boolean updateSellerCommission(double commission) {
        config.setSellerCommission(commission);
        return true;
    }

    public boolean updateBuyerPremium(double premium) {
        config.setBuyerPremium(premium);
        return true;
    }

    public double getSellerCommission() {
        return config.getSellerCommission();
    }

    public double getBuyerPremium() {
        return config.getBuyerPremium();
    }
}