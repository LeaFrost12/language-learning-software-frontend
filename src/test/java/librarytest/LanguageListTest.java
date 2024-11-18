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

public class LanguageListTest {
    private LanguageList languageList = LanguageList.getInstance();
	private ArrayList<Language> languages = languageList.getLanguages();

    @BeforeEach
    public void setup() {
        LanguageList.getInstance();
        languages = LanguageList.getInstance().getLanguages();
    }

    @AfterEach
    public void tearDown() {
        LanguageList.getInstance().getLanguages().clear();
    }

    @Test
    public void testSingletonInstance() {
        LanguageList anotherInstance = LanguageList.getInstance();
        assertEquals(languageList, anotherInstance, "Should be the same instance");
    }

    @Test
    public void testGetLanguagesSize() {
        assertEquals(3, languages.size(), "Language List should contain 3 languages");
    }

    @Test
    public void testGetLanguagesFirstItem() {
        assertEquals("Spanish", languages.get(0).getLanguageName(), "First language is spanish");
    }

    @Test
    public void testGetLanguagesLastItem() {
        assertEquals("German", languages.get(2).getLanguageName(), "Third language is german");
    }

    @Test
    public void testGetLanguageFirstItem() {
        Language language = languageList.getLanguage("Spanish");
        assertEquals("Spanish", language.getLanguageName(), "Language name should be Spanish");
    }

    @Test
    public void testGetLanguageLastItem() {
        Language language = languageList.getLanguage("German");
        assertEquals("German", language.getLanguageName(), "Language name should be German");
    }

    @Test
    public void testGetLanguageInvalidLanguage() {
        assertNull(languageList.getLanguage("Mandarin"), "Should return null for a language not in the list");
    }

    @Test
    public void testGetLanguageEmpty() {
        assertNull(languageList.getLanguage(""), "Should return null for empty language");
    }

    @Test
    public void testGetLanguageByEnumFirstItem() {
        Language language = languageList.getLanguageByEnum(LanguagesEnum.SPANISH);
        assertEquals(LanguagesEnum.SPANISH, language.getLanguageEnum(), "Language enum should be SPANISH");
    }

    @Test
    public void testGetLanguageByEnumInvalidEnum() {
        assertNull(languageList.getLanguageByEnum(LanguagesEnum.GERMAN), "Should return null for an enum not in the list");
    }

    @Test
    public void testContainsValidFirstItem() {
        assertTrue(languageList.contains("Spanish"), "Should return true for existing language 'Spanish'");
    }

    @Test
    public void testContainsValidLastItem() {
        assertTrue(languageList.contains("German"), "Should return true for existing language 'German'");
    }

    @Test
    public void testContainsLanguageInvalid() {
        assertFalse(languageList.contains("Mandarin"));
    }

    @Test
    public void testContainsLanguageEmpty() {
        assertFalse(languageList.contains(""));
    }

}
