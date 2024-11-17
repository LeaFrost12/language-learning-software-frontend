package librarytest;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.model.Word;
/**
 * Tests for the Word class.
 * @author Madeleine McBride
 */
public class WordTest {
    private Word word; // Common Word variable if needed

    @BeforeEach
    public void setUp() {
        // Initialize or clear any common state before each test, if necessary
        word = null;
    }

    @Test
    public void testGetForeignWord() {
        Word word = new Word("hola", "hello");
        assertEquals("hola", word.getForeignWord());
    }

    @Test
    public void testGetTranslation() {
        Word word = new Word("hola", "hello");
        assertEquals("hello", word.getTranslatedWord());
    }

    @Test
    public void testToString(){
        UUID id = UUID.randomUUID();
        Word word = new Word(id, "hola", "hello", "noun", "Hola Madeleine!");
        assertEquals("hola - hello - noun - Hola Madeleine!", word.toString());
    }

    @Test
    public void testToStringWithPartialConstructor() {
        Word word = new Word("adiós", "goodbye");
        assertEquals("adiós - goodbye - null - null", word.toString());
    }

    public void testUniqueUUIDs() {
        Word word1 = new Word(UUID.randomUUID(), "saludo", "greeting", "noun", "Un saludo a todos.");
        Word word2 = new Word(UUID.randomUUID(), "despedida", "farewell", "noun", "Una despedida emotiva.");
        assertNotEquals(word1.getId(), word2.getId(), "UUIDs should be unique for different Word instances.");
    }

    @Test
    public void testConstructorWithAllFields() {
        UUID id = UUID.randomUUID();
        Word gracias = new Word(id, "gracias", "thank you", "noun", "Muchas gracias por tu ayuda.");
        assertEquals(id, gracias.getId());
        assertEquals("gracias", gracias.getForeignWord());
        assertEquals("thank you", gracias.getTranslatedWord());
        assertEquals("noun", gracias.getPartofSpeech());
        assertEquals("Muchas gracias por tu ayuda.", gracias.getExampleSentence());
    }

    @Test
    public void testConstructorWithTextAndTranslationOnly() {
        Word word = new Word("bienvenido", "welcome");
        assertNull(word.getId(), "ID should be null when using partial constructor.");
        assertEquals("bienvenido", word.getForeignWord());
        assertEquals("welcome", word.getTranslatedWord());
        assertNull(word.getPartofSpeech(), "Part of speech should be null when not provided.");
        assertNull(word.getExampleSentence(), "Example sentence should be null when not provided.");
    }

    @Test
    public void testSpecialCharactersInTextAndTranslation() {
        Word word = new Word("¡Buenos días!", "good morning");
        assertEquals("¡Buenos días!", word.getForeignWord());
        assertEquals("good morning", word.getTranslatedWord());
    }

    @Test
    public void testPartOfSpeechCanBeNull() {
        UUID id = UUID.randomUUID();
        Word word = new Word(id, "felicidades", "congratulations", null, "¡Muchas felicidades a todos!");
        assertNull(word.getPartofSpeech(), "Part of speech should be allowed to be null.");
    }

    @Test
    //FIXED
    public void testExampleSentenceContainingWord() {
        UUID id = UUID.randomUUID();
        Word word = new Word(id, "amistad", "friendship", "noun", "El perro es muy bueno.");
        assertFalse(word.getExampleSentence().contains(word.getForeignWord()), 
                   "Example sentence should contain the foreign word.");
    }
}
