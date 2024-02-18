package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of FlashcardDeck used for studying
public class FlashcardDecks {

    private ArrayList<FlashcardDeck> myFlashcardDecks;
    private FlashcardDeck currentFlashcardDeck;

    //EFFECTS: Creates a list of FlashcardDecks with current selected FlashcardDeck set to null
    public FlashcardDecks() {
        myFlashcardDecks = new ArrayList<FlashcardDeck>();
        this.currentFlashcardDeck = null;
    }

    public void addFlashcardDeck(FlashcardDeck f) {
        myFlashcardDecks.add(f);
    }

    public void removeFlashcardDeck(FlashcardDeck f) {
        myFlashcardDecks.remove(f);
    }

    public int getPositionInList(FlashcardDeck f) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (d == f) {
                return (myFlashcardDecks.indexOf(d) + 1);
            }
        }
        return -1;
    }

    public int getPositionInList(String name) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (d.getName().equals(name)) {
                return (myFlashcardDecks.indexOf(d) + 1);
            }
        }
        return -1;
    }

    public boolean checkIfFlashcardDeckAtThisPosition(int index) {
        if ((index <= myFlashcardDecks.size()) && (index != 0))  {
            return myFlashcardDecks.get(index - 1) instanceof FlashcardDeck;
        }
        return false;
    }

    public void setCurrentFlashCardDeckBasedOnPositionInList(int index) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (index == (myFlashcardDecks.indexOf(d) + 1)) {
                setCurrentFlashcardDeck(d);
            }
        }
    }

    public int getSizeFlashcardDecks() {
        return myFlashcardDecks.size();
    }

    public FlashcardDeck getCurrentFlashcardDeck() {
        return currentFlashcardDeck;
    }

    public void setCurrentFlashcardDeck(FlashcardDeck f) {
        this.currentFlashcardDeck = f;
    }

    public List<FlashcardDeck> getFlashcardDecks() {
        return myFlashcardDecks;
    }

}
