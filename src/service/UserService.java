package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import reprository.UserRepository;

public class UserService {

    private static final String USERS_FILE = "users_data.json";
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

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
        users.add(user);  // Тепер додається до змінного списку
        writeUsersToJson(users);
    }

    private List<User> readUsersFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(USERS_FILE)) {
            // Вказуємо тип списку, щоб Gson міг коректно десеріалізувати список користувачів
            Type userListType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(reader, userListType);
            // Якщо список не ініціалізований, створюємо порожній список
            return users != null ? users : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Якщо файл не знайдено, повертаємо порожній список
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося прочитати дані з файлу: " + e.getMessage());
        }
    }

    private void writeUsersToJson(List<User> users) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            throw new RuntimeException(
                "Не вдалося записати дані користувачів в файл: " + e.getMessage());
        }
    }
}
