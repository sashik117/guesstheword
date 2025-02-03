package pages;

import java.util.Scanner;

public class AuthView {

    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        System.out.println("\033[34m====================\033[0m"); // Синій заголовок
        System.out.println(
            "\033[36m   Ласкаво просимо до гри Вгадай слово!\033[0m"); // Бірюзовий текст
        System.out.println("\033[34m====================\033[0m"); // Синій заголовок

        while (true) {
            System.out.println("\n\033[33mОберіть опцію:\033[0m"); // Жовтий текст
            System.out.println("1. Увійти");
            System.out.println("2. Зареєструватися");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    signUp();
                    break;
                case "3":
                    System.out.println(
                        "\033[32mДякуємо, що грали! До побачення!\033[0m"); // Зелений текст
                    return;
                default:
                    System.out.println(
                        "\033[31mНевірна опція. Спробуйте ще раз.\033[0m"); // Червоний текст
            }
        }
    }

    private void login() {
        System.out.println("\n\033[35m--- Вхід ---\033[0m"); // Фіолетовий текст
        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        // Місце для реальної логіки автентифікації
        if (username.equals("user") && password.equals("pass")) {
            System.out.println("\033[32mВхід успішний!\033[0m"); // Зелений текст
        } else {
            System.out.println(
                "\033[31mВхід не вдався. Перевірте свої дані.\033[0m"); // Червоний текст
        }
    }

    private void signUp() {
        System.out.println("\n\033[35m--- Реєстрація ---\033[0m"); // Фіолетовий текст
        System.out.print("Введіть нове ім'я користувача: ");
        String username = scanner.nextLine();
        System.out.print("Введіть новий пароль: ");
        String password = scanner.nextLine();

        // Місце для реальної логіки реєстрації
        System.out.println("\033[32mКористувач успішно зареєстрований!\033[0m"); // Зелений текст
    }
}