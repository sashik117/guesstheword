package pages;

import entity.User;
import java.util.Random;
import java.util.Scanner;
import service.GameService;

public class GameView {

    public void displayGame(Scanner scanner, GameService gameService, User user) {
        // Масив слів для гри
        String[] words = {
            "яблуко", "банан", "перо", "собака", "квітка", "річка", "дерево", "стіл", "машина",
            "телефон", "комп'ютер", "книга", "школа", "місто", "роза", "світло", "гора", "птах",
            "телевізор", "друзі", "сніг", "дощ", "гроші", "молоко", "сіль", "кава", "хліб",
            "колесо", "сонце", "місяць", "зима", "літо", "весна", "осінь", "сміх", "пісня",
            "зуби", "ліс", "море", "пташка", "корова", "ріка", "камінь", "папір", "олівець",
            "сірник", "сова", "сірка", "свиня", "двері", "вікно", "помідор", "сир", "село",
            "вода", "підлога"
        };

        // Вибір випадкового слова з масиву
        Random random = new Random();
        String wordToGuess = words[random.nextInt(words.length)].toLowerCase();

        // Перевірка на наявність некоректних символів (тільки кирилиця)
        if (!wordToGuess.matches("[а-яА-ЯіІїЇєЄґҐ]+")) {
            System.out.println("Помилка! Слово містить некоректні символи.");
            return;  // Перериваємо гру, якщо є помилка
        }

        // Створення прихованого слова
        StringBuilder hiddenWord = new StringBuilder(wordToGuess.replaceAll(".", "_"));
        String guessedLetters = "";  // Використані літери
        int attemptsLeft = 15;  // Лічильник спроб
        boolean gameWon = false;

        // Гра починається
        System.out.println("********************************************");
        System.out.println("   Почнемо гру: Вгадай слово!");
        System.out.println("********************************************");

        while (!hiddenWord.toString().equals(wordToGuess) && attemptsLeft > 0) {
            // Виведення поточного стану
            System.out.println("\nСлово: " + hiddenWord);  // Поточний стан слова
            System.out.println("Використані літери: " + guessedLetters);
            System.out.println("Залишилось спроб: " + attemptsLeft);
            System.out.println("===========================================");
            System.out.print("Введіть букву або 'вихід' щоб вийти: ");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("вихід")) {
                System.out.println("Дякуємо за гру! До побачення!");
                break;
            }

            // Перевірка, чи введена одна кирилична буква
            if (input.length() == 1 && input.matches("[а-яА-ЯіІїЇєЄґҐ]")) {
                if (guessedLetters.contains(input)) {
                    System.out.println(
                        "\u001B[33m" + "❗ Ви вже використовували цю літеру!" + "\u001B[0m");
                    continue;
                }

                guessedLetters += input;

                if (wordToGuess.contains(input)) {
                    System.out.println(
                        "\u001B[32m" + "✅ Вірно! Буква " + input + " є в слові." + "\u001B[0m");
                    // Оновлення прихованого слова
                    for (int i = 0; i < wordToGuess.length(); i++) {
                        if (wordToGuess.charAt(i) == input.charAt(0)) {
                            hiddenWord.setCharAt(i, input.charAt(0));
                        }
                    }
                } else {
                    attemptsLeft--; // Віднімання спроби при неправильному введенні
                    System.out.println(
                        "\u001B[31m" + "❌ Невірно! Буква " + input + " відсутня в слові."
                            + "\u001B[0m");
                }
            } else {
                System.out.println(
                    "\u001B[35m" + "⚠️ Будь ласка, введіть лише одну кириличну букву."
                        + "\u001B[0m");
            }
        }

        // Перевірка результату
        if (hiddenWord.toString().equals(wordToGuess)) {
            System.out.println(
                "\u001B[34m" + "🎉 Вітаємо! Ви вгадали слово: " + wordToGuess + " 🎉" + "\u001B[0m");
            gameWon = true;
        } else {
            System.out.println(
                "\u001B[31m" + "😞 Вибачте, ви програли! Слово було: " + wordToGuess + "\u001B[0m");
        }

        if (gameWon) {
            user.incrementWordsGuessed(); // Збільшуємо кількість вгаданих слів
        }

        user.incrementGamesPlayed(); // Збільшуємо кількість зіграних ігор
    }
}
