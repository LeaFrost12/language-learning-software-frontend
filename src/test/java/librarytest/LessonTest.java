package librarytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import com.model.*;

public class LessonTest {

    private Lesson lesson;
    private User mockUser;
    private WordList wordList;
    private static LanguageList languageList;

    @BeforeEach
    public void setup() {
        wordList = new WordList(new ArrayList<>());
        wordList.addWord(new Word("Hola", "Hello", "greeting", "Hola amigo!"));
        wordList.addWord(new Word("Adios", "Goodbye", "farewell", "Adios, amigo!"));

        lesson = new Lesson("Sample Lesson", 1, wordList);

        languageList = LanguageList.getInstance();

        mockUser = new User("Test", "User", "test@example.com", "1234567890", "testUser", "Password123!", languageList.getLanguageByEnum(LanguagesEnum.SPANISH), UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    public void runRandomQuestion_ShouldReturnTrue_ForCorrectAnswer() {
        boolean result = lesson.runRandomQuestion(mockUser);
        assertTrue(result, "runRandomQuestion should return true for a correct answer.");
    }

    @Test
    public void canMoveToNextLesson_ShouldReturnTrue_WhenSufficientCorrectAnswers() {
        for (int i = 0; i < 4; i++) {
            lesson.runRandomQuestion(mockUser);
        }
        assertTrue(lesson.canMoveToNextLesson(), "User should be able to move to the next lesson after sufficient correct answers.");
    }

    @Test
    public void shouldFailCurrentLesson_ShouldReturnTrue_WhenExceededMaxWrongAnswers() {
        for (int i = 0; i < 1 + 1; i++) {
            lesson.runRandomQuestion(mockUser);
        }
        assertTrue(lesson.shouldFailCurrentLesson(), "User should fail the lesson after exceeding maximum wrong answers.");
    }

    @Test
    public void getCorrectAnswerCount_ShouldReturnExpectedCount() {
        int correctAnswers = 3;
        for (int i = 0; i < correctAnswers; i++) {
            lesson.runRandomQuestion(mockUser);
        }
        int result = lesson.getCorrectAnswerCount();
        assertEquals(correctAnswers, result, "getCorrectAnswerCount should return the number of correct answers given.");
    }

    @Test
    public void getWrongAnswerCount_ShouldReturnExpectedCount() {
        int wrongAnswers = 2;
        for (int i = 0; i < wrongAnswers; i++) {
            lesson.runRandomQuestion(mockUser);
        }
        int result = lesson.getWrongAnswerCount();
        assertEquals(wrongAnswers, result, "getWrongAnswerCount should return the number of wrong answers given.");
    }

    @Test
    public void learn_ShouldRunWithoutExceptions() {
        assertDoesNotThrow(() -> lesson.learn(), "learn should run without throwing exceptions.");
    }

    @Test
    public void toString_ShouldReturnExpectedFormat() {
        String expectedOutput = "Lesson Name: Sample Lesson\nLesson Number: 1";
        String result = lesson.toString();
        assertEquals(expectedOutput, result, "toString should return the expected format.");
    }
}
