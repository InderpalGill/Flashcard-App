package persistence;

import model.Flashcard;
import model.FlashcardDeck;
import model.FlashcardDecks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Class that is used to create tests for the JsonWriter Class
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {
    FlashcardDecks flashcardDecks;
    FlashcardDeck testFlashcardDeck1;
    FlashcardDeck testFlashcardDeck2;
    Flashcard testFlashcard1;
    Flashcard testFlashcard2;
    Flashcard testFlashcard3;

    @BeforeEach
    void runBefore() {
        flashcardDecks = new FlashcardDecks("My test decks");
        testFlashcardDeck1 = new FlashcardDeck("test deck1");
        testFlashcardDeck2 = new FlashcardDeck("test deck2");
        testFlashcard1 = new Flashcard("What year is it?", "2024");
        testFlashcard2 = new Flashcard("What color is the sky?", "Blue");
        testFlashcard3 = new Flashcard("How many centimeters in an inch?", "2.54");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFlashcardDecks() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFlashcardDecks.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFlashcardDecks.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(0, flashcardDecks.getSizeFlashcardDecks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksWithOneFlashcardDeckWithOneFlashcard() {
        try {
            testFlashcardDeck1.addCard(testFlashcard1);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            JsonWriter writer = new JsonWriter("./data/testWriterOneFlashcardDeckWithOneCard.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterOneFlashcardDeckWithOneCard.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(1, flashcardDecks.getSizeFlashcardDecks());
            assertEquals(1, flashcardDecks.getFlashcardDeckFromPosition(1).deckSize());
            assertEquals("test deck1", flashcardDecks.getFlashcardDeckFromPosition(1).getName());
            checkFlashcardQuestion("What year is it?", 1, 0, flashcardDecks);
            checkFlashcardAnswer("2024", 1, 0, flashcardDecks);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksWithOneFlashcardDeckWithThreeFlashcards() {
        try {
            testFlashcardDeck1.addCard(testFlashcard2);
            testFlashcardDeck1.addCard(testFlashcard3);
            testFlashcardDeck1.addCard(testFlashcard1);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            JsonWriter writer = new
                    JsonWriter("./data/testWriterFlashcardDecksWithOneFlashcardDeckWithThreeFlashcards.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();
            JsonReader reader = new JsonReader
                    ("./data/testWriterFlashcardDecksWithOneFlashcardDeckWithThreeFlashcards.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(1, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 3, flashcardDecks);
            checkFlashcardQuestion("What year is it?", 1, 2, flashcardDecks );
            checkFlashcardQuestion("What color is the sky?", 1, 0, flashcardDecks );
            checkFlashcardQuestion("How many centimeters in an inch?", 1, 1,
                    flashcardDecks );
            checkFlashcardAnswer("2024", 1, 2, flashcardDecks);
            checkFlashcardAnswer("Blue", 1, 0, flashcardDecks);
            checkFlashcardAnswer("2.54", 1, 1, flashcardDecks);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksTwoFlashcardDecksOneEmptyOneWithTwoCards() {
        try {
            testFlashcardDeck2.addCard(testFlashcard1);
            testFlashcardDeck2.addCard(testFlashcard2);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck2);
            JsonWriter writer = new
                    JsonWriter("./data/testWriterFlashcardDecksTwoFlashcardDecksOneEmptyOneWithTwoCards.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();
            JsonReader reader = new JsonReader
                    ("./data/testWriterFlashcardDecksTwoFlashcardDecksOneEmptyOneWithTwoCards.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(2, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 0, flashcardDecks);
            checkFlashcardDeck("test deck2", 2, 2, flashcardDecks);
            checkFlashcardQuestion("What year is it?", 2, 0, flashcardDecks );
            checkFlashcardQuestion("What color is the sky?", 2, 1, flashcardDecks );
            checkFlashcardAnswer("2024", 2, 0, flashcardDecks);
            checkFlashcardAnswer("Blue", 2, 1, flashcardDecks);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksTwoFlashcardDecksBothEmpty() {
        try {
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck2);
            JsonWriter writer = new
                    JsonWriter("./data/testWriterFlashcardDecksTwoFlashcardDecksBothEmpty.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();
            JsonReader reader = new JsonReader
                    ("./data/testWriterFlashcardDecksTwoFlashcardDecksBothEmpty.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(2, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 0, flashcardDecks);
            checkFlashcardDeck("test deck2", 2, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksTwoFlashcardDecksBothWithOneCard() {
        try {
            testFlashcardDeck1.addCard(testFlashcard1);
            testFlashcardDeck2.addCard(testFlashcard2);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            flashcardDecks.addFlashcardDeck(testFlashcardDeck2);
            JsonWriter writer = new
                    JsonWriter("./data/testWriterFlashcardDecksTwoFlashcardDecksBothWithOneCard.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();
            JsonReader reader = new JsonReader
                    ("./data/testWriterFlashcardDecksTwoFlashcardDecksBothWithOneCard.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(2, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 1, flashcardDecks);
            checkFlashcardDeck("test deck2", 2, 1, flashcardDecks);
            checkFlashcardQuestion("What year is it?", 1, 0, flashcardDecks );
            checkFlashcardQuestion("What color is the sky?", 2, 0, flashcardDecks );
            checkFlashcardAnswer("2024", 1, 0, flashcardDecks);
            checkFlashcardAnswer("Blue", 2, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFlashcardDecksOneFlashcardDeckEmpty() {
        try {
            flashcardDecks.addFlashcardDeck(testFlashcardDeck1);
            JsonWriter writer = new
                    JsonWriter("./data/testWriterFlashcardDecksOneFlashcardDeckEmpty.json");
            writer.open();
            writer.write(flashcardDecks);
            writer.close();
            JsonReader reader = new JsonReader
                    ("./data/testWriterFlashcardDecksOneFlashcardDeckEmpty.json");
            flashcardDecks = reader.read();
            assertEquals("My test decks", flashcardDecks.getName());
            assertEquals(1, flashcardDecks.getSizeFlashcardDecks());
            checkFlashcardDeck("test deck1", 1, 0, flashcardDecks);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
