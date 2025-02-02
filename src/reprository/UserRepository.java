package reprository;

import entity.User;
import java.util.Optional;

public class UserRepository {

    // Тут мають бути методи для збереження, пошуку та перевірки користувачів в колекціях
    public void save(User user) {
        // Сюди має йти код для збереження користувача в базу/файл
    }

    public Optional<User> findByEmail(String email) {
        // Пошук користувача за email
        return Optional.empty(); // Тестовий метод, замініть на реальний пошук
    }
}
