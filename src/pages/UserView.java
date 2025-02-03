package pages;

import entity.User;
import java.util.Scanner;

public class UserView {

    private final Scanner scanner = new Scanner(System.in);

    public void displayUserMenu(User user) {  // Тепер передаємо користувача як параметр
        System.out.println("\033[34m====================\033[0m"); // Синій заголовок
        System.out.println("\033[36m   Кабінет користувача\033[0m"); // Бірюзовий текст
        System.out.println("\033[34m====================\033[0m");

        while (true) {
            System.out.println("\n\033[33mОберіть опцію:\033[0m");
            System.out.println("1. Переглянути статистику");
            System.out.println("2. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showStatistics(user);  // Тепер передаємо користувача в метод статистики
                    break;
                case "2":
                    System.out.println("\033[32mПовертаємось до головного меню.\033[0m");
                    return;
                default:
                    System.out.println("\033[31mНевірна опція. Спробуйте ще раз.\033[0m");
            }
        }
    }

    private void showStatistics(User user) {  // Приймаємо користувача як параметр
        System.out.println("\033[35m--- Ваша статистика ---\033[0m");
        System.out.println("Ігор зіграно: " + user.getGamesPlayed());  // Виводимо реальні дані
        System.out.println("Слів відгадано: " + user.getWordsGuessed());  // Виводимо реальні дані
    }
}
