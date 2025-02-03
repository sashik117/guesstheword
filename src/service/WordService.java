package service;

import entity.Word;
import java.util.List;
import java.util.UUID;
import reprository.WordRepository;

public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    // Створити нове слово
    public Word createWord(String text, String category, int complexity) {
        UUID id = UUID.randomUUID();  // Генерація нового ID для слова
        Word word = new Word(id, text, category, complexity);  // Додаємо ID як четвертий параметр
        wordRepository.add(word);
        return word;
    }

    // Отримати всі слова
    public List<Word> getAllWords() {
        return wordRepository.getAll();
    }

    // Оновити слово
    public Word updateWord(UUID id, String text, String category, int complexity) {
        Word existingWord = wordRepository.getById(id);
        if (existingWord != null) {
            existingWord.setText(text);
            existingWord.setCategory(category);
            existingWord.setComplexity(complexity);
            wordRepository.update(existingWord);
            return existingWord;
        }
        return null;
    }

    // Видалити слово по ID
    public boolean deleteWord(UUID id) {
        Word word = wordRepository.getById(id);
        if (word != null) {
            wordRepository.delete(id);  // Викликаємо delete по ID
            return true;
        }
        return false;
    }

    // Видалити всі слова
    public void deleteAllWords() {
        wordRepository.clear();
    }

    // Генерація випадкових слів (наприклад)
    public void generateRandomWords(int count) {
        for (int i = 0; i < count; i++) {
            createWord("RandomWord" + (i + 1), "General", 3);
        }
    }

    // Завантажити слова з файлу
    public void loadWordsFromFile(String filePath) {
        // Реалізація для завантаження слів з файлу
    }

    // Зберегти слова у файл
    public void saveWordsToFile(String filePath) {
        // Реалізація для збереження слів у файл
    }

    // Отримати слово по ID
    public Word getWordById(UUID id) {
        return wordRepository.getById(id);
    }
}
