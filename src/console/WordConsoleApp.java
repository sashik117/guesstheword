package console;

import entity.Word;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import reprository.WordRepository;
import service.WordService;

public class WordConsoleApp {

    private static final WordService wordService = new WordService(new WordRepository());
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Керування словами ===");
            System.out.println("1. Створити слово");
            System.out.println("2. Переглянути всі слова");
            System.out.println("3. Оновити слово");
            System.out.println("4. Видалити слово");
            System.out.println("5. Згенерувати випадкові слова");
            System.out.println("6. Зберегти слова у файл");
            System.out.println("7. Завантажити слова з файлу");
            System.out.println("8. Видалити всі слова");
            System.out.println("9. Вихід");
            System.out.print("Оберіть опцію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createWord();
                    break;
                case "2":
                    readWords();
                    break;
                case "3":
                    updateWord();
                    break;
                case "4":
                    deleteWord();
                    break;
                case "5":
                    generateRandomWords();
                    break;
                case "6":
                    saveWordsToFile();
                    break;
                case "7":
                    loadWordsFromFile();
                    break;
                case "8":
                    deleteAllWords();
                    break;
                case "9":
                    running = false;
                    System.out.println("Вихід з керування словами.");
                    break;
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }
    }

    private static void generateRandomWords() {
        System.out.print("Введіть кількість випадкових слів для генерації: ");
        int count = Integer.parseInt(scanner.nextLine());
        wordService.generateRandomWords(count);
        System.out.println(count + " випадкових слів згенеровано.");
    }

    private static void saveWordsToFile() {
        System.out.print("Введіть шлях до файлу для збереження слів: ");
        String filePath = scanner.nextLine();
        wordService.saveWordsToFile(filePath);
        System.out.println("Слова збережено в " + filePath);
    }

    private static void loadWordsFromFile() {
        System.out.print("Введіть шлях до файлу для завантаження слів: ");
        String filePath = scanner.nextLine();
        wordService.loadWordsFromFile(filePath);
        System.out.println("Слова завантажено з " + filePath);
    }

    private static void createWord() {
        System.out.print("Введіть текст слова: ");
        String text = scanner.nextLine();
        System.out.print("Введіть категорію: ");
        String category = scanner.nextLine();
        System.out.print("Введіть складність (1-5): ");
        int complexity = Integer.parseInt(scanner.nextLine());

        Word word = wordService.createWord(text, category, complexity);
        System.out.println("Слово створено: " + word);
    }

    private static void readWords() {
        System.out.println("\n=== Усі слова ===");
        List<Word> words = wordService.getAllWords();
        for (Word word : words) {
            System.out.println(word);
        }
    }

    private static void updateWord() {
        System.out.print("Введіть ID слова для оновлення: ");
        String idInput = scanner.nextLine();

        UUID id;
        try {
            id = UUID.fromString(idInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Невірний формат ID.");
            return;
        }

        Word existingWord = wordService.getWordById(id);  // Оновлений виклик

        if (existingWord == null) {
            System.out.println("Слово не знайдено.");
            return;
        }

        System.out.println("Поточне слово: " + existingWord);
        System.out.print("Введіть новий текст (або залиште порожнім, щоб зберегти поточне): ");
        String newText = scanner.nextLine();
        System.out.print("Введіть нову категорію (або залиште порожнім, щоб зберегти поточну): ");
        String newCategory = scanner.nextLine();
        System.out.print("Введіть нову складність (або залиште порожнім, щоб зберегти поточну): ");
        String complexityInput = scanner.nextLine();

        int newComplexity = complexityInput.isEmpty() ? existingWord.getComplexity()
            : Integer.parseInt(complexityInput);
        Word updatedWord = wordService.updateWord(
            id,
            newText.isEmpty() ? existingWord.getText() : newText,
            newCategory.isEmpty() ? existingWord.getCategory() : newCategory,
            newComplexity
        );

        System.out.println("Слово оновлено: " + updatedWord);
    }

    private static void deleteWord() {
        System.out.print("Введіть ID слова для видалення: ");
        String idInput = scanner.nextLine();

        UUID id;
        try {
            id = UUID.fromString(idInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Невірний формат ID.");
            return;
        }

        boolean isDeleted = wordService.deleteWord(id);
        if (isDeleted) {
            System.out.println("Слово видалено.");
        } else {
            System.out.println("Слово не знайдено.");
        }
    }

    private static void deleteAllWords() {
        wordService.deleteAllWords();
        System.out.println("Всі слова видалено.");
    }
}
