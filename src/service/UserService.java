package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.User;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final String USERS_FILE = "users_data.json";
    private final Gson gson = new Gson();

    public boolean existsByEmail(String email) {
        return findByEmail(email) != null;
    }

    public User findByEmail(String email) {
        List<User> users = readUsersFromJson();
        return users.stream()
            .filter(user -> user.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }

    public void save(User user) {
        List<User> users = readUsersFromJson();
        users.add(user);
        writeUsersToJson(users);
        System.out.println("Користувач збережений: " + user);
    }

    private List<User> readUsersFromJson() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(reader, userListType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу користувачів: " + e.getMessage());
        }
    }

    private void writeUsersToJson(List<User> users) {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            throw new RuntimeException("Помилка запису файлу користувачів: " + e.getMessage());
        }
    }
}