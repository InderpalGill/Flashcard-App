package persistence;


import model.FlashcardDecks;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Class that has methods inherited by JsonWriter and JsonReader
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkFlashcardQuestion(String question, int flashcardDeckPosition, int flashcardIndex,
                                          FlashcardDecks fd) {
        assertEquals(question, fd.getFlashcardDeckFromPosition(flashcardDeckPosition).getCardFromIndex(flashcardIndex)
                .getQuestion());
    }

    protected void checkFlashcardAnswer(String answer, int flashcardDeckPosition, int flashcardIndex,
                                        FlashcardDecks fd) {
        assertEquals(answer, fd.getFlashcardDeckFromPosition(flashcardDeckPosition).getCardFromIndex(flashcardIndex)
                .getAnswer());
    }

    protected void checkFlashcardDeck(String name, int flashcardDeckPosition, int size, FlashcardDecks fd) {
        assertEquals(name, fd.getFlashcardDeckFromPosition(flashcardDeckPosition).getName());
        assertEquals(size, fd.getFlashcardDeckFromPosition(flashcardDeckPosition).deckSize());
    }


}
