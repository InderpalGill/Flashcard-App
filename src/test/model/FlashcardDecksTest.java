package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardDecksTest {
    private FlashcardDecks testFlashcardDecks;
    private FlashcardDeck testFlashcardDeck1;
    private FlashcardDeck testFlashcardDeck2;
    private FlashcardDeck testFlashcardDeck3;
    private Flashcard testFlashcard1;
    private Flashcard testFlashcard2;
    private Flashcard testFlashcard3;

    @BeforeEach
    public void runBefore() {
        testFlashcardDecks = new FlashcardDecks();
        testFlashcardDeck1 = new FlashcardDeck("Math test");
        testFlashcardDeck2 = new FlashcardDeck("Geography test");
        testFlashcardDeck3 = new FlashcardDeck("Science test");
        testFlashcard1 = new Flashcard("What is 1 + 1?", "2");
        testFlashcard2 = new Flashcard("What is the capital of Canada?", "Ottawa");
        testFlashcard3 = new Flashcard("Is a tomato a fruit?", "Yes");
        testFlashcardDeck1.addCard(testFlashcard1);
        testFlashcardDeck2.addCard(testFlashcard2);
        testFlashcardDeck3.addCard(testFlashcard3);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
    }

    @Test
    public void testAddFlashcardDeck() {
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList(testFlashcardDeck1));
        assertEquals(2, testFlashcardDecks.getPositionInList(testFlashcardDeck2));
    }

    @Test
    public void testRemoveFlashcardDeck() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck3);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList(testFlashcardDeck2));
    }

    @Test
    public void testGetPositionInListFlashcardDeckParameter() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList(testFlashcardDeck1));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(2, testFlashcardDecks.getPositionInList(testFlashcardDeck2));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(3, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(3, testFlashcardDecks.getPositionInList(testFlashcardDeck3));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList(testFlashcardDeck2));
        assertEquals(2, testFlashcardDecks.getPositionInList(testFlashcardDeck3));
        assertEquals(-1, testFlashcardDecks.getPositionInList(testFlashcardDeck1));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList(testFlashcardDeck3));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck3);
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(-1, testFlashcardDecks.getPositionInList(testFlashcardDeck2));
    }

    @Test
    public void testGetPositionInListDeckNameParameter() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList("Math test"));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(2, testFlashcardDecks.getPositionInList("Geography test"));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(3, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(3, testFlashcardDecks.getPositionInList("Science test"));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList("Geography test"));
        assertEquals(2, testFlashcardDecks.getPositionInList("Science test"));
        assertEquals(-1, testFlashcardDecks.getPositionInList("Math test"));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(-1, testFlashcardDecks.getPositionInList("Geography test"));
        assertEquals(1, testFlashcardDecks.getPositionInList("Science test"));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck3);
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(-1, testFlashcardDecks.getPositionInList("Geography test"));
        assertEquals(-1, testFlashcardDecks.getPositionInList("Science test"));
    }

    @Test
    public void testGetPositionInListDeckNameParameterDuplicates() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(3, testFlashcardDecks.getSizeFlashcardDecks());
        assertEquals(1, testFlashcardDecks.getPositionInList("Math test"));
        assertEquals(2, testFlashcardDecks.getPositionInList("Geography test"));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(4, testFlashcardDecks.getPositionInList("Science test"));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getPositionInList("Geography test"));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertEquals(-1, testFlashcardDecks.getPositionInList("Geography test"));
        assertEquals(2, testFlashcardDecks.getPositionInList("Science test"));
    }

    @Test
    public void testSetCurrentFlashCardDeckBasedOnPositionInList() {
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(2);
        assertEquals(testFlashcardDeck2, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(1);
        assertEquals(testFlashcardDeck1, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(testFlashcardDeck1, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(1);
        assertEquals(testFlashcardDeck2, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(2);
        assertEquals(testFlashcardDeck3, testFlashcardDecks.getCurrentFlashcardDeck());
    }

    @Test
    public void testGetSizeFlashcardDecks() {
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(3, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(2, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck3);
        assertEquals(1, testFlashcardDecks.getSizeFlashcardDecks());
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertEquals(0, testFlashcardDecks.getSizeFlashcardDecks());
    }

    @Test
    public void testGetCurrentFlashcardDeck() {
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(null, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashcardDeck(testFlashcardDeck2);
        assertEquals(testFlashcardDeck2, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashcardDeck(testFlashcardDeck3);
        assertEquals(testFlashcardDeck3, testFlashcardDecks.getCurrentFlashcardDeck());
        testFlashcardDecks.setCurrentFlashcardDeck(testFlashcardDeck1);
        assertEquals(testFlashcardDeck1, testFlashcardDecks.getCurrentFlashcardDeck());
    }

    @Test
    public void testCheckIfFlashcardDeckAtThisPosition() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertTrue(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(1));
        assertTrue(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(2));
        assertTrue(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(3));
        assertFalse(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(4));
        assertFalse(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(0));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        assertFalse(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(3));
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        assertTrue(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(3));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck3);
        assertFalse(testFlashcardDecks.checkIfFlashcardDeckAtThisPosition(1));
    }

    @Test
    public void testGetFlashcardDeckFromPosition() {
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck1);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck2);
        testFlashcardDecks.addFlashcardDeck(testFlashcardDeck3);
        assertEquals(testFlashcardDeck1, testFlashcardDecks.getFlashcardDeckFromPosition(1));
        assertEquals(testFlashcardDeck2, testFlashcardDecks.getFlashcardDeckFromPosition(2));
        assertEquals(testFlashcardDeck3, testFlashcardDecks.getFlashcardDeckFromPosition(3));
        testFlashcardDecks.removeFlashcardDeck(testFlashcardDeck1);
        assertEquals(testFlashcardDeck2, testFlashcardDecks.getFlashcardDeckFromPosition(1));
    }

}
