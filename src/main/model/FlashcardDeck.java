package model;

import java.util.ArrayList;
import java.util.List;

// Represents a deck which can hold many flashcards
// INVARIANT: flashcardDeck and cardTracker, always have same number of elements,
// index of a particular flashcard in flashcardDeck == index of card in cardTracker
public class FlashcardDeck {

    private ArrayList<Flashcard> flashcardDeck;
    private int correctTracker;
    private int sizeTracker;
    private int percentCorrect;
    private Flashcard currentCard;
    private String name;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Constructor for class. Creates a new deck of given name with list of no flashcards,
    // cardTracker list with no entries, and cardTrackingNumber at 0.
    public FlashcardDeck(String name) {
        flashcardDeck = new ArrayList<Flashcard>();
        this.name = name;
        this.correctTracker = 0;
        this.sizeTracker = 0;
    }

    public void addCard(String question, String answer) {
        Flashcard newCard = new Flashcard(question, answer);
        flashcardDeck.add(newCard);
        sizeTracker += 1;
    }

    public void addCard(Flashcard f) {
        flashcardDeck.add(f);
        sizeTracker += 1;
    }

    //EFFECTS: removes card of given question from flashcardDeck, reduces size tracker by 1
    public void removeCard(String question) {
        ArrayList<Flashcard> match = new ArrayList<Flashcard>();
        for (Flashcard f : flashcardDeck) {
            if (f.getQuestion().equals(question)) {
                match.add(f);
                sizeTracker--;
            }
        }
        flashcardDeck.removeAll(match);
    }

    //EFFECTS: removes given Flashcard object from flashcardDeck, reduces size tracker by 1
    public void removeCard(Flashcard f) {
        flashcardDeck.remove(f);
        sizeTracker--;
    }

    //EFFECTS: Lets user mark currentCard as correct. Changes flashcard.isCorrect() from false to true
    public void markCardCorrect() {
        currentCard.setIsCorrect(true);
        correctTracker++;
    }

    //EFFECTS: Sets Flashcard as currentCard
    public void setCurrentCard(Flashcard f) {
        currentCard = f;
    }

    //EFFECTS: Sets name of FlashcardDeck
    public void setName(String s) {
        name = s;
    }

    public int getPositionOfCardInList(Flashcard f) {
        return (flashcardDeck.indexOf(f) + 1);
    }

    public Boolean checkIfFlashcardAtThisPosition(int index) {
        if ((index <= flashcardDeck.size()) && (index != 0)) {
            return flashcardDeck.get(index - 1) instanceof Flashcard;
        }
        return false;
    }


    //Effects: resets all cards in deck to isCorrect = false
    //resets correctTracker to zero
    public void resetCards() {
        for (Flashcard f : flashcardDeck) {
            if (f.getIsCorrect() == true) {
                f.setIsCorrect(false);
            }
        }
        correctTracker = 0;
        currentCard = null;
    }

    public List<Flashcard> getFlashcards() {
        return flashcardDeck;
    }

    public Flashcard getCurrentCard() {
        return currentCard;
    }

    public String getName() {
        return name;
    }

    public Flashcard getCardFromIndex(int index) {
        return flashcardDeck.get(index);
    }

    public int getPercentCorrect() {
        percentCorrect = (((correctTracker) / (sizeTracker)) * 100);
        return percentCorrect;
    }

    public int getCorrectTracker() {
        return correctTracker;
    }

    public int getSizeTracker() {
        return sizeTracker;
    }

    public int deckSize() {
        return flashcardDeck.size();
    }


}
