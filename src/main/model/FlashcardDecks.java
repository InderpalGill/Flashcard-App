package model;

import java.util.ArrayList;
import java.util.List;

// Represents a collection of FlashcardDeck used for studying
public class FlashcardDecks {

    private final ArrayList<FlashcardDeck> myFlashcardDecks;
    private FlashcardDeck currentFlashcardDeck;

    //EFFECTS: Constructor for class. Creates a list of FlashcardDecks with current selected FlashcardDeck set to null
    public FlashcardDecks() {
        myFlashcardDecks = new ArrayList<>();
        this.currentFlashcardDeck = null;
    }

    //MODIFIES: this
    //EFFECTS: Adds given FlashcardDeck to end of myFlashcardDecks.
    public void addFlashcardDeck(FlashcardDeck f) {
        myFlashcardDecks.add(f);
    }

    //REQUIRES: myFlashcardDecks size > 0
    //MODIFIES: this
    //EFFECTS: Removes given FlashcardDeck from myFlashcardDecks
    public void removeFlashcardDeck(FlashcardDeck f) {
        myFlashcardDecks.remove(f);
    }

    //EFFECTS: Returns the position of given FlashcardDeck in myFlashcardDecks. Returns -1 if FlashcardDeck is not in
    //myFlashcardDecks. Gives position as index place + 1
    public int getPositionInList(FlashcardDeck f) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (d == f) {
                return (myFlashcardDecks.indexOf(d) + 1);
            }
        }
        return -1;
    }

    //EFFECTS: Returns the position of a FlashcardDeck of given name in myFlashcardDecks. Returns -1 if FlashcardDeck of
    //given name is not in myFlashcardDecks. Gives position as index + 1.
    public int getPositionInList(String name) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (d.getName().equals(name)) {
                return (myFlashcardDecks.indexOf(d) + 1);
            }
        }
        return -1;
    }

    //EFFECTS: Returns true if there is a FlashcardDeck in myFlashcardDecks, at the position given by index.
    // False otherwise. Does not use zero based indexing, the index parameter is number of position on a list.
    public boolean checkIfFlashcardDeckAtThisPosition(int index) {
        if ((index <= myFlashcardDecks.size()) && (index != 0))  {
            return myFlashcardDecks.get(index - 1) instanceof FlashcardDeck;
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: Sets currentFlashcardDeck to FlashcardDeck in my FlashcardDecks that was in the position indicated by the
    // index parameter.
    public void setCurrentFlashCardDeckBasedOnPositionInList(int index) {
        for (FlashcardDeck d : myFlashcardDecks) {
            if (index == (myFlashcardDecks.indexOf(d) + 1)) {
                setCurrentFlashcardDeck(d);
            }
        }
    }

    //EFFECTS: Returns a FlashcardDeck in myFlashcardDecks, that was in position indicated by the input parameter.
    public FlashcardDeck getFlashcardDeckFromPosition(int position) {
        return myFlashcardDecks.get(position - 1);
    }

    //EFFECTS: Returns number of FlashcardDecks in myFlashcardDecks
    public int getSizeFlashcardDecks() {
        return myFlashcardDecks.size();
    }

    //EFFECTS: Returns FlashcardDeck that is currentFlashcardDeck.
    public FlashcardDeck getCurrentFlashcardDeck() {
        return currentFlashcardDeck;
    }

    //MODIFIES: this
    //EFFECTS: Sets currentFlashcardDeck field to input parameter FlashcardDeck
    public void setCurrentFlashcardDeck(FlashcardDeck f) {
        this.currentFlashcardDeck = f;
    }


    //EFFECTS: returns a list of all FlashcardDecks that are in myFlashcardDecks.
    public List<FlashcardDeck> getFlashcardDecks() {
        return myFlashcardDecks;
    }
}
