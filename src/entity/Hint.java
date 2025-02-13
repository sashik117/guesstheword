package entity;

import java.util.UUID;

public class Hint {

    private final UUID id;
    private final String word;  // Додаємо слово
    private final String text;  // Текст підсказки
    private final int level;    // Рівень підсказки

    public Hint(String word, String text, int level) {
        this.id = UUID.randomUUID();
        this.word = word;
        this.text = text;
        this.level = level;
    }

    public UUID getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getText() {
        return text;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Hint{" +
            "id=" + id +
            ", word='" + word + '\'' +
            ", text='" + text + '\'' +
            ", level=" + level +
            '}';
    }
}