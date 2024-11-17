package librarytest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.*;

import java.util.ArrayList;
import java.util.UUID;

public class UnitListTest {

    private UnitList unitList;
    private Unit unit1;
    private Unit unit2;
    private Unit unit3;

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
        lessons.add(lesson3);

        // Initialize LessonList with initial lessons
        lessonList = new LessonList(lessons);

        // Create sample units for testing
        unit1 = new Unit(UUID.randomUUID(), "Unit 1", 1, lessonList, unitList);
        unit2 = new Unit(UUID.randomUUID(), "Unit 2", 2, lessonList, unitList);
        unit3 = new Unit(UUID.randomUUID(), "Unit 3", 3, lessonList, unitList);

        // Initialize UnitList with predefined units
        ArrayList<Unit> units = new ArrayList<>();
        units.add(unit1);
        units.add(unit2);
        unitList = new UnitList(units);
    }

    @Test
    public void testAddUnit() {
        unitList.addUnit(unit3);
        assertTrue(unitList.getUnits().contains(unit3), "UnitList should contain the newly added unit.");
        assertEquals(3, unitList.getUnits().size(), "UnitList should have 3 units after adding one.");
    }

    @Test
    public void testRemoveUnit() {
        unitList.removeUnit(unit1);
        assertFalse(unitList.getUnits().contains(unit1), "UnitList should no longer contain the removed unit.");
        assertEquals(1, unitList.getUnits().size(), "UnitList should have 1 unit after removing one.");
    }

    @Test
    public void testGetUnitByNumber() {
        assertEquals(unit1, unitList.getUnit(1), "Should retrieve the correct unit by its unit number.");
        assertNull(unitList.getUnit(4), "Retrieving a unit with a non-existent number should return null.");
    }

    @Test
    public void testGetUnitById() {
        UUID unitId = unit2.getId();
        assertEquals(unit2, unitList.getUnitById(unitId), "Should retrieve the correct unit by UUID.");

        // Test with a non-existent UUID
        assertNull(unitList.getUnitById(UUID.randomUUID()), "Retrieving with a non-existent UUID should return null.");
    }

    @Test
    public void testGetUnitNumberById() {
        UUID unitId = unit1.getId();
        assertEquals(0, unitList.getUnitNumberById(unitId), "Unit number should match the index in the list.");

        // Test with a non-existent UUID
        assertEquals(-1, unitList.getUnitNumberById(UUID.randomUUID()), "Retrieving with non-existent UUID should return -1.");
    }

    @Test
    public void testGoToNextUnit() {
        assertTrue(unitList.goToNextUnit(), "Should successfully move to the next unit.");
        assertEquals(unit2, unitList.getCurrentUnit(), "Current unit should update to the next unit.");

        // Attempt to go beyond the last unit
        assertFalse(unitList.goToNextUnit(), "Should not be able to move to the next unit past the last one.");
    }

    @Test
    public void testNextUnit() {
        Unit nextUnit = unitList.nextUnit(unit1);
        assertEquals(unit2, nextUnit, "The next unit after unit1 should be unit2.");

        // Test advancing beyond the last unit
        nextUnit = unitList.nextUnit(unit2);
        assertNull(nextUnit, "Next unit should be null after the last unit.");
    }

    @Test
    public void testRestartUnit() {
        unitList.goToNextUnit(); // Move to unit2
        Unit restartedUnit = unitList.restartUnit(unit2);
        
        assertEquals(unit2, restartedUnit, "Restarting a unit should return the same unit.");
        assertEquals(0, restartedUnit.getLessonList().getCurrentLessonIndex(), "The lesson list should reset to the first lesson.");
    }
}
