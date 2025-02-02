package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

public class UserVerificationDto {

    @NotBlank(message = "Email не може бути порожнім")
    @Email(message = "Некоректний формат email")
    private final String email;

    @NotBlank(message = "Код верифікації не може бути порожнім")
    private final String verificationCode;

    public UserVerificationDto(String email, String verificationCode) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email не може бути порожнім");
        }
        if (verificationCode == null || verificationCode.isEmpty()) {
            throw new IllegalArgumentException("Код верифікації не може бути порожнім");
        }

        this.email = email;
        this.verificationCode = verificationCode;
    }

    public String getEmail() {
        return email;
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
        UserVerificationDto that = (UserVerificationDto) o;
        return Objects.equals(email, that.email) &&
            Objects.equals(verificationCode, that.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, verificationCode);
    }

    @Override
    public String toString() {
        return "UserVerificationDto{" +
            "email='" + email + '\'' +
            ", verificationCode='" + verificationCode + '\'' +
            '}';
    }
}
