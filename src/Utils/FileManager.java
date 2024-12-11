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

    private static FileManager instance;

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