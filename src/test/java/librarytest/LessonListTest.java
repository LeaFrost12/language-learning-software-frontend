package librarytest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.UUID;

import com.model.Lesson;
import com.model.LessonList;
import com.model.WordList;

public class LessonListTest {

    private LessonList lessonList;
    private WordList wordList;
    private Lesson lesson1;
    private Lesson lesson2;
    private Lesson lesson3;

    @BeforeEach
    public void setUp() {
        // Create some sample Lesson objects with unique UUIDs for testing
        lesson1 = new Lesson(UUID.randomUUID(), "Introduction", 1, wordList);
        lesson2 = new Lesson(UUID.randomUUID(), "Basics", 2, wordList);
        lesson3 = new Lesson(UUID.randomUUID(), "Advanced", 3, wordList);

        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);

        // Initialize LessonList with initial lessons
        lessonList = new LessonList(lessons);
    }

    @Test
    public void testAddLesson() {
        lessonList.addLesson(lesson3);
        assertEquals(3, lessonList.getLessonCount(), "LessonList should contain 3 lessons after adding one.");
        assertTrue(lessonList.getLessons().contains(lesson3), "LessonList should contain the newly added lesson.");
    }

    @Test
    public void testRemoveLesson() {
        lessonList.removeLesson(lesson1);
        assertEquals(1, lessonList.getLessonCount(), "LessonList should contain 1 lesson after removing one.");
        assertFalse(lessonList.getLessons().contains(lesson1), "LessonList should no longer contain the removed lesson.");
    }

    @Test
    public void testGetLessonById() {
        UUID lessonId = lesson1.getId();
        assertEquals(lesson1, lessonList.getLessonById(lessonId), "Should retrieve the correct lesson by UUID.");
        
        // Test retrieval with a non-existent UUID
        assertNull(lessonList.getLessonById(UUID.randomUUID()), "Retrieving with non-existent UUID should return null.");
    }

    @Test
    public void testRestartLessons() {
        lessonList.goToNextLesson(); // Move to the next lesson
        lessonList.restartLessons(); // Reset to the beginning
        assertEquals(lesson1, lessonList.getCurrentLesson(), "After restart, current lesson should be the first lesson.");
    }

    @Test
    public void testGetCurrentLesson() {
        assertEquals(lesson1, lessonList.getCurrentLesson(), "Current lesson should initially be the first lesson.");
    }

    @Test
    public void testNextLesson() {
        Lesson nextLesson = lessonList.nextLesson(lesson1);
        assertEquals(lesson2, nextLesson, "Next lesson after lesson1 should be lesson2.");

        // Test with the last lesson in the list
        nextLesson = lessonList.nextLesson(lesson2);
        assertNull(nextLesson, "Next lesson after the last lesson should return null.");
    }

    @Test
    public void testGoToNextLesson() {
        assertTrue(lessonList.goToNextLesson(), "Should successfully move to the next lesson.");
        assertEquals(lesson2, lessonList.getCurrentLesson(), "Current lesson should update to the next lesson.");

        // Attempt to go beyond the last lesson
        assertFalse(lessonList.goToNextLesson(), "Should not be able to move to the next lesson past the last one.");
    }

    @Test
    public void testDisplayLessons() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        lessonList.displayLessons();
        String expectedOutput = lesson1.toString() + "\n" + lesson2.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString(), "Display output should match the format of all lessons.");
    }
}
