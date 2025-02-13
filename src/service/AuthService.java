package service;

import dto.UserDto;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email не може бути порожнім.");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() < 6) {
            throw new IllegalArgumentException("Пароль має містити щонайменше 6 символів.");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Цей email вже використовується.");
        }

        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        User newUser = new User(userDto.getName(), userDto.getEmail(), hashedPassword);
        userService.save(newUser);

        System.out.println("✔️ Користувач зареєстрований!");
    }

    public User login(UserDto userDto) {
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email не може бути порожнім.");
        }
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Пароль не може бути порожнім.");
        }

        User user = userService.findByEmail(userDto.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Користувача не знайдено.");
        }
        if (!BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Невірний пароль.");
        }

        System.out.println("✔️ Вхід успішний!");
        return user;
    }
}