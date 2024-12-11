package Utils;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import Models.*;
import Controllers.*;

public class FileManager {
    private static final String DATA_DIR = "auctionData";
    private static final String USERS_FILE = "users.json";
    private static final String CATS_FILE = "cats.json";
    private static final String AUCTIONS_FILE = "auctions.json";

    private static FileManager instance;
    private CategoryController catController;
    private UserController userController;

    private FileManager() {
        initialize();
        
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public void initialize() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save Methods
    public void saveUsers(Map<String, User> users) {
        JSONArray jsonUsers = new JSONArray();
        
        for (User user : users.values()) {
            JSONObject jsonUser = new JSONObject();
            jsonUser.put("username", user.getUsername());
            jsonUser.put("password", user.getPassword());
            jsonUser.put("canSell", user.isCanBid());
            jsonUser.put("canBid", user.isCanSell());
            jsonUser.put("isAdmin", user instanceof Admin);
            
            jsonUsers.add(jsonUser);
        }
        
        writeJSONToFile(USERS_FILE, jsonUsers);
    }

    public void saveCategories(Map<String, Category> cats){
        JSONArray jsonCats = new JSONArray();

        for(Category cat : cats.values()){
            JSONObject jsonCat = new JSONObject();
            jsonCat.put("name", cat.getName());

            jsonCats.add(jsonCat);
        }

        writeJSONToFile(CATS_FILE, jsonCats);
    }

    public void saveAuctions(Map<String, Auction> auctions){
        JSONArray jsonAuctions = new JSONArray();
        catController = CategoryController.getInstance();
        userController = UserController.getInstance();

        for(Auction auction : auctions:values()){
            JSONObject jsonAuction = new JSONObject();

            jsonAuction.put("auctionID", auction.getAuctionId());
            jsonAuction.put("item", tokenizeItem(auction.getItem()));
            jsonAuction.put("starting", String.valueOf(auction.getStartingPrice()));
            jsonAuction.put("buyNow", String.valueOf(auction.getBuyNowPrice()));
            jsonAuction.put("startTime", auction.getStartTime().toString());
            jsonAuction.put("endTime", auction.getEndTime().toString());
            
            //ADD BIDS

        }

        writeJSONToFile(AUCTIONS_FILE, jsonAuctions);
    }

    public JSONObject tokenizeItem(Item item){
        JSONObject jsonItem = new JSONObject();
        jsonItem.put("itemID", item.getItemId());
        jsonItem.put("name", item.getName());
        jsonItem.put("desc", item.getDescription());
        jsonItem.put("shipping", String.valueOf(item.getShippingCost()));
        jsonItem.put("category", item.getCategory().getName());
        jsonItem.put("seller", item.getSeller().getUsername());

        return jsonItem;
    }

    //---Break Load Methods following
    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        JSONArray jsonUsers = readJSONArrayFromFile(USERS_FILE);
        
        if (jsonUsers != null) {
            for (Object obj : jsonUsers) {
                JSONObject jsonUser = (JSONObject) obj;
                String username = (String) jsonUser.get("username");
                String password = (String) jsonUser.get("password");
                boolean isAdmin = (boolean) jsonUser.get("isAdmin");
                
                User user = isAdmin ? new Admin(username, password) : new User(username, password);
                user.setCanSell((boolean) jsonUser.get("canSell"));
                user.setCanBid((boolean) jsonUser.get("canBid"));
                
                users.put(username, user);
            }
        }
        
        return users;
    }

    public Map<String, Category> loadCats(){
        Map<String, Category> cats = new HashMap<>();
        JSONArray jsonCats = readJSONArrayFromFile(CATS_FILE);

        if(jsonCats != null){
            for(Object obj : jsonCats){
                JSONObject jsonCat = (JSONObject) obj;
                String name = (String) jsonCat.get("name");

                Category cat = new Category(name, name);

                cats.put(name, cat);
            }
        }

        return cats;
    }

    public Map<String, Auction> loadAuctions(){
        Map<String, Auction> auctions = new HashMap<>();
        JSONArray jsonAuctions = readJSONArrayFromFile(AUCTIONS_FILE);

        if(jsonAuctions != null){
            for(Object obj : jsonAuctions){
                JSONObject jsonAuction = (JSONObject) obj;

                String auctionID = (String) jsonAuction.get("auctionID");
                Item item = itemizeToken((JSONObject)jsonAuction.get("item"));
                double starting = Double.parseDouble((String) jsonAuction.get("starting"));
                double buyNow = Double.parseDouble((String) jsonAuction.get("buyNow"));
                LocalDateTime startTime = LocalDateTime.parse((String) jsonAuction.get("startTime"));
                LocalDateTime endTime = LocalDateTime.parse((String) jsonAuction.get("endTime"));

                auctions.put(auctionID, new Auction(auctionID, item, starting, buyNow, startTime, endTime));

            }
        }


        return auctions;
    }

    public Item itemizeToken(JSONObject jsonItem){
        String itemID = (String) jsonItem.get("itemID");
        String name = (String) jsonItem.get("name");
        String desc = (String) jsonItem.get("desc");
        double shipping = Double.parseDouble((String) jsonItem.get("shipping"));
        Category category = catController.getCategory((String) jsonItem.get("category"));
        User seller = userController.getUser((String) jsonItem.get("seller"));

        return new Item(itemID, name, desc, shipping, category, seller);
    }

    // helper methods
    private void writeJSONToFile(String filename, Object jsonObject) {
        try (FileWriter file = new FileWriter(Paths.get(DATA_DIR, filename).toString())) {
            file.write(jsonObject.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JSONArray readJSONArrayFromFile(String filename) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(DATA_DIR, filename)));
            JSONParser parser = new JSONParser();
            return (JSONArray) parser.parse(content);
        } catch (IOException | ParseException e) {
            return null;
        }
    }
}