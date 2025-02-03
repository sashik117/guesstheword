package forms;

public class LoginForm {

    private String username;
    private String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Отримати ім'я користувача
    public String getUsername() {
        return username;
    }

    // Встановити ім'я користувача
    public void setUsername(String username) {
        this.username = username;
    }

    // Отримати пароль
    public String getPassword() {
        return password;
    }

    // Встановити пароль
    public void setPassword(String password) {
        this.password = password;
    }
}
