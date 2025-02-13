package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDto {

    private final String name; // Ім'я користувача (необов’язкове для логіна)

    @NotBlank(message = "Email не може бути порожнім")
    @Email(message = "Некоректний формат email")
    private final String email;

    private final String password; // Пароль (необов’язковий для верифікації)

    private final String verificationCode; // Код верифікації (необов’язковий для реєстрації та логіна)

    public UserDto(String name, String email, String password, String verificationCode) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email не може бути порожнім");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Некоректний формат email");
        }
        if (password != null && password.length() < 6) {
            throw new IllegalArgumentException("Пароль має бути не менше 6 символів");
        }
        if (name != null && name.isEmpty()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        if (verificationCode != null && verificationCode.isEmpty()) {
            throw new IllegalArgumentException("Код верифікації не може бути порожнім");
        }

        this.name = name;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) &&
            Objects.equals(email, userDto.email) &&
            Objects.equals(password, userDto.password) &&
            Objects.equals(verificationCode, userDto.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, verificationCode);
    }

    @Override
    public String toString() {
        return "UserDto{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", verificationCode='" + verificationCode + '\'' +
            '}';
    }
}