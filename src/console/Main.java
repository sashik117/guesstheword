package console;

import com.google.gson.Gson;
import dto.UserLoginDto;
import dto.UserRegisterDto;
import entity.User;
import java.util.Scanner;
import pages.AuthView;
import pages.GameView;
import pages.UserView;
import service.AuthService;
import service.GameService;
import service.UserService;

public class Main {

    private static final String BLUE = "\033[1;34m";
    private static final String YELLOW = "\033[1;33m";
    private static final String GREEN = "\033[1;32m";
    private static final String RED = "\033[1;31m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        // Ініціалізація сервісів
        UserService userService = new UserService();
        AuthService authService = new AuthService(userService);
        GameService gameService = new GameService();

        // Створення об'єктів для переглядів
        AuthView authView = new AuthView();
        GameView gameView = new GameView();
        UserView userView = new UserView();

        // Відображення головного меню
        while (true) {
            System.out.println(BLUE + "====================================");
            System.out.println("      🎮 Welcome to Guess The Word!      ");
            System.out.println("====================================" + RESET);

            System.out.println(YELLOW + "1 - Реєстрація");
            System.out.println("2 - Вхід" + RESET);
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Пропускаємо символ нового рядка

            User user = null;

            if (choice == 1) {
                // Логіка реєстрації
                System.out.print(YELLOW + "[?] Введіть ім'я: " + RESET);
                String username = scanner.nextLine();

                System.out.print(YELLOW + "[?] Введіть email: " + RESET);
                String email = scanner.nextLine();

                // Валідація email
                if (!isValidEmail(email)) {
                    System.err.println(RED + "❌ Невірний формат email." + RESET);
                    return;
                }

                System.out.print(YELLOW + "[?] Введіть пароль: " + RESET);
                String password = scanner.nextLine();

                try {
                    UserRegisterDto registerDto = new UserRegisterDto(username, email, password);
                    authService.register(registerDto);
                    System.out.println(GREEN + "✔ Реєстрація успішна!" + RESET);
                } catch (IllegalArgumentException e) {
                    System.err.println(RED + "Помилка реєстрації: " + e.getMessage() + RESET);
                    return;
                }
            } else if (choice == 2) {
                // Логіка входу
                System.out.print(YELLOW + "[?] Введіть email: " + RESET);
                String email = scanner.nextLine();

                System.out.print(YELLOW + "[?] Введіть пароль: " + RESET);
                String password = scanner.nextLine();

                try {
                    UserLoginDto loginDto = new UserLoginDto(email, password);
                    user = authService.login(loginDto);
                    System.out.println(
                        GREEN + "✔ Вхід успішний! Ласкаво просимо, " + user.getName() + "! 🎉"
                            + RESET);

                    // Після входу викликаємо дисплей меню
                    while (true) {
                        System.out.println(BLUE + "=============================");
                        System.out.println("      Головне меню користувача      ");
                        System.out.println("=============================" + RESET);

                        System.out.println(YELLOW + "1 - Почати гру");
                        System.out.println("2 - Переглянути статистику");
                        System.out.println("3 - Вийти" + RESET);
                        System.out.print("Ваш вибір: ");

                        String actionChoice = scanner.nextLine();

                        switch (actionChoice) {
                            case "1":
                                gameView.displayGame(scanner, gameService, user); // Запуск гри
                                break;
                            case "2":
                                userView.displayUserMenu(user); // Перегляд статистики
                                break;
                            case "3":
                                System.out.println(GREEN + "До побачення!" + RESET);
                                return;
                            default:
                                System.out.println(RED + "❌ Невірний вибір." + RESET);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(RED + "Помилка входу: " + e.getMessage() + RESET);
                    return;
                }
            } else {
                System.out.println(RED + "❌ Невірний вибір." + RESET);
                return;
            }
        }
    }

    // Метод для перевірки правильності email через regex
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
