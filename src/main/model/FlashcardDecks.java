package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a collection of FlashcardDeck used for studying
public class FlashcardDecks implements Writable {

    private final ArrayList<FlashcardDeck> myFlashcardDecks;
    private FlashcardDeck currentFlashcardDeck;
    private final String name;

    //EFFECTS: Constructor for class. Creates a list of FlashcardDecks with current selected FlashcardDeck set to null
    public FlashcardDecks(String name) {
        myFlashcardDecks = new ArrayList<>();
        this.currentFlashcardDeck = null;
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: Adds given FlashcardDeck to end of myFlashcardDecks, records Event on EventLog.
    public void addFlashcardDeck(FlashcardDeck f) {
        EventLog.getInstance().logEvent(new Event("Added Flashcard Deck: " + f.getName()
                + ", to my Flashcard Decks" + "\n"));
        myFlashcardDecks.add(f);
    }

    //REQUIRES: myFlashcardDecks size > 0
    //MODIFIES: this
    //EFFECTS: Removes given FlashcardDeck from myFlashcardDecks, records Event on EventLog
    public void removeFlashcardDeck(FlashcardDeck f) {
        EventLog.getInstance().logEvent(new Event("Removed Flashcard Deck: " + f.getName()
                + ", from my Flashcard Decks" + "\n"));
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

    //EFFECTS: returns name of myFlashcardDecks
    public String getName() {
        return this.name;
    }

    //MODIFIES: this
    //EFFECTS: Converts a FlashcardDecks object to a JSONObject allowing it to be saved to file. Uses key "name" to
    //store this.name as value, uses "FlashcardDeck:" as key to store array of FlashcardDeck as value.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("FlashcardDeck:", flashcardDeckToJson());
        return json;
    }

    //EFFECTS Creates a JSONArray, iterates through myFlashcardDecks and converts each FlashcardDeck in myFlashcardDecks
    // to JSONObject, and adds it to JSONArray
    private JSONArray flashcardDeckToJson() {
        JSONArray jsonArray = new JSONArray();
        for (FlashcardDeck f : myFlashcardDecks) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }
}
