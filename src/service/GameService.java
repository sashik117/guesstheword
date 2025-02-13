package service;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import entity.Game;
import entity.Hint;
import entity.PlayerAttempt;
import entity.User;
import entity.Word;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GameService {

    private final Faker faker;
    private final Gson gson;
    private final Map<UUID, Game> activeGames; // Для збереження активних ігор
    private final HintService hintService; // Додаємо сервіс для роботи з підсказками

    // Конструктор
    public GameService(HintService hintService) {
        this.faker = new Faker();
        this.gson = new Gson();
        this.activeGames = new HashMap<>(); // Ініціалізація активних ігор
        this.hintService = hintService; // Ініціалізація сервісу підсказок
    }

    // Створення користувача
    public User createUser() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        return new User(firstName, lastName, email);
    }

    // Створення слова
    public Word createWord() {
        UUID id = UUID.randomUUID();
        String text = faker.lorem().word();
        String category = "Загальна";
        int complexity = faker.number().numberBetween(1, 6);
        return new Word(id, text, category, complexity);
    }

    // Створення підсказки
    public Hint createHint(String word, String description, int level) {
        return hintService.create(word, description, level);
    }

    // Створення гри
    public Game createGame(User user, Word word) {
        Game game = new Game(user, word);

        // Створюємо підсказку для цього слова
        String hintDescription = "Це слово має певну важливість у житті людини."; // Приклад підсказки
        int hintLevel = 1; // Рівень складності підсказки
        Hint hint = createHint(word.getText(), hintDescription,
            hintLevel); // Створення підсказки для гри

        game.addHint(hint); // Додаємо підсказку до гри
        activeGames.put(game.getId(), game); // Додаємо гру в активні
        return game;
    }

    // Створення спроби гравця
    public PlayerAttempt createPlayerAttempt(String guess, boolean isCorrect) {
        return new PlayerAttempt(guess, isCorrect);
    }

    // Збереження гри у файл
    public void saveGameToFile(Game game, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(game, writer);
            System.out.println("Дані збережено у файл " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отримання всіх активних ігор
    public List<Game> getAllGames() {
        return new ArrayList<>(activeGames.values());
    }

    // Завершення гри
    public boolean endGame(UUID gameId) {
        if (activeGames.containsKey(gameId)) {
            activeGames.remove(gameId);
            System.out.println("Гра завершена: " + gameId);
            return true;
        } else {
            System.out.println("Гру не знайдено або вона вже завершена.");
            return false;
        }
    }

    // Показати підсказку для гри
    public void showHintForGame(UUID gameId) {
        Game game = activeGames.get(gameId);
        if (game != null) {
            List<Hint> hints = game.getHints(); // Отримуємо список підсказок з гри
            if (!hints.isEmpty()) {
                System.out.println(
                    "Підсказка: " + hints.get(0).getText()); // Показуємо першу підсказку
            } else {
                System.out.println("Для цього слова немає підсказки.");
            }
        } else {
            System.out.println("Гру не знайдено.");
        }
    }
}