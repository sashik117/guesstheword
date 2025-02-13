package console;

import com.google.gson.Gson;
import dto.UserDto;
import entity.User;
import java.util.Scanner;
import pages.AuthView;
import pages.GameView;
import pages.UserView;
import service.AuthService;
import service.GameService;
import service.HintService;
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

        UserService userService = new UserService();
        AuthService authService = new AuthService(userService);
        HintService hintService = new HintService();
        GameService gameService = new GameService(hintService);

        AuthView authView = new AuthView();
        GameView gameView = new GameView(gameService); // Тепер передаємо GameService
        UserView userView = new UserView();

        while (true) {
            System.out.println(BLUE + "====================================");
            System.out.println("      🎮 Welcome to Guess The Word!      ");
            System.out.println("====================================" + RESET);

            System.out.println(YELLOW + "1 - Реєстрація");
            System.out.println("2 - Вхід");
            System.out.println("3 - Вихід" + RESET);
            System.out.print("Ваш вибір: ");

            String input = scanner.nextLine();
            if (!input.matches("[1-3]")) {
                System.out.println(RED + "❌ Невірний вибір." + RESET);
                continue;
            }

            int choice = Integer.parseInt(input);
            if (choice == 3) {
                System.out.println(GREEN + "До побачення!" + RESET);
                break;
            }

            if (choice == 1) {
                System.out.print(YELLOW + "[?] Введіть ім'я (мін. 3 символи): " + RESET);
                String username = scanner.nextLine().trim();
                if (username.length() < 3) {
                    System.out.println(RED + "❌ Ім'я має містити щонайменше 3 символи!" + RESET);
                    continue;
                }

                System.out.print(YELLOW + "[?] Введіть email: " + RESET);
                String email = scanner.nextLine().trim();
                if (!isValidEmail(email)) {
                    System.out.println(RED + "❌ Невірний формат email!" + RESET);
                    continue;
                }

                System.out.print(YELLOW + "[?] Введіть пароль (мін. 6 символів): " + RESET);
                String password = scanner.nextLine().trim();
                if (password.length() < 6) {
                    System.out.println(RED + "❌ Пароль має містити щонайменше 6 символів!" + RESET);
                    continue;
                }
                try {
                    UserDto registerDto = new UserDto(username, email, password, null);
                    authService.register(registerDto);
                } catch (IllegalArgumentException e) {
                    System.err.println(RED + "Помилка реєстрації: " + e.getMessage() + RESET);
                }
            } else if (choice == 2) {
                System.out.print(YELLOW + "[?] Введіть email: " + RESET);
                String email = scanner.nextLine();
                System.out.print(YELLOW + "[?] Введіть пароль: " + RESET);
                String password = scanner.nextLine();
                try {
                    UserDto loginDto = new UserDto(null, email, password, null);
                    User user = authService.login(loginDto);
                    System.out.println(
                        GREEN + "✔️ Вхід успішний! Ласкаво просимо, " + user.getName() + "! 🎉"
                            + RESET);
                    while (true) {
                        System.out.println(BLUE + "=============================");
                        System.out.println("         Головне меню         ");
                        System.out.println("=============================" + RESET);

                        System.out.println(YELLOW + "1 - Почати гру");
                        System.out.println("2 - Переглянути статистику");
                        System.out.println("3 - Вийти" + RESET);
                        System.out.print("Ваш вибір: ");

                        String actionChoice = scanner.nextLine();
                        switch (actionChoice) {
                            case "1":
                                // Передаємо всі необхідні параметри у displayGame
                                gameView.displayGame(scanner, gameService, user);
                                break;
                            case "2":
                                userView.displayUserMenu(user);
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
                }
            }
        }
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}