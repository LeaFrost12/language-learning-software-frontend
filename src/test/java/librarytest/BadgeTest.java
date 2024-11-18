package librarytest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Badge;

/**
 * Tests for the Badge class.
 * @author Madeleine McBride
 */

public class BadgeTest {

    @BeforeEach
    public void resetBadges() {
        // Clear the badge list before each test to prevent interference between tests
        Badge.badges.clear();
    }

    @Test
    public void testAddBadgeToCollection() {
        Badge badge = new Badge("Achievement Badge", "Awarded for completing a milestone.");
        Badge.addBadge(badge);
        assertTrue(Badge.badges.contains(badge), "Badge collection should contain the newly added badge.");
        assertEquals(1, Badge.badges.size(), "Badge collection size should be 1 after adding one badge.");
    }

    @Test
    public void testUniqueUUIDsForDifferentBadges() {
        Badge badge1 = new Badge("Unique Badge 1", "First unique badge.");
        Badge badge2 = new Badge("Unique Badge 2", "Second unique badge.");
        assertNotEquals(badge1.getID(), badge2.getID(), "Each badge should have a unique UUID.");
    }

    @Test
    public void testRetrieveBadgeByNonExistentUUID() {
        Badge badge = new Badge("Sample Badge", "Badge to test retrieval.");
        Badge.addBadge(badge);
        UUID nonExistentID = UUID.randomUUID();
        assertNull(Badge.getBadgeByUUID(nonExistentID), "Retrieving a badge with a non-existent UUID should return null.");
    }

    @Test
    public void testDisplayBadgeOutputFormat() {
        Badge badge = new Badge("Format Badge", "Badge with specific output format.");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        badge.displayBadge();
        assertEquals("Badge: Format Badge - Badge with specific output format.\n", outContent.toString());
    }

    @Test
    public void testDisplayBadgesWithMultipleBadges() {
        Badge badge1 = new Badge("Multi Badge 1", "Description for badge 1.");
        Badge badge2 = new Badge("Multi Badge 2", "Description for badge 2.");
        Badge.addBadge(badge1);
        Badge.addBadge(badge2);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        Badge.displayBadges();
        String expectedOutput = "Badge: Multi Badge 1 - Description for badge 1.\n" +
                                "Badge: Multi Badge 2 - Description for badge 2.\n";
        assertEquals(expectedOutput, outContent.toString(), "Display output should match expected format with multiple badges.");
    }

    @Test
    public void testPreventDuplicateBadge() {
        Badge badge1 = new Badge("Unique Badge", "This is a unique badge.");
        Badge.addBadge(badge1);

        // Attempt to add the same badge again
        Badge.addBadge(badge1);

        assertEquals(1, Badge.badges.size(), "Duplicate badge should not be added to the collection.");
    }

    @Test
    public void testBadgeCollectionSizeAfterAddition() {
        Badge badge1 = new Badge("Size Test Badge 1", "Testing collection size after adding badges.");
        Badge badge2 = new Badge("Size Test Badge 2", "Testing collection size for multiple additions.");
        Badge.addBadge(badge1);
        Badge.addBadge(badge2);

        assertEquals(2, Badge.badges.size(), "The size of the badge collection should match the number of added badges.");
    }

    @Test
    public void testClearBadges() {
        Badge badge1 = new Badge("Clear Test Badge 1", "Badge to test clear functionality.");
        Badge badge2 = new Badge("Clear Test Badge 2", "Another badge for testing clear.");
        Badge.addBadge(badge1);
        Badge.addBadge(badge2);

        Badge.badges.clear();  // Manually clearing the badge list for test purposes
        assertTrue(Badge.badges.isEmpty(), "Badge collection should be empty after clearing.");
    }
}