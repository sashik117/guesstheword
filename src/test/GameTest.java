package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import dto.UserDto;
import entity.Game;
import entity.User;
import entity.Word;
import java.io.File;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import service.AuthService;
import service.GameService;
import service.HintService;
import service.UserService;

public class GameTest {

    private static final String USERS_FILE = "users_data.json";
    private UserService userService;
    private AuthService authService;
    private GameService gameService;
    private HintService hintService;

    @Before
    public void setUp() {
        // Видалення файлу користувачів перед тестами
        File file = new File(USERS_FILE);
        if (file.exists()) {
            file.delete();
        }

        userService = new UserService();
        authService = new AuthService(userService);
        hintService = new HintService();
        gameService = new GameService(hintService);
    }

    @Test
    public void testRegistration() {
        UserDto registerDto = new UserDto("test_user", "test@mail.com", "password123", null);
        authService.register(registerDto);

        User user = userService.findByEmail("test@mail.com");
        assertNotNull("Користувач не знайдений після реєстрації", user);
        assertEquals("Ім'я користувача не збігається", "test_user", user.getName());
    }

    @Test
    public void testLogin() {
        UserDto registerDto = new UserDto("test_user", "test@mail.com", "password123", null);
        authService.register(registerDto);

        UserDto loginDto = new UserDto(null, "test@mail.com", "password123", null);
        User user = authService.login(loginDto);

        assertNotNull("Користувач не авторизувався", user);
        assertEquals("Ім'я користувача не збігається після логіну", "test_user", user.getName());
    }

    @Test
    public void testCreateGame() {
        User user = gameService.createUser();
        assertNotNull("Користувач не створений", user);

        Word word = gameService.createWord();
        assertNotNull("Слово не створене", word);

        Game game = gameService.createGame(user, word);
        assertNotNull("Гра не створена", game);
        assertEquals("Гравець у грі не збігається", user, game.getPlayer());
    }

    @Test
    public void testSaveGameToFile() {
        User user = gameService.createUser();
        Word word = gameService.createWord();
        Game game = gameService.createGame(user, word);

        String filePath = "test_game.json";
        gameService.saveGameToFile(game, filePath);

        File file = new File(filePath);
        assertTrue("Файл гри не створений", file.exists());
    }

    @Test
    public void testEndGame() {
        User user = gameService.createUser();
        Word word = gameService.createWord();
        Game game = gameService.createGame(user, word);

        UUID gameId = game.getId(); // Тепер UUID знайдений через правильний імпорт
        boolean gameEnded = gameService.endGame(gameId);
        assertTrue("Гра не завершилась", gameEnded);
    }
}
