package librarytest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

import java.util.ArrayList;
import java.util.UUID;

public class UnitTest {

    private Unit unit;
    private Unit unit2;
    private LessonList lessonList;
    private UnitList unitList;
    private Lesson lesson1;
    private Lesson lesson2;
    private Lesson lesson3;
    private WordList wordList;

    @BeforeEach
    public void setUp() {
        // Create sample Word objects
        Word word1 = new Word(UUID.randomUUID(), "Hola", "Hello", "noun", "Hola, amigo!");
        Word word2 = new Word(UUID.randomUUID(), "Adiós", "Goodbye", "noun", "Adiós, hasta luego.");
        
        // Create a list of Words and initialize WordList with it
        ArrayList<Word> words = new ArrayList<>();
        words.add(word1);
        words.add(word2);
        wordList = new WordList(words);

        // Initialize sample lessons using the new Lesson constructor
        lesson1 = new Lesson(UUID.randomUUID(), "Lesson 1", 1, wordList);
        lesson2 = new Lesson(UUID.randomUUID(), "Lesson 2", 2, wordList);
        lesson3 = new Lesson(UUID.randomUUID(), "Lesson 3", 3, wordList);
        
        ArrayList<Lesson> lessons = new ArrayList<>();
        lessons.add(lesson1);
        lessons.add(lesson2);
        lessonList = new LessonList(lessons);

        // Initialize units
        unit = new Unit(UUID.randomUUID(), "Unit 1", 1, lessonList, null); // UnitList to be set after creation
        unit2 = new Unit(UUID.randomUUID(), "Unit 2", 2, new LessonList(new ArrayList<>()), null);

        // Create and initialize UnitList with existing units
        ArrayList<Unit> units = new ArrayList<>();
        units.add(unit);
        units.add(unit2);
        unitList = new UnitList(units);

        // Set unitList reference for each unit
        unit = new Unit(unit.getId(), unit.getUnitName(), unit.getUnitNumber(), unit.getLessonList(), unitList);
        unit2 = new Unit(unit2.getId(), unit2.getUnitName(), unit2.getUnitNumber(), unit2.getLessonList(), unitList);
    }

    @Test
    public void testAddLessonToUnit() {
        unit.addLesson(lesson3);
        assertTrue(unit.getLessons().contains(lesson3), "Unit should contain the newly added lesson.");
        assertEquals(3, unit.getLessons().size(), "Unit should have 3 lessons after adding one.");
    }

    @Test
    public void testRemoveLessonFromUnit() {
        unit.removeLesson(lesson1);
        assertFalse(unit.getLessons().contains(lesson1), "Unit should no longer contain the removed lesson.");
        assertEquals(1, unit.getLessons().size(), "Unit should have 1 lesson after removing one.");
    }

    @Test
    public void testNextLessonInUnit() {
        Lesson nextLesson = unit.nextLesson(lesson1);
        assertEquals(lesson2, nextLesson, "The next lesson after lesson1 should be lesson2.");

        // Test advancing beyond the last lesson
        nextLesson = unit.nextLesson(lesson2);
        assertNull(nextLesson, "Next lesson should be null after the last lesson.");
    }

    @Test
    public void testRestartUnit() {
        unit.addLesson(lesson3);
        unit.getLessonList().goToNextLesson(); // Move to lesson 2
        unit.restartUnit(); // Restart the unit
        assertEquals(lesson1, unit.getLessonList().getCurrentLesson(), 
                     "After restart, the current lesson should be reset to the first lesson.");
    }

    @Test
    public void testNextUnit() {
        Unit nextUnit = unit.nextUnit();
        assertEquals(unit2, nextUnit, "The next unit after unit1 should be unit2.");

        // Test moving beyond the last unit
        Unit beyondLastUnit = unit2.nextUnit();
        assertNull(beyondLastUnit, "Next unit after the last unit should return null.");
    }

    @Test
    public void testUnitToStringFormat() {
        String expectedOutput = "Unit Name: Unit 1 Unit Number: 1";
        assertEquals(expectedOutput, unit.toString(), "The unit string representation should match the expected format.");
    }
}
