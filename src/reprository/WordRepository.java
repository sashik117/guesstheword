package reprository;

import entity.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WordRepository {

    private final List<Word> words = new ArrayList<>();
    private String filePath;  // Додано поле для шляху до файлу
    private boolean isPersisted;  // Додано поле для визначення чи зберігати

    // Конструктор без параметрів
    public WordRepository() {
        // Використовується стандартний конструктор без параметрів
    }

    // Конструктор з параметрами для ініціалізації
    public WordRepository(String filePath, boolean isPersisted) {
        this.filePath = filePath;
        this.isPersisted = isPersisted;
    }

    // Додавання слова
    public void add(Word word) {
        words.add(word);
    }

    // Отримати всі слова
    public List<Word> getAll() {
        return new ArrayList<>(words);
    }

    // Оновлення слова
    public Word update(Word word) {
        int index = -1;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getId().equals(word.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            words.set(index, word);
            return word;
        }
        return null;
    }

    // Видалення слова по ID
    public void delete(UUID id) {
        words.removeIf(word -> word.getId().equals(id));
    }

    // Очищення списку
    public void clear() {
        words.clear();
    }

    // Знайти слово за ID
    public Word getById(UUID id) {
        return words.stream().filter(word -> word.getId().equals(id)).findFirst().orElse(null);
    }

    // Отримання шляху до файлу
    public String getFilePath() {
        return filePath;
    }

    // Отримання статусу збереження
    public boolean isPersisted() {
        return isPersisted;
    }
}
