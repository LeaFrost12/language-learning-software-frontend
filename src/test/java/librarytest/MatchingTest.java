package librarytest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

public class MatchingTest {
    
    private User user;
    private Matching question;
    private HashMap<String,String> testAnswer;

	@Before
	public void setupUser() {
		Language language = LanguageList.getInstance().getLanguageByEnum(LanguagesEnum.SPANISH);
		Unit unit = language.getUnitList().getUnit(0);
        UUID unitId = unit.getId();

        Lesson lesson = unit.getLessonList().getLesson(0);
        UUID lessonId = lesson.getId();
		user = new User("Sofia", "Bacha", "sbacha@email.com", "1234567890", "sbacha", "Password#1", language , unitId, lessonId);

        testAnswer.put("ci","cola");
		testAnswer.put("bi","bola");
		testAnswer.put("ji","jola");
		testAnswer.put("hi","hola");
	}

	@BeforeEach
	public void setup() {
		ArrayList<Word> words = new ArrayList<>();
		words.add(new Word(new UUID(10,5),"hola","hi","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"jola","ji","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"bola","bi","interjection","_____, my llamo Cody"));
		words.add(new Word(new UUID(10,5),"cola","ci","interjection","_____, my llamo Cody"));
        question = new Matching(words, 100);
	}
	
	@AfterEach
	public void tearDown() {
        question = null;
	}
	
	
	@Test
	void testCorrectAnswer() {

		if(!question.answerPart(0, user, "cola"))
			assertTrue(false);
		else if(!question.answerPart(1, user, "bola"))
			assertTrue(false);
		else if(!question.answerPart(2, user, "jola"))
			assertTrue(false);
		else if(!question.answerPart(3, user, "hola"))
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	void testIncorrectAnswer() {
        
		if(!question.answerPart(0, user, "cola"))
			assertTrue(false);
		else if(!question.answerPart(1, user, "bola"))
			assertTrue(false);
		else if(!question.answerPart(2, user, "hola"))
			assertTrue(false);
		else if(!question.answerPart(3, user, "jola"))
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	void testNullAnswer() {
        
		if(!question.answerPart(0, user, null))
			assertTrue(false);
		else if(!question.answerPart(1, user, "bola"))
			assertTrue(false);
		else if(!question.answerPart(2, user, "hola"))
			assertTrue(false);
		else if(!question.answerPart(3, user, "jola"))
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	void testBlankAnswer() {
        if(!question.answerPart(0, user, ""))
			assertTrue(false);
		else if(!question.answerPart(1, user, ""))
			assertTrue(false);
		else if(!question.answerPart(2, user, ""))
			assertTrue(false);
		else if(!question.answerPart(3, user, ""))
			assertTrue(false);
		else
			assertTrue(true);
	}

	@Test
	void testOneWord() {
		ArrayList<Word> words = new ArrayList<>();
		words.add(new Word(new UUID(10,5),"hola","hi","interjection","_____, my llamo Cody"));
        question = new Matching(words,100);

		HashMap testMap = new HashMap<>();
		testMap.put("hola","hi");

		boolean correctAnswer = question.checkAnswer(testMap);
		assertTrue(correctAnswer);
	}

	@Test
	void testNullWord() {
		try {
			ArrayList<Word> words = new ArrayList<>();
			words.add(null);
			question = new Matching(words,100);

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
			question = new Matching(words,100);

			assertTrue(true);
		}
		catch(Exception e) {
			assertTrue(false);
		}
	}

}
