package ui;

//Enum of names for main buttons in application
// references the AlarmSystemProject for how to create JButtons
//https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public enum ButtonNames {
    CREATE("Create a new Flashcard Deck"),
    SELECT("Select an existing Flashcard Deck"),
    SAVE("Save Flashcard Deck to file"),
    LOAD("Load Flashcard Deck from file"),
    DELETE("Delete a Flashcard Deck"),
    QUIT("Quit the application"),
    DISPLAY("Display a list of all Flashcards in this Deck to edit or delete"),
    CREATE_CARD("Create a new Flashcard to add to this deck"),
    REMOVE("Remove this Flashcard from deck"),
    EDIT_QUESTION("Edit the question of this Flashcard"),
    EDIT_ANSWER("Edit the answer of this Flashcard"),
    STUDY("Study this deck"),
    CHANGE_NAME("Change the name of this deck"),
    QUIZ("Begin a quiz session of all Flashcards in this Deck"),
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
