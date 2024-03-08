package persistence;

import model.FlashcardDecks;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

// Class that is used to test the JsonReader class
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FlashcardDecks fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFlashcardDecks() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFlashcardDecks.json");
        try {
            FlashcardDecks flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(0, flashcardDecks.getSizeFlashcardDecks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFlashcardDecksWithOneEmptyFlashcardDeck() {
        JsonReader reader = new
                JsonReader("./data/testReaderFlashcardDecksWithOneEmptyFlashcardDeck.json");
        try {
            FlashcardDecks flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(1, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFlashcardDecksWithOneFlashcardDeckWithOneFlashcard() {
        JsonReader reader = new
                JsonReader("./data/testReaderFlashcardDecksWithOneFlashcardDeckWithOneFlashcard.json");
        try {
            FlashcardDecks flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(1, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 1, flashcardDecks);
            checkFlashcardQuestion("What is 1 + 1?", 1, 0, flashcardDecks);
            checkFlashcardAnswer("2", 1, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFlashcardDecksWithTwoEmptyFlashcardDeck() {
        JsonReader reader = new
                JsonReader("./data/testReaderFlashcardDecksWithTwoEmptyFlashcardDeck.json");
        try {
            FlashcardDecks flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(2, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("deck1", 1, 0, flashcardDecks);
            checkFlashcardDeck("deck2", 2, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFlashcardDecksWithTwoFlashcardDeck() {
        JsonReader reader = new
                JsonReader("./data/testReaderFlashcardDecksWithTwoFlashcardDeck.json");
         try {
             FlashcardDecks flashcardDecks = reader.read();
             assertEquals("Testing Deck", flashcardDecks.getName());
             assertEquals(2, flashcardDecks.getSizeFlashcardDecks());
             checkFlashcardDeck("Geography Deck", 1, 1, flashcardDecks);
             checkFlashcardDeck("Science Deck", 2, 2, flashcardDecks);
             checkFlashcardQuestion("What is the capital of Canada?", 1, 0,
                     flashcardDecks);
             checkFlashcardAnswer("Ottawa", 1, 0, flashcardDecks);
             checkFlashcardQuestion("Which element has atomic Number 2?", 2, 0,
                     flashcardDecks);
             checkFlashcardAnswer("Helium", 2, 0, flashcardDecks);
             checkFlashcardQuestion("What is the powerhouse of the cell?", 2, 1,
                     flashcardDecks);
             checkFlashcardAnswer("Mitochondrion", 2, 1, flashcardDecks);
         } catch (IOException e) {
             fail("Couldn't read from file");
         }
    }
}
