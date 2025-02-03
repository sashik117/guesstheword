package entity;

import java.util.UUID;

public class Word {

    private UUID id;
    private String text;   // Текст слова, має бути на українській мові
    private String category;
    private int complexity;

    public Word(UUID id, String text, String category, int complexity) {
        this.id = id;
        this.text = text;
        this.category = category;
        this.complexity = complexity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    // Повертає першу букву для підсказки
    public String getFirstLetterHint() {
        return "Перша буква: " + text.charAt(0);  // Повертаємо першу букву слова
    }

    // Повертає другу букву для підсказки
    public String getSecondLetterHint() {
        return "Друга буква: " + text.charAt(1);  // Повертаємо другу букву слова
    }

    // Повертає третю букву для підсказки
    public String getThirdLetterHint() {
        return "Третя буква: " + text.charAt(2);  // Повертаємо третю букву слова
    }

    // Повертає опис для підсказки
    public String getDescriptionHint() {
        // Тут треба додати більш детальний опис предмету
        return "Це об'єкт, який має важливе значення в нашому житті.";
    }

    @Override
    public String toString() {
        return "Word{" +
            "id=" + id +
            ", text='" + text + '\'' +
            ", category='" + category + '\'' +
            ", complexity=" + complexity +
            '}';
    }
}
