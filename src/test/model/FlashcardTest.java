package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardTest {
    private Flashcard testFlashcard1;
    private Flashcard testFlashcard2;
    private Flashcard testFlashcard3;

    @BeforeEach
    public void runBefore() {
        testFlashcard1 = new Flashcard("What is 1 + 1?", "2");
        testFlashcard2 = new Flashcard("What is the capital of Canada?", "Ottawa");
        testFlashcard3 = new Flashcard("Is a tomato a fruit?", "Yes");
    }

    @Test
    public void testConstructor() {
        assertEquals("Is a tomato a fruit?", testFlashcard3.getQuestion());
        assertEquals("Yes", testFlashcard3.getAnswer());
        assertFalse(testFlashcard2.getIsCorrect());
    }

    @Test
    public void testSetQuestionChangeQuestions() {
        assertEquals("What is 1 + 1?", testFlashcard1.getQuestion());
        assertEquals("2", testFlashcard1.getAnswer());
        testFlashcard1.setQuestion("How old are you?");
        assertEquals("How old are you?", testFlashcard1.getQuestion());
        assertEquals("2", testFlashcard1.getAnswer());
    }

    @Test
    public void testSetAnswerChangeAnswer() {
        assertEquals("Ottawa", testFlashcard2.getAnswer());
        testFlashcard2.setAnswer("Burnaby");
        assertEquals("What is the capital of Canada?", testFlashcard2.getQuestion());
        assertEquals("Burnaby", testFlashcard2.getAnswer());
    }

    @Test
    public void testSetIsCorrect() {
        assertFalse(testFlashcard1.getIsCorrect());
        testFlashcard1.setIsCorrect(true);
        assertTrue(testFlashcard1.getIsCorrect());
        testFlashcard1.setIsCorrect(true);
        assertTrue(testFlashcard1.getIsCorrect());
        testFlashcard1.setIsCorrect(false);
        assertFalse(testFlashcard1.getIsCorrect());
    }


}
