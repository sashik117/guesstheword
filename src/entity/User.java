package entity;

import java.util.UUID;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final String password; // Зберігаємо хеш пароля
    private int score;
    private int attempts;
    private boolean verified; // Додано поле для верифікації
    private int gamesPlayed; // Додаємо поле для ігор
    private int wordsGuessed; // Додаємо поле для відгаданих слів

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.score = 0;
        this.attempts = 0;
        this.verified = false; // за замовчуванням користувач не верифікований
        this.gamesPlayed = 0;  // ініціалізація
        this.wordsGuessed = 0; // ініціалізація
    }

    public User(UUID id, String name, String email, String password, boolean verified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.score = 0;
        this.attempts = 0;
        this.verified = verified;
        this.gamesPlayed = 0;  // ініціалізація
        this.wordsGuessed = 0; // ініціалізація
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    } // Використовуємо для перевірки пароля

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    // Нові гетери для статистики
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public int getWordsGuessed() {
        return wordsGuessed;
    }

    public void incrementWordsGuessed() {
        this.wordsGuessed++;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", score=" + score +
            ", attempts=" + attempts +
            ", verified=" + verified +
            ", gamesPlayed=" + gamesPlayed +
            ", wordsGuessed=" + wordsGuessed +
            '}';
    }

    // Метод для перевірки, чи верифікований користувач
    public boolean isVerified() {
        return verified;
    }

    // Метод для встановлення статусу верифікації
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
