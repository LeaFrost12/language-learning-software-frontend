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

public class DataWriterTest {
    private UserList userList = UserList.getInstance();
	private ArrayList<User> users = userList.getUsers();
    Language spanish = LanguageList.getInstance().getLanguageByEnum(LanguagesEnum.SPANISH);
	
	@BeforeEach
	public void setup() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}
	
	@AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		users = DataLoader.getUserList();
		assertEquals(0, users.size());
	}

	@Test
	void testWritingOneUser() {
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(spanish);
		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c29"), "Amy", "Smith", "asmith@gmail.com", "803-454-3344", "asmith", "Password123!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
		DataWriter.saveUsers();
		assertEquals("asmith", DataLoader.getUserList().get(0).getUsername());
	}
	
	@Test
	void testWritingFiveUsers() {
		ArrayList<Badge> badges = new ArrayList<Badge>();
		ArrayList<Word> words = new ArrayList<Word>();
		WordList wordList = new WordList(words);
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(spanish);

        users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c29"), "Amy", "Smith", "asmith@gmail.com", "803-454-3344", "asmith", "Password123!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
        users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c28"), "Jane", "Doe", "jdoe@gmail.com", "803-454-8989", "asmith", "Password1234!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c27"), "Bob", "Smith", "asmith@gmail.com", "803-454-3344", "asmith", "Password123!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c26"), "Paige", "Jones", "pjones@gmail.com", "123-454-3344", "pjones", "Password12345!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
		users.add(new User(UUID.fromString("7036cdd2-dedf-46ae-8f90-d56257fa4c25"), "Tracy", "Travis", "ttravis@gmail.com", "302-454-3344", "ttravis", "Password123456!", languages, new ArrayList<Badge>(), UUID.fromString("9b6e7eab-f214-4a2a-8437-1bef6b77b785"), UUID.fromString("d9803a1b-97db-4518-afd0-20562be10c0e"), new WordList(new ArrayList<Word>())));
		DataWriter.saveUsers();
		assertEquals("ttravis", DataLoader.getUserList().get(4).getUsername());
	}
	
	@Test
	void testWritingEmptyUser() {
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(spanish);
		users.add(new User("", "", "", "", "", "", spanish, UUID.fromString("00000000-0000-0000-0000-000000000000"), UUID.fromString("00000000-0000-0000-0000-000000000000")));
		DataWriter.saveUsers();
		assertEquals("", DataLoader.getUserList().get(0).getUsername());
	}
	
	@Test
	void testWritingNullUser() {
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(spanish);
		users.add(new User("", "", "", "", null, "", spanish, UUID.fromString("00000000-0000-0000-0000-000000000000"), UUID.fromString("00000000-0000-0000-0000-000000000000")));
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUserList().get(0).getUsername());
	}
}
