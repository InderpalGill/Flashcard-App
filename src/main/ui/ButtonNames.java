package ui;

public enum ButtonNames {
    CREATE("Create a new Flashcard Deck"),
    SELECT("Select an existing Flashcard Deck"),
    SAVE("Save Flashcard Deck to file"),
    LOAD("Load Flashcard Deck from file"),
    DELETE("Delete a Flashcard Deck"),
    QUIT("Quit the application"),
    DISPLAY("Display a list of all Flashcards in this Deck"),
    CREATE_CARD("Create a new Flashcard to add to this deck"),
    REMOVE("Remove a Flashcard from this deck"),
    EDIT("Edit a Flashcard in this deck"),
    STUDY("Study this deck"),
    CHANGE_NAME("Change the name of this deck"),
    BACK("Go back to previous menu");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
