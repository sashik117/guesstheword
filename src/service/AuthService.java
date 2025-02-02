package service;

import dto.UserLoginDto;
import dto.UserRegisterDto;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(UserRegisterDto registerDto) {
        // Перевірка на наявність email в базі
        if (userService.existsByEmail(registerDto.getEmail())) {
            throw new IllegalArgumentException("Цей email вже використовується.");
        }

        // Створення нового користувача
        String hashedPassword = BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt());
        User newUser = new User(registerDto.getName(), registerDto.getEmail(), hashedPassword);
        userService.save(newUser);
    }

    public User login(UserLoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail());
        if (!BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Невірний пароль.");
        }
        return user;
    }
}
