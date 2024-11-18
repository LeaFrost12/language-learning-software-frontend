package librarytest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Badge;
import com.model.BadgeList;

/**
 * Tests for the BadgeList class.
 * @author Madeleine McBride
 */

public class BadgeListTest {

    private BadgeList badgeList;

    @BeforeEach
    public void setUp() {
        // Clear badges before each test by resetting the singleton
        badgeList = BadgeList.getInstance();
        badgeList.getBadges().clear();
    }

    @Test
    public void testSingletonInstance() {
        BadgeList instance1 = BadgeList.getInstance();
        BadgeList instance2 = BadgeList.getInstance();
        assertSame(instance1, instance2, "BadgeList should follow singleton pattern, returning the same instance.");
    }

    @Test
    public void testAddBadgeToList() {
        Badge badge = new Badge("Unique Badge", "Awarded for a unique achievement.");
        badgeList.addBadge(badge);

        assertTrue(badgeList.getBadges().contains(badge), "BadgeList should contain the added badge.");
        assertEquals(1, badgeList.getBadges().size(), "BadgeList size should be 1 after adding one badge.");
    }

    @Test
    public void testPreventDuplicateBadgeAddition() {
        Badge badge = new Badge("Duplicate Test Badge", "Testing duplicate prevention.");
        badgeList.addBadge(badge);

        // Attempt to add the same badge again
        badgeList.addBadge(badge);

        // Verify that only one instance of the badge exists in the list
        assertEquals(1, badgeList.getBadges().size(), "Duplicate badge should not be added to the collection.");
    }

    @Test
    public void testRetrieveNonExistentBadge() {
        Badge badge = new Badge("Existing Badge", "This badge exists in the list.");
        badgeList.addBadge(badge);
        
        UUID randomUUID = UUID.randomUUID();
        assertNull(badgeList.getBadgeByUUID(randomUUID), "Retrieving a non-existent UUID should return null.");
    }

    @Test
    public void testDisplayBadgesFormat() {
        Badge badge1 = new Badge("Display Badge 1", "First badge for display test.");
        Badge badge2 = new Badge("Display Badge 2", "Second badge for display test.");
        badgeList.addBadge(badge1);
        badgeList.addBadge(badge2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        badgeList.displayBadges();

        String expectedOutput = "Badge: Display Badge 1 - First badge for display test.\n" +
                                "Badge: Display Badge 2 - Second badge for display test.\n";
        assertEquals(expectedOutput, outContent.toString(), "Display output should match expected format for all badges.");
    }
}
