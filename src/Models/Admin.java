package Models;

public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    //Admin functionality
    public void setSystemCommission(double commission) {
        //handled by a controller
    }

    public void setBuyerPremium(double premium) {
        //contoller handled
    }

    public void createCategory(String categoryName) {
        //contoller handled
    }
}