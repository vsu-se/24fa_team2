package Models;
public class User {
    private String username;
    private String password;
    private boolean canSell;
    private boolean canBid;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.canSell = true;  
        this.canBid = true;
    }

    //getters and setters
    public String getUsername(){
        return username;
    }
    public boolean getCanSell(){
        return canSell;
    }
    public boolean getCanBid(){ 
        return canBid;
    }

    public void setCanSell(boolean canSell){
        this.canSell = canSell;
    }
    public void setCanBid(boolean canBid){
        this.canBid = canBid;
    }

    // Password verification
    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
