package librarytest;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

public class DataLoaderTest {
    private UserList userList = UserList.getInstance();
	private ArrayList<User> users = userList.getUsers();
	
	@BeforeEach
	public void setup() {
        
		users.clear();
		Language spanish = LanguageList.getInstance().getLanguageByEnum(LanguagesEnum.SPANISH);
		ArrayList<Language> languages = new ArrayList<Language>();
		languages.add(spanish);
		ArrayList<Badge> badges = new ArrayList<Badge>();
		ArrayList<Word> words = new ArrayList<Word>();
		WordList wordList = new WordList(words);

		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c29"), "Amy", "Smith", "asmith@gmail.com", "803-454-3344", "asmith", "Password123!", languages, badges, UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), wordList));
		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c21"), "Bob", "Brown", "bbrown@gmail.com", "803-123-3899", "bbrown", "Password789!", languages, badges, UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), wordList));
		DataWriter.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}

    @Test
	void testGetUsersSize() {
		users = DataLoader.getUserList();
		assertEquals(2, users.size());
	}

	@Test
	void testGetUsersSizeZero() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
		assertEquals(0, users.size());
	}

	@Test
	void testGetUserFirstUserName() {
		users = DataLoader.getUserList();
		assertEquals("asmith", users.get(0).getUsername());
	}

	@Test
	void testGetUserLastUserName() {
		users = DataLoader.getUserList();
		assertEquals("bbrown", users.get(1).getUsername());
	}

	//Language List loading functionality tested in language list test
}
