package model;

import java.util.ArrayList;
import java.util.List;

// Represents a deck which can hold many flashcards
public class FlashcardDeck {

    private ArrayList<Flashcard> flashcardDeck;
    private double correctTracker;
    private double sizeTracker;
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public void addCard(String question, String answer) {
        Flashcard newCard = new Flashcard(question, answer);
        flashcardDeck.add(newCard);
        sizeTracker += 1;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int getPositionOfCardInList(Flashcard f) {
        return (flashcardDeck.indexOf(f) + 1);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public List<Flashcard> getFlashcards() {
        return flashcardDeck;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public Flashcard getCurrentCard() {
        return currentCard;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public String getName() {
        return name;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public Flashcard getCardFromIndex(int index) {
        return flashcardDeck.get(index);
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public double getPercentCorrect() {
        double correct = ((correctTracker / sizeTracker) * 100);
        return correct;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public double getCorrectTracker() {
        return correctTracker;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public double getSizeTracker() {
        return sizeTracker;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int deckSize() {
        return flashcardDeck.size();
    }


}
