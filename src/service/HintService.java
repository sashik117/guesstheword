package service;

import entity.Hint;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HintService {

    private final List<Hint> hints = new ArrayList<>();

    // Створити підсказку
    public Hint create(String word, String text, int level) {
        Hint hint = new Hint(word, text, level); // Тепер додаємо слово
        hints.add(hint);
        return hint;
    }

    // Отримати підсказку за ID
    public Hint getById(UUID id) {
        return hints.stream()
            .filter(hint -> hint.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Отримати підсказку для певного слова
    public Hint getHintByWord(String word) {
        return hints.stream()
            .filter(hint -> hint.getWord().equals(word))
            .findFirst()
            .orElse(null);
    }

    // Оновити підсказку
    public boolean update(UUID id, String newText, int newLevel) {
        Hint hint = getById(id);
        if (hint != null) {
            hints.remove(hint);
            hints.add(new Hint(hint.getWord(), newText, newLevel)); // Оновлення підсказки
            return true;
        }
        return false;
    }

    // Видалити підсказку
    public boolean delete(UUID id) {
        return hints.removeIf(hint -> hint.getId().equals(id));
    }

    // Пошук підсказок за текстом
    public List<Hint> searchByText(String text) {
        return hints.stream()
            .filter(hint -> hint.getText().toLowerCase().contains(text.toLowerCase()))
            .toList();
    }

    // Фільтрація підсказок за рівнем
    public List<Hint> filterByLevel(int level) {
        return hints.stream()
            .filter(hint -> hint.getLevel() == level)
            .toList();
    }
}