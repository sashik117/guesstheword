package dto;

import java.util.Objects;

public class UserRegisterDto {

    private final String name; // Якщо це і є ім'я користувача
    private final String email;
    private final String password;

    public UserRegisterDto(String name, String email, String password) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() { // Змінив з getUsername на getName
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRegisterDto that = (UserRegisterDto) o;
        return Objects.equals(name, that.name) &&
            Objects.equals(email, that.email) &&
            Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return "UserRegisterDto{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
