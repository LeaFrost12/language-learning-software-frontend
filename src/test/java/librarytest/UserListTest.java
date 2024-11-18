package librarytest;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

public class UserListTest {
    private UserList userList = UserList.getInstance();
	private ArrayList<User> users = userList.getUsers();
	private User user;
	private Language langue;
	private Unit unit;
	private UUID unitId;
	private Lesson lesson;
	private UUID lessonId;
	private Language language;
	
	@BeforeEach
	public void setup() {
		users.clear();
		language = LanguageList.getInstance().getLanguage("Spanish");
		unit = language.getUnitList().getUnit(0);
        unitId = unit.getId();

        lesson = unit.getLessonList().getLesson(0);
        lessonId = lesson.getId();
		user = new User("Sofia", "Bacha", "sbacha@email.com", "1234567890", "sbacha", "Password#1", language , unitId, lessonId);
		users.add(user);
		users.add(new User("Joe", "Schmo", "jschmo@email.com", "1234567890", "jschmo", "123#Pass", language , unitId, lessonId));
		DataWriter.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}
	
	
	@Test
	void testHaveUserValidFirstItem() {
		boolean hasSofia = userList.userExists("sbacha");
		assertTrue(hasSofia);
	}
	
	@Test
	void testHaveUserValidLastItem() {
		boolean hasJoe = userList.userExists("jschmo");
		assertTrue(hasJoe);
	}
	
	@Test
	void testHaveUserInValid() {
		boolean hasJoe2 = userList.userExists("jsmith");
		assertFalse(hasJoe2);
	}
	
	@Test
	void testHaveUserEmpty() {
		boolean hasEmpty = userList.userExists("");
		assertFalse(hasEmpty);
	}
	
	@Test
	void testHaveUserNull() {
		boolean hasNull = userList.userExists(null);
		assertFalse(hasNull);
	}

	@Test
	void testValidPass() {
		boolean validPass = userList.validPass("sbacha", "Password#1");
		assertTrue(validPass);
	}

	@Test
	void testInValidPass() {
		boolean invalidPass = userList.validPass("sbacha", "password#1");
		assertFalse(invalidPass);
	}

	@Test
	void testValidPassEmptyPass() {
		boolean emptyPass = userList.validPass("", "");
		assertFalse(emptyPass);
	}

	@Test
	void testValidPassNullPass() {
		boolean nullPass = userList.validPass(null, null);
		assertFalse(nullPass);
	}

	@Test
	void testEditUserValid() {
		boolean validEdit = userList.editUser("Sofia", "Bacha", "sbacha@email.com", "0987654321", "sbacha", "newPass#123", "Password#1");
		assertTrue(validEdit);
	}

	@Test
	void testEditUserNoUser() {
		boolean noEdit = userList.editUser("Fake", "User", "fuser@email.com", "0987654321", "jsmith", "newPass#123", "Password#1");
		assertFalse(noEdit);
	}

	@Test
	void testEditUserIncorrectPassword() {
		boolean edited = userList.editUser("Sofia", "Bacha", "sbacha@email.com", "1234567890", "sbacha", "newPass#123", "WrongPassword");
		assertFalse(edited);
	}

	@Test
	void testValidLogin() {
		boolean validLogin = userList.login("sbacha", "Password#1");
		assertTrue(validLogin);
	}

	@Test
	void testInValidUserLogin() {
		boolean inValidUserLogin = userList.login("jsmith", "Password#1");
		assertFalse(inValidUserLogin);
	}

	@Test
	void testInValidPassLogin() {
		boolean inValidPassLogin = userList.login("sbacha", "123#Pass");
		assertFalse(inValidPassLogin);
	}

	@Test
	void testValidLogout() {
		boolean validLogout = userList.logout(user);
		assertTrue(validLogout);
	}

	@Test
	void testNullLogout() {
		boolean nullLogout = userList.logout(null);
		assertFalse(nullLogout);
	}

	@Test
	void testAddDuplicateUser() {
    boolean addedFirst = userList.addUser("John", "Doe", "jdoe@email.com", "1234567890", "jdoe", "Password#1", language, unitId, lessonId);
    assertTrue(addedFirst);
    boolean addedSecond = userList.addUser("John", "Doe", "jdoe@email.com", "1234567890", "jdoe", "Password#1", language, unitId, lessonId);
    assertFalse(addedSecond);
	}
}