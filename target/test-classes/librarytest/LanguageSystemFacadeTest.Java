package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class LanguageSystemFacadeTest {

    private LanguageSystemFacade facade;
    private UserList userList;
    private LanguageList languageList;
    private User testUser;
    private Language testLanguage;

    @BeforeEach
    public void setup() {
        userList = UserList.getInstance();
        languageList = LanguageList.getInstance();

        testUser = new User("Test", "User", "test@example.com", "1234567890", "testUser", "Password123!", LanguagesEnum.SPANISH, UUID.randomUUID(), UUID.randomUUID());
        userList.addUser(testUser);

        testLanguage = new Language("Spanish");
        languageList.addLanguage(testLanguage);

        facade = new LanguageSystemFacade();
    }

    @Test
    public void login_ShouldReturnUser_WhenCredentialsAreCorrect() {
        User result = facade.login("testUser", "Password123!");
        assertNotNull(result, "login should return a User object when credentials are correct.");
    }

    @Test
    public void login_ShouldReturnNull_WhenCredentialsAreIncorrect() {
        User result = facade.login("testUser", "WrongPassword");
        assertNull(result, "login should return null when credentials are incorrect.");
    }

    @Test
    public void logout_ShouldReturnTrue_WhenUserIsLoggedIn() {
        facade.login("testUser", "Password123!");
        boolean result = facade.logout();
        assertTrue(result, "logout should return true when a user is logged in.");
    }

    @Test
    public void logout_ShouldReturnFalse_WhenNoUserIsLoggedIn() {
        boolean result = facade.logout();
        assertFalse(result, "logout should return false when no user is logged in.");
    }

    @Test
    public void loadLanguage_ShouldReturnTrue_WhenLanguageExists() {
        facade.login("testUser", "Password123!");
        boolean result = facade.loadLanguage("Spanish");
        assertTrue(result, "loadLanguage should return true when the language exists.");
    }

    @Test
    public void loadLanguage_ShouldReturnFalse_WhenLanguageDoesNotExist() {
        facade.login("testUser", "Password123!");
        boolean result = facade.loadLanguage("French");
        assertFalse(result, "loadLanguage should return false when the language does not exist.");
    }

    @Test
    public void runUnit_ShouldReturnTrue_WhenUnitExists() {
        facade.login("testUser", "Password123!");
        facade.loadLanguage("Spanish");
        boolean result = facade.runUnit(1);
        assertTrue(result, "runUnit should return true when the unit exists.");
    }

    @Test
    public void runUnit_ShouldReturnFalse_WhenUnitDoesNotExist() {
        facade.login("testUser", "Password123!");
        facade.loadLanguage("Spanish");
        boolean result = facade.runUnit(999);
        assertFalse(result, "runUnit should return false when the unit does not exist.");
    }

    @Test
    public void runNextLesson_ShouldReturnTrue_WhenNextLessonExists() {
        facade.login("testUser", "Password123!");
        facade.loadLanguage("Spanish");
        boolean result = facade.runNextLesson();
        assertTrue(result, "runNextLesson should return true when the next lesson exists.");
    }

    @Test
    public void validateUsername_ShouldReturnTrue_WhenUsernameIsValid() {
        boolean result = facade.validateUsername("validUsername");
        assertTrue(result, "validateUsername should return true for a valid username.");
    }

    @Test
    public void validateUsername_ShouldReturnFalse_WhenUsernameIsInvalid() {
        boolean result = facade.validateUsername("x");
        assertFalse(result, "validateUsername should return false for an invalid username.");
    }

    @Test
    public void validatePassword_ShouldReturnTrue_WhenPasswordIsValid() {
        boolean result = facade.validatePassword("StrongPass1!");
        assertTrue(result, "validatePassword should return true for a valid password.");
    }

    @Test
    public void validatePassword_ShouldReturnFalse_WhenPasswordIsInvalid() {
        boolean result = facade.validatePassword("weak");
        assertFalse(result, "validatePassword should return false for an invalid password.");
    }

    @Test
    public void createUser_ShouldReturnTrue_WhenUserDetailsAreValid() {
        boolean result = facade.createUser("New", "User", "new@example.com", "0987654321", "newUser", "Password123!", testLanguage);
        assertTrue(result, "createUser should return true when user details are valid.");
    }

    @Test
    public void createUser_ShouldReturnFalse_WhenUsernameIsTaken() {
        boolean result = facade.createUser("Test", "User", "test@example.com", "1234567890", "testUser", "Password123!", testLanguage);
        assertFalse(result, "createUser should return false when username is already taken.");
    }

    @Test
    public void getProblemWordList_ShouldReturnWordList_WhenUserIsLoggedIn() {
        facade.login("testUser", "Password123!");
        WordList result = facade.getProblemWordList();
        assertNotNull(result, "getProblemWordList should return a WordList when a user is logged in.");
    }

    @Test
    public void getProblemWordList_ShouldReturnNull_WhenNoUserIsLoggedIn() {
        WordList result = facade.getProblemWordList();
        assertNull(result, "getProblemWordList should return null when no user is logged in.");
    }
}
