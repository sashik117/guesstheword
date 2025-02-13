package form;

public class UserForm {

    private String username;
    private String password;
    private String guessedWord; // Використовується лише для введення слова

    public UserForm(String username, String password, String guessedWord) {
        this.username = username;
        this.password = password;
        this.guessedWord = guessedWord;
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

    // Отримати слово, введене користувачем
    public String getGuessedWord() {
        return guessedWord;
    }

    // Встановити слово, введене користувачем
    public void setGuessedWord(String guessedWord) {
        this.guessedWord = guessedWord;
    }

    @Override
    public String toString() {
        return "UserForm{" +
            "username='" + username + '\'' +
            ", guessedWord='" + guessedWord + '\'' +
            '}';
    }
}