package librarytest;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

public class MultipleChoiceTest {
    
    private User user;
    private MultipleChoice question;

	@Before
	public void setupUser() {
		Language language = LanguageList.getInstance().getLanguageByEnum(LanguagesEnum.SPANISH);
		Unit unit = language.getUnitList().getUnit(0);
        UUID unitId = unit.getId();

        Lesson lesson = unit.getLessonList().getLesson(0);
        UUID lessonId = lesson.getId();
		user = new User("Sofia", "Bacha", "sbacha@email.com", "1234567890", "sbacha", "Password#1", language , unitId, lessonId);
	}

	@BeforeEach
	public void setup() {
		ArrayList<Word> words = new ArrayList<>();
		words.add(new Word(new UUID(10,5),"hola","hi","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"jola","ji","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"bola","bi","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"cola","ci","interjection","_____, my llamo Cody"));
        question = new MultipleChoice(words, 100);
	}
	
	@AfterEach
	public void tearDown() {
        question = null;
	}
	
	
	@Test
	void testCorrectAnswer() {
        
		boolean correctAnswer = question.checkAnswer("cola");
		assertTrue(correctAnswer);
	}

	@Test
	void testCorrectNumberAnswer() {
        
		boolean correctAnswer = question.checkAnswer("4");
		assertTrue(correctAnswer);
	}

	@Test
	void testIncorrectAnswer() {
        
		boolean incorrectAnswer = question.checkAnswer("absdf");
		assertFalse(incorrectAnswer);
	}

	@Test
	void testNullAnswer() {
        
		boolean incorrectAnswer = question.checkAnswer(null);
		assertFalse(incorrectAnswer);
	}

	@Test
	void testBlankAnswer() {
        
		boolean incorrectAnswer = question.checkAnswer("");
		assertFalse(incorrectAnswer);
	}

	@Test
	void testOneWord() {
		ArrayList<Word> words = new ArrayList<>();
		words.add(new Word(new UUID(10,5),"hola","hi","interjection","_____, my llamo Cody"));
        question = new MultipleChoice(words,100);

		boolean correctAnswer = question.checkAnswer("hola");
		assertTrue(correctAnswer);
	}

	@Test
	void testNullWord() {
		try {
			ArrayList<Word> words = new ArrayList<>();
			words.add(null);
			question = new MultipleChoice(words,100);

			assertTrue(true);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test
	void testNoWords() {
		try {
			ArrayList<Word> words = new ArrayList<>();
			question = new MultipleChoice(words,100);

			assertTrue(true);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test
	void testRunCorrectAnswer() {
		try {
			question.run(user,"cola");

			assertTrue(true);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test
	void testRunIncorrectAnswer() {
		try {
			question.run(user,"bola");

			assertTrue(true);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}

}
