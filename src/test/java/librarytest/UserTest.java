package librarytest;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

public class UserTest {
    private UserList userList = UserList.getInstance();
	private ArrayList<User> users = userList.getUsers();

    @BeforeEach
	public void setup() {
		users.clear();
		Language language = LanguageList.getInstance().getLanguage("Spanish");
		Unit unit = language.getUnitList().getUnit(0);
        UUID unitId = unit.getId();

        Lesson lesson = unit.getLessonList().getLesson(0);
        UUID lessonId = lesson.getId();
		users.add(new User("Sofia", "Bacha", "sbacha@email.com", "1234567890", "sbacha", "Password#1", language , unitId, lessonId));
		users.add(new User("Joe", "Schmo", "jschmo@email.com", "1234567890", "jschmo", "123#Pass", language , unitId, lessonId));
		DataWriter.saveUsers();
	}

    @AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}

    @Test
    void testValidIsUser() {
        boolean validUser = User.isUser("sbacha");
        assertTrue(validUser);
    }

    @Test
    void testInValidIsUser() {
        boolean inValidUser = User.isUser("jsmith");
        assertFalse(inValidUser);
    }

    @Test
    void testEmptyIsUser() {
        boolean emptyUser = User.isUser(" ");
        assertFalse(emptyUser);
    }

    @Test
    void testNullIsUser() {
        boolean nullUser = User.isUser(null);
        assertFalse(nullUser);
    }

    @Test
    void testValidPassMin() {
        boolean minValidPass = User.validPass("Passw0rd#"); 
        assertTrue(minValidPass);
    }

    @Test
    void testInValidPassNoUppercase() {
        boolean noUppercasePass = User.validPass("password#1"); 
        assertFalse(noUppercasePass);
    }

    @Test
    void testInValidPassNoNumber() {
        boolean noNumberPass = User.validPass("Password#"); 
        assertFalse(noNumberPass);
    }

    @Test
    void testInValidPassNoSpecialChar() {
        boolean nospecialCharPass = User.validPass("Password1"); 
        assertFalse(nospecialCharPass);
    }

    @Test
    void testInValidPassShort() {
        boolean shortPass = User.validPass("Pass#1"); 
        assertFalse(shortPass);
    }

    @Test
    void testValidUserMin() {
        boolean minValidUser = User.validPass("abc"); //3 characters
        assertTrue(minValidUser);
    }

    @Test
    void testValidUserMax() {
        boolean maxValidUser = User.validPass("abcdefghijklmnopqrst"); //20 characters
        assertTrue(maxValidUser);
    }

    @Test
    void testInValidUserSort() {
        boolean shortInValidUser = User.validPass("ab"); //2 characters
        assertFalse(shortInValidUser);
    }

    @Test
    void testInValidUserLong() {
        boolean longInValidUser = User.validUser("abcdefghijklmnopqrstq"); //21 characters
        assertFalse(longInValidUser);
    }

    @Test
    void testInValidUserSpecialChar() {
        boolean specialCharInValidUser = User.validUser("usern@me"); 
        assertFalse(specialCharInValidUser);
    }

    @Test
    void testUpdateUser_CorrectPassword() {
        User user = users.get(0);
        user.updateUser("NewFirst", "NewLast", "newemail@email.com", "9876543210", "NewPassword#2", "Password#1");
        assertEquals("NewFirst", user.getFirstName());
        assertEquals("NewLast", user.getLastName());
        assertEquals("newemail@email.com", user.getEmail());
        assertEquals("9876543210", user.getPhoneNumber());
        assertEquals("NewPassword#2", user.getPassword());
    }

    @Test
    void testUpdateUser_IncorrectPassword() {
        User user = users.get(0);
        user.updateUser("NewFirst", "NewLast", "newemail@email.com", "9876543210", "NewPassword#2", "WrongPassword");
        assertNotEquals("NewFirst", user.getFirstName());
    }
}