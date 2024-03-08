package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a deck which can hold many flashcards
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class FlashcardDeck implements Writable {

    private final ArrayList<Flashcard> flashcardDeck;
    private double correctTracker;
    private double sizeTracker;
    private Flashcard currentCard;
    private String name;


    //EFFECTS: Constructor for class. Creates a new deck of given name with list of no flashcards,
    // correctTracker at zero, and sizeTracker at zero.
    public FlashcardDeck(String name) {
        flashcardDeck = new ArrayList<>();
        this.name = name;
        this.correctTracker = 0;
        this.sizeTracker = 0;
    }

    //MODIFIES:this
    //EFFECTS: Creates a new flashcard with question and answer. Adds new flashcard to flashcardDeck
    //increases sizeTracker by 1
    public void addCard(String question, String answer) {
        Flashcard newCard = new Flashcard(question, answer);
        flashcardDeck.add(newCard);
        sizeTracker += 1;
    }

    //MODIFIES: this
    //EFFECTS: adds given flashcard to flashcardDeck, increases sizeTracker by 1
    public void addCard(Flashcard f) {
        flashcardDeck.add(f);
        sizeTracker += 1;
    }

    //REQUIRES: flashcardDeck size >= 1
    //MODIFIES: this
    //EFFECTS: removes card of given question from flashcardDeck, reduces size tracker by 1
    public void removeCard(String question) {
        ArrayList<Flashcard> match = new ArrayList<>();
        for (Flashcard f : flashcardDeck) {
            if (f.getQuestion().equals(question)) {
                match.add(f);
                sizeTracker--;
            }
        }
        flashcardDeck.removeAll(match);
    }

    //REQUIRES: flashcardDeck size >= 1
    //MODIFIES: this
    //EFFECTS: removes given Flashcard object from flashcardDeck, reduces size tracker by 1
    public void removeCard(Flashcard f) {
        flashcardDeck.remove(f);
        sizeTracker--;
    }

    //MODIFIES: this
    //EFFECTS: Lets user mark currentCard as correct. Changes flashcard.isCorrect() from false to true
    public void markCardCorrect() {
        currentCard.setIsCorrect(true);
        correctTracker++;
    }

    //MODIFIES: this
    //EFFECTS: Sets Flashcard as currentCard
    public void setCurrentCard(Flashcard f) {
        currentCard = f;
    }

    //MODIFIES: this
    //EFFECTS: Sets name of FlashcardDeck
    public void setName(String s) {
        name = s;
    }

    //EFFECTS: returns the position of a specified flashcard in flashcardDeck. Returns 0 if flashcard is not in
    // flashcardDeck. Index 0 in flashcardDeck is position 1.
    public int getPositionOfCardInList(Flashcard f) {
        return (flashcardDeck.indexOf(f) + 1);
    }

    //EFFECTS: returns true if there is a flashcard at the position given by index in flashcardDeck, false otherwise.
    // does not use zero based indexing, the index parameter is number of position on a list.
    public Boolean checkIfFlashcardAtThisPosition(int index) {
        if ((index <= flashcardDeck.size()) && (index != 0)) {
            return flashcardDeck.get(index - 1) instanceof Flashcard;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: resets all cards in deck to isCorrect = false
    //resets correctTracker to zero
    public void resetCards() {
        for (Flashcard f : flashcardDeck) {
            if (f.getIsCorrect()) {
                f.setIsCorrect(false);
            }
        }
        correctTracker = 0;
        currentCard = null;
    }

    //EFFECTS: returns list of Flashcards that are in flashcardDeck
    public List<Flashcard> getFlashcards() {
        return flashcardDeck;
    }

    //EFFECTS: returns the Flashcard that is currentCard in flashcardDeck
    public Flashcard getCurrentCard() {
        return currentCard;
    }

    //EFFECTS: returns the name of the flashcardDeck
    public String getName() {
        return name;
    }

    //REQUIRES: flashcardDeck size > 0
    //EFFECTS: returns Flashcard at specified index in flashcardDeck list
    public Flashcard getCardFromIndex(int index) {
        return flashcardDeck.get(index);
    }

    //REQUIRES: sizeTracker != 0
    //MODIFIES:
    //EFFECTS: returns percentage of flashcards answered correctly during study session
    public double getPercentCorrect() {
        return ((correctTracker / sizeTracker) * 100);
    }

    //EFFECTS: returns correctTracker field. Number of flashcards in deck that were answered correctly.
    public double getCorrectTracker() {
        return correctTracker;
    }

    //EFFECTS: returns sizeTracker field (double). Number of flashcards in flashcard deck
    public double getSizeTracker() {
        return sizeTracker;
    }

    //EFFECTS: returns an int of number of flashcards in flashcard deck
    public int deckSize() {
        return flashcardDeck.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name:", this.name);
        json.put("Flashcards:", flashcardToJson());
        return json;
    }

    private JSONArray flashcardToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Flashcard f : flashcardDeck) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
