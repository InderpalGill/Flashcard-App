package model;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FlashcardDeckTest {
    private FlashcardDeck testFlashcardDeck;
    private Flashcard testFlashcard1;
    private Flashcard testFlashcard2;
    private Flashcard testFlashcard3;

    @BeforeEach
    public void runBefore() {
        testFlashcardDeck = new FlashcardDeck("test");
        testFlashcard1 = new Flashcard("What is 1 + 1?", "2");
        testFlashcard2 = new Flashcard("What is the capital of Canada?", "Ottawa");
        testFlashcard3 = new Flashcard("Is a tomato a fruit?", "Yes");

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testFlashcardDeck.getFlashcards().size());
        assertEquals(0, testFlashcardDeck.getSizeTracker());
        assertEquals("test", testFlashcardDeck.getName());
    }

    @Test
    public void testAddCardOneCardQuestionAnswerParameter() {
        testFlashcardDeck.addCard("What is 1 + 1?", "2");
        assertEquals(1, testFlashcardDeck.getFlashcards().size());
        assertEquals(1, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testAddCardOneCardFlashcardParameter() {
        testFlashcardDeck.addCard(testFlashcard1);
        assertEquals(1, testFlashcardDeck.getFlashcards().size());
        assertEquals(1, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testAddCardTwoCardsQuestionAnswerParameter() {
        testFlashcardDeck.addCard("What is 1 + 1?", "2");
        testFlashcardDeck.addCard("What is the Capital of Canada?", "Ottawa");
        assertEquals(2, testFlashcardDeck.getFlashcards().size());
        assertEquals(2, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testAddCardTwoCardsFlashcardParameter() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        assertEquals(2, testFlashcardDeck.getFlashcards().size());
        assertEquals(2, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(1));
        assertEquals(testFlashcard1, testFlashcardDeck.getCardFromIndex(0));
    }

    @Test
    public void testRemoveCardOneQuestionAnswerParameter() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        assertEquals(3, testFlashcardDeck.getFlashcards().size());
        assertEquals(3, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard3, testFlashcardDeck.getCardFromIndex(2));
        testFlashcardDeck.removeCard("Is a tomato a fruit?");
        assertEquals(2, testFlashcardDeck.getFlashcards().size());
        assertEquals(2, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(1));
        testFlashcardDeck.removeCard("What is 1 + 1?");
        assertEquals(1, testFlashcardDeck.getFlashcards().size());
        assertEquals(1, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(0));
        testFlashcardDeck.removeCard("What is the capital of Canada?");
        assertEquals(0, testFlashcardDeck.getFlashcards().size());
        assertEquals(0, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testRemoveCardNoCardMatches() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        assertEquals(3, testFlashcardDeck.getFlashcards().size());
        assertEquals(3, testFlashcardDeck.getSizeTracker());
        testFlashcardDeck.removeCard("How old is Canada?");
        assertEquals(3, testFlashcardDeck.getFlashcards().size());
        assertEquals(3, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard1, testFlashcardDeck.getCardFromIndex(0));
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(1));
        assertEquals(testFlashcard3, testFlashcardDeck.getCardFromIndex(2));
    }

    @Test
    public void testRemoveCardNoCardsInDeck() {
        assertEquals(0, testFlashcardDeck.getFlashcards().size());
        assertEquals(0, testFlashcardDeck.getSizeTracker());
        testFlashcardDeck.removeCard("What is the capital of Canada?");
        assertEquals(0, testFlashcardDeck.getFlashcards().size());
        assertEquals(0, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testRemoveCardFlashcardParameter() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        assertEquals(3, testFlashcardDeck.getFlashcards().size());
        assertEquals(3, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard3, testFlashcardDeck.getCardFromIndex(2));
        testFlashcardDeck.removeCard(testFlashcard3);
        assertEquals(2, testFlashcardDeck.getFlashcards().size());
        assertEquals(2, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(1));
        testFlashcardDeck.removeCard(testFlashcard1);
        assertEquals(1, testFlashcardDeck.getFlashcards().size());
        assertEquals(1, testFlashcardDeck.getSizeTracker());
        assertEquals(testFlashcard2, testFlashcardDeck.getCardFromIndex(0));
        testFlashcardDeck.removeCard(testFlashcard2);
        assertEquals(0, testFlashcardDeck.getFlashcards().size());
        assertEquals(0, testFlashcardDeck.getSizeTracker());
    }

    @Test
    public void testMarkCardCorrect() {
        testFlashcardDeck.setCurrentCard(testFlashcard1);
        assertEquals(testFlashcard1, testFlashcardDeck.getCurrentCard());
        assertEquals(0, testFlashcardDeck.getCorrectTracker());
        testFlashcardDeck.markCardCorrect();
        assertTrue(testFlashcard1.getIsCorrect());
        assertEquals(1, testFlashcardDeck.getCorrectTracker());
    }

    @Test
    public void testResetCards() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        testFlashcardDeck.setCurrentCard(testFlashcard1);
        testFlashcardDeck.markCardCorrect();
        testFlashcardDeck.setCurrentCard(testFlashcard2);
        testFlashcardDeck.setCurrentCard(testFlashcard3);
        testFlashcardDeck.markCardCorrect();
        assertTrue(testFlashcard1.getIsCorrect());
        assertTrue(testFlashcard3.getIsCorrect());
        assertEquals(2, testFlashcardDeck.getCorrectTracker());
        testFlashcardDeck.resetCards();
        assertFalse(testFlashcard1.getIsCorrect());
        assertFalse(testFlashcard3.getIsCorrect());
        assertEquals(0, testFlashcardDeck.getCorrectTracker());
        assertNull(testFlashcardDeck.getCurrentCard());
    }

    @Test
    public void testGetPositionOfCardInList() {
        testFlashcardDeck.addCard(testFlashcard1);
        assertEquals(0, testFlashcardDeck.getPositionOfCardInList(testFlashcard2));
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        assertEquals(1, testFlashcardDeck.getPositionOfCardInList(testFlashcard1));
        assertEquals(2, testFlashcardDeck.getPositionOfCardInList(testFlashcard2));
        assertEquals(3, testFlashcardDeck.getPositionOfCardInList(testFlashcard3));
        testFlashcardDeck.removeCard(testFlashcard2);
        assertEquals(0, testFlashcardDeck.getPositionOfCardInList(testFlashcard2));
        assertEquals(2, testFlashcardDeck.getPositionOfCardInList(testFlashcard3));
        testFlashcardDeck.addCard(testFlashcard2);
        assertEquals(3, testFlashcardDeck.getPositionOfCardInList(testFlashcard2));
    }

    @Test
    public void testCheckIfFlashcardAtThisPosition() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.addCard(testFlashcard3);
        assertTrue(testFlashcardDeck.checkIfFlashcardAtThisPosition(1));
        assertTrue(testFlashcardDeck.checkIfFlashcardAtThisPosition(2));
        assertTrue(testFlashcardDeck.checkIfFlashcardAtThisPosition(3));
        assertFalse(testFlashcardDeck.checkIfFlashcardAtThisPosition(4));
        assertFalse(testFlashcardDeck.checkIfFlashcardAtThisPosition(0));
        testFlashcardDeck.removeCard(testFlashcard1);
        assertFalse(testFlashcardDeck.checkIfFlashcardAtThisPosition(3));
        testFlashcardDeck.addCard(testFlashcard1);
        assertTrue(testFlashcardDeck.checkIfFlashcardAtThisPosition(3));
        testFlashcardDeck.removeCard(testFlashcard1);
        testFlashcardDeck.removeCard(testFlashcard3);
        testFlashcardDeck.removeCard(testFlashcard2);
        assertFalse(testFlashcardDeck.checkIfFlashcardAtThisPosition(1));
    }

    @Test
    public void testGetPercentCorrect() {
        testFlashcardDeck.addCard(testFlashcard1);
        testFlashcardDeck.addCard(testFlashcard2);
        testFlashcardDeck.setCurrentCard(testFlashcard1);
        assertEquals(2, testFlashcardDeck.deckSize());
        assertEquals(2, testFlashcardDeck.getSizeTracker());
        assertEquals (0, testFlashcardDeck.getCorrectTracker());
        testFlashcardDeck.markCardCorrect();
        assertEquals(1, testFlashcardDeck.getCorrectTracker());
        assertTrue(testFlashcard1.getIsCorrect());
        assertEquals(50, testFlashcardDeck.getPercentCorrect());
        testFlashcardDeck.setCurrentCard(testFlashcard2);
        testFlashcardDeck.markCardCorrect();
        assertEquals(100, testFlashcardDeck.getPercentCorrect());
    }

    @Test
    public void testSetName() {
        assertEquals("test", testFlashcardDeck.getName());
        testFlashcardDeck.setName("Scooby Doo");
        assertEquals("Scooby Doo", testFlashcardDeck.getName());
    }

    @Test
    public void testDeckSize() {
        assertEquals(0, testFlashcardDeck.deckSize());
        testFlashcardDeck.addCard(testFlashcard1);
        assertEquals(1, testFlashcardDeck.deckSize());
        testFlashcardDeck.addCard(testFlashcard2);
        assertEquals(2, testFlashcardDeck.deckSize());
        testFlashcardDeck.removeCard(testFlashcard2);
        assertEquals(1, testFlashcardDeck.deckSize());
    }

    @Test
    public void testToJson() {
        testFlashcardDeck.addCard(testFlashcard1);
        JSONObject testJsonObject;
        testJsonObject = testFlashcardDeck.toJson();
        assertEquals("test", testJsonObject.get("Name:"));
        assertEquals("[{\"Question:\":\"What is 1 + 1?\",\"Answer:\":\"2\"}]",
                testJsonObject.getJSONArray("Flashcards:").toString());
    }
}
