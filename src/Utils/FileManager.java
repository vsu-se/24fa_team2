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