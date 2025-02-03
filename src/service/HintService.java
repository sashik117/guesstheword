package service;

import entity.Hint;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HintService {

    private final List<Hint> hints = new ArrayList<>();

    // Створити
    public Hint create(String text, int level) {
        Hint hint = new Hint(text, level);
        hints.add(hint);
        return hint;
    }

    // Читати (отримати за ID)
    public Hint getById(UUID id) {
        return hints.stream()
            .filter(hint -> hint.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    // Оновити
    public boolean update(UUID id, String newText, int newLevel) {
        Hint hint = getById(id);
        if (hint != null) {
            hints.remove(hint);
            hints.add(new Hint(newText, newLevel));
            return true;
        }
        return false;
    }

    // Видалити
    public boolean delete(UUID id) {
        return hints.removeIf(hint -> hint.getId().equals(id));
    }

    // Пошук за текстом
    public List<Hint> searchByText(String text) {
        return hints.stream()
            .filter(hint -> hint.getText().toLowerCase().contains(text.toLowerCase()))
            .toList();
    }

    // Фільтрація за рівнем
    public List<Hint> filterByLevel(int level) {
        return hints.stream()
            .filter(hint -> hint.getLevel() == level)
            .toList();
    }
}
